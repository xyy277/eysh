package com.aebiz.app.web.commons.shiro.realm;

import com.aebiz.app.accuser.modules.models.Sc_account_link;
import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.app.accuser.modules.services.ScAccountLinkService;
import com.aebiz.app.accuser.modules.services.ScAccountUserService;
import com.aebiz.app.tourist.modules.models.Tour_user;
import com.aebiz.app.tourist.modules.services.TourUserService;
import com.aebiz.app.web.commons.shiro.token.MemberCaptchaToken;
import com.aebiz.baseframework.shiro.exception.CaptchaEmptyException;
import com.aebiz.baseframework.shiro.exception.CaptchaIncorrectException;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.nutz.castor.Castors;
import org.nutz.dao.Cnd;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wizzer on 2017/1/11.
 */
public class MemberAuthorizingRealm extends AuthorizingRealm {

    private static final Log log = Logs.get();

    @Autowired
    private TourUserService tourUserService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token.getClass().isAssignableFrom(MemberCaptchaToken.class)) {
            MemberCaptchaToken authcToken = Castors.me().castTo(token, MemberCaptchaToken.class);
            String loginname = authcToken.getUsername();
            String captcha = authcToken.getCaptcha();
            if (Strings.isBlank(loginname)) {
                throw Lang.makeThrow(AuthenticationException.class, "Member name is empty");
            }
            int errCount = NumberUtils.toInt(Strings.sNull(SecurityUtils.getSubject().getSession(true).getAttribute("memberErrCount")));
            if (errCount > 2) {
                //输错三次显示验证码窗口
                if (Strings.isBlank(captcha)) {
                    throw Lang.makeThrow(CaptchaEmptyException.class, "Captcha is empty");
                }
                String _captcha = Strings.sBlank(SecurityUtils.getSubject().getSession(true).getAttribute("memberCaptcha"));
                if (!authcToken.getCaptcha().equalsIgnoreCase(_captcha)) {
                    throw Lang.makeThrow(CaptchaIncorrectException.class, "Captcha is error");
                }
            }
            Tour_user user = tourUserService.getTuoristByLoginname(loginname);
            if(user!=null){
                //移除session
                SecurityUtils.getSubject().getSession(true).removeAttribute("memberErrCount");
                SecurityUtils.getSubject().getSession(true).removeAttribute("memberUid");
                SecurityUtils.getSubject().getSession(true).removeAttribute("memberUsername");

                SecurityUtils.getSubject().getSession(true).setAttribute("accuserErrCount", 0);
                SecurityUtils.getSubject().getSession(true).setAttribute("accuserUid", user.getId());
                SecurityUtils.getSubject().getSession(true).setAttribute("accuserUsername", user.getLoginname());
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
                info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
                return info;
            }else{
                if (Lang.isEmpty(user)) {
                    throw Lang.makeThrow(UnknownAccountException.class, "Account [ %s ] not found", loginname);
                }
            }
    }
        return null;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object object = principals.getPrimaryPrincipal();
        if (object.getClass().isAssignableFrom(Tour_user.class)) {
            Tour_user user = Castors.me().castTo(object, Tour_user.class);
            if (!Lang.isEmpty(user)) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                List<String> roleNameList = new ArrayList<String>();
                roleNameList.add("member");
                info.addRoles(roleNameList);
                return info;
            } else {
                return null;
            }
        }
        return null;
    }

    public MemberAuthorizingRealm() {
        this(null, null);
    }

    public MemberAuthorizingRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
        setAuthenticationTokenClass(MemberCaptchaToken.class);
    }

    public MemberAuthorizingRealm(CacheManager cacheManager) {
        this(cacheManager, null);
    }

    public MemberAuthorizingRealm(CredentialsMatcher matcher) {
        this(null, matcher);
    }
}