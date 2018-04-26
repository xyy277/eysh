package com.aebiz.app.web.commons.shiro.realm;


import com.aebiz.app.accuser.modules.models.Sc_account_link;
import com.aebiz.app.accuser.modules.models.Sc_account_user;
import com.aebiz.app.accuser.modules.services.ScAccountLinkService;
import com.aebiz.app.accuser.modules.services.ScAccountUserService;
import com.aebiz.app.web.commons.shiro.token.AccUserCaptchaToken;
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

/**
 * Created by wizzer on 2017/1/11.
 */
public class AccUserAuthorizingRealm extends AuthorizingRealm {
    private static final Log log = Logs.get();
    @Autowired
    private ScAccountUserService scAccountUserService;
    @Autowired
    private ScAccountLinkService scAccountLinkService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token.getClass().isAssignableFrom(AccUserCaptchaToken.class)) {
            AccUserCaptchaToken authcToken = Castors.me().castTo(token, AccUserCaptchaToken.class);
            String loginname = authcToken.getUsername();
            String captcha = authcToken.getCaptcha();
            if (Strings.isBlank(loginname)) {
                throw Lang.makeThrow(AuthenticationException.class, "Account name is empty");
            }
            int errCount = NumberUtils.toInt(Strings.sNull(SecurityUtils.getSubject().getSession(true).getAttribute("storeErrCount")));
            if (errCount > 2) {
                //输错三次显示验证码窗口
                if (Strings.isBlank(captcha)) {
                    throw Lang.makeThrow(CaptchaEmptyException.class, "Captcha is empty");
                }
                String _captcha = Strings.sBlank(SecurityUtils.getSubject().getSession(true).getAttribute("storeCaptcha"));
                if (!authcToken.getCaptcha().equalsIgnoreCase(_captcha)) {
                    throw Lang.makeThrow(CaptchaIncorrectException.class, "Captcha is error");
                }
            }
            Sc_account_user scAccountUser = scAccountUserService.getAccountByLoginname(loginname);
            Sc_account_link user = scAccountLinkService.fetch(Cnd.where("account_id", "=", scAccountUser.getId()));
            //移除session
            SecurityUtils.getSubject().getSession(true).removeAttribute("memberErrCount");
            SecurityUtils.getSubject().getSession(true).removeAttribute("memberUid");
            SecurityUtils.getSubject().getSession(true).removeAttribute("memberTableId");
            SecurityUtils.getSubject().getSession(true).removeAttribute("memberUsername");
            SecurityUtils.getSubject().getSession(true).removeAttribute("memberType");

            SecurityUtils.getSubject().getSession(true).setAttribute("accuserErrCount", 0);
            SecurityUtils.getSubject().getSession(true).setAttribute("accuserUid", user.getAccount_id());
            SecurityUtils.getSubject().getSession(true).setAttribute("tableId", user.getTable_id());
            SecurityUtils.getSubject().getSession(true).setAttribute("accuserUsername", scAccountUser.getLoginname());
            SecurityUtils.getSubject().getSession(true).setAttribute("serviceType", user.getType());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, scAccountUser.getPassword(), getName());
            info.setCredentialsSalt(ByteSource.Util.bytes(scAccountUser.getSalt()));
            return info;
        }
        return null;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object object = principals.getPrimaryPrincipal();
        if (object.getClass().isAssignableFrom(Sc_account_link.class)) {
            Sc_account_link user = Castors.me().castTo(object, Sc_account_link.class);
            if (!Lang.isEmpty(user)) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                return info;
            } else {
                return null;
            }
        }
        return null;
    }

    public AccUserAuthorizingRealm() {
        this(null, null);
    }

    public AccUserAuthorizingRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
        setAuthenticationTokenClass(AccUserCaptchaToken.class);
    }

    public AccUserAuthorizingRealm(CacheManager cacheManager) {
        this(cacheManager, null);
    }

    public AccUserAuthorizingRealm(CredentialsMatcher matcher) {
        this(null, matcher);
    }
}