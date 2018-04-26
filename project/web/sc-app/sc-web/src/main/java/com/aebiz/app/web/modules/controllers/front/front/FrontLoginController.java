package com.aebiz.app.web.modules.controllers.front.front;


import com.aebiz.app.tourist.modules.models.Tour_user;
import com.aebiz.app.tourist.modules.services.TourUserService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.shiro.token.MemberCaptchaToken;
import com.aebiz.app.web.commons.utils.SSOUtil;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.shiro.exception.CaptchaEmptyException;
import com.aebiz.baseframework.shiro.exception.CaptchaIncorrectException;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.RSAUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.nutz.dao.Cnd;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Random;


/**
 * @author: 277
 * @date: 2018/3/21 17:01
 * 前台登录校验
 */
@Controller
@RequestMapping("/front/login")
public class FrontLoginController {

    private static final Log log = Logs.get();

    @Autowired
    private TourUserService tourUserService;

    @RequestMapping("")
    public String login(HttpServletRequest request) {
        try {
            HashMap<String, Object> map = RSAUtil.getKeys();
            //生成公钥和私钥
            RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
            RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
            //模
            String publicKeyModulus = publicKey.getModulus().toString(16);
            //公钥指数
            String publicKeyExponent = publicKey.getPublicExponent().toString(16);
            //私钥指数
            request.setAttribute("memberPublicKeyExponent", publicKeyExponent);
            request.setAttribute("memberPublicKeyModulus", publicKeyModulus);
            request.getSession().setAttribute("memberPrivateKey", privateKey);
            String returnUrl = request.getParameter("returnUrl");
            log.debug(returnUrl);
            if(Strings.isNotBlank(returnUrl)){
                request.setAttribute("returnUrl",returnUrl);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(new Random().nextInt(10)%2==0){
            return "pages/front/tourist/login2";
        }
        return "pages/front/tourist/login";
    }

    @RequestMapping("/loginDo")
    @SJson
    public Object loginDo(@RequestParam String username, @RequestParam String password,
                          @RequestParam(value = "captcha", defaultValue = "", required = false) String captcha,
                          HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        int errCount = 0;
        try {
            Subject subject = SecurityUtils.getSubject();
            Cnd cnd = Cnd.NEW();
            ThreadContext.bind(subject);
            subject.login(createToken(username, password, true, captcha, request));
            Tour_user tour_user= (Tour_user) subject.getPrincipal();
            tour_user.setLast_time(DateUtil.getDateTime());
            tour_user.setLast_ip(request.getRemoteAddr());
            tourUserService.updateIgnoreNull(tour_user);
            session.setAttribute("uid",tour_user.getId());
            session.setAttribute("nickname",tour_user.getNickname());
            log.debug(tour_user);
            String returnUrl = request.getParameter("returnUrl");
            if(Strings.isNotBlank(returnUrl)){
                return Result.success(returnUrl);
            }
            return Result.success("");
        } catch (CaptchaIncorrectException e) {
            //自定义的验证码错误异常
            return Result.error(1, "sys.login.error.captcha");
        } catch (CaptchaEmptyException e) {
            //验证码为空
            return Result.error(2, "sys.login.error.captcha");
        } catch (LockedAccountException e) {
            return Result.error(3, "sys.login.error.locked");
        } catch (UnknownAccountException e) {
            errCount++;
            SecurityUtils.getSubject().getSession(true).setAttribute("platformErrCount", errCount);
            return Result.error(4, "sys.login.error.username");
        } catch (AuthenticationException e) {
            errCount++;
            SecurityUtils.getSubject().getSession(true).setAttribute("platformErrCount", errCount);
            return Result.error(5, "sys.login.error.password");
        } catch (Exception e) {
            errCount++;
            SecurityUtils.getSubject().getSession(true).setAttribute("platformErrCount", errCount);
            return Result.error(6, "sys.login.error.system");
        }
    }
    protected AuthenticationToken createToken(String username, String password, boolean rememberMe, String captcha, HttpServletRequest request) {
        String host = request.getRemoteHost();
        try {
            RSAPrivateKey memberPrivateKey = (RSAPrivateKey) request.getSession().getAttribute("memberPrivateKey");
            if (memberPrivateKey != null) {
                password = RSAUtil.decryptByPrivateKey(password, memberPrivateKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MemberCaptchaToken(username, password, rememberMe, host, captcha);
    }
}
