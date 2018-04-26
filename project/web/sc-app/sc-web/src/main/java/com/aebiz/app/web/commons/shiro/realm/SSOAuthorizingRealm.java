package com.aebiz.app.web.commons.shiro.realm;

import com.aebiz.app.accuser.modules.models.Sc_account_link;
import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.app.accuser.modules.services.ScAccountLinkService;
import com.aebiz.app.accuser.modules.services.ScAccountUserService;
import com.aebiz.app.sys.modules.models.Sys_role;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.sys.modules.services.SysRoleService;
import com.aebiz.app.sys.modules.services.SysUserService;
import com.aebiz.app.web.commons.em.SSOUserTypeEnum;
import com.aebiz.app.web.commons.shiro.token.SSOSimpleToken;
import com.alibaba.dubbo.common.URL;
import com.hexin.cas.client.authentication.AuthenticationFilter;
import com.hexin.cas.client.validation.Assertion;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.nutz.castor.Castors;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 单点登录shiro验证
 * Created by zyang on 2017/10/8.
 */
public class SSOAuthorizingRealm extends AuthorizingRealm {
    private static final Log log = Logs.get();
    @Autowired
    private ScAccountUserService scAccountUserService;
    @Autowired
    private ScAccountLinkService scAccountLinkService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token.getClass().isAssignableFrom(SSOSimpleToken.class)) {
            SSOSimpleToken ssoToken = Castors.me().castTo(token, SSOSimpleToken.class);
            if (SSOUserTypeEnum.isAdmin(ssoToken.getType())) {
                return adminDo(ssoToken);
            } else {
                return memmberDo(ssoToken);
            }
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object object = principals.getPrimaryPrincipal();
        // 如果是管理员
        if (object.getClass().isAssignableFrom(Sys_user.class)) {
            Sys_user user = Castors.me().castTo(object, Sys_user.class);
            if (!Lang.isEmpty(user) && !user.isDisabled()) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                log.debug("sysUserService.getRoleCodeList(user):::" + Json.toJson(sysUserService.getRoleCodeList(user)));
                info.addRoles(sysUserService.getRoleCodeList(user));
                List<Sys_role> roles = user.getRoles();

                if (roles == null || roles.isEmpty()) {
                    // 设置默认权限
                    roles = sysRoleService.query(Cnd.where("code", "=", "public").and("delFlag", "=", false).and("disabled", "=", false));
                }
                user.setRoles(roles);
                for (Sys_role role : roles) {
                    if (!role.isDisabled())
                        info.addStringPermissions(sysRoleService.getPermissionNameList(role));
                }

                return info;
            } else {
                return null;
            }
        }
        return null;
    }

    private AuthenticationInfo adminDo(SSOSimpleToken ssoToken) {
        String loginname = ssoToken.getUsername();
        Sys_user user = sysUserService.fetch(Cnd.where("loginname", "=", loginname));
        if (user == null) {
            user = buildAdmin();
        }
        if (user == null) {
            throw Lang.makeThrow(UnknownAccountException.class, "用户不存在");
        }
        sysUserService.fetchLinks(user, null);
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(sysRoleService.query(Cnd.where("code", "=", "public").and("delFlag", "=", false).and("disabled", "=", false)));
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw Lang.makeThrow(AuthorizationException.class, "您没有权限");
        }
        sysUserService.fillMenu(user);
        SecurityUtils.getSubject().getSession(true).setAttribute("platformErrCount", 0);
        SecurityUtils.getSubject().getSession(true).setAttribute("uid", user.getId());
        SecurityUtils.getSubject().getSession(true).setAttribute("username", user.getUsername());
        SecurityUtils.getSubject().getSession(true).setAttribute("useryType", "admin");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, "", getName());
        return info;
    }

    private AuthenticationInfo memmberDo(SSOSimpleToken ssoToken) {
        String loginname = ssoToken.getUsername();
        Sc_account_user scAccountUser = scAccountUserService.fetch(Cnd.where("loginname", "=", loginname).and("delFlag", "=", false));
        Sc_account_link user = scAccountLinkService.fetch(Cnd.where("account_id", "=", scAccountUser.getId()));
            return null;

    }

    /**
     * 单点过来的管理员如果本地没有增加一个
     */
    private Sys_user buildAdmin() {
        Assertion assertion = (Assertion) SecurityUtils.getSubject().getSession().getAttribute(AuthenticationFilter.CONST_CAS_ASSERTION);
        Map map = assertion.getPrincipal().getAttributes();
        String loginName = Strings.sNull(map.get("LOGINNAME"));
        String name = Strings.sNull(map.get("NAME"));
        String mobile = Strings.sNull(map.get("MOBILE"));
        String unitid = Strings.sNull(map.get("GROUPID"));
        Sys_user user = new Sys_user();
        user.setUsername(URL.decode(name));
        user.setLoginname(loginName);
        user.setUnitid(unitid);
        user.setMobile(mobile);
        user.setPassword(loginName);
        try {
            return sysUserService.addDo2(user);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public SSOAuthorizingRealm() {
        this(null, null);
    }

    public SSOAuthorizingRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
        setAuthenticationTokenClass(SSOSimpleToken.class);
    }

    public SSOAuthorizingRealm(CacheManager cacheManager) {
        this(cacheManager, null);
    }

    public SSOAuthorizingRealm(CredentialsMatcher matcher) {
        this(null, matcher);
    }
}
