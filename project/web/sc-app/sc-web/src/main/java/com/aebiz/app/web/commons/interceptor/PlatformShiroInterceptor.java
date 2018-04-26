package com.aebiz.app.web.commons.interceptor;

import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.baseframework.base.Result;
import com.aebiz.commons.utils.StringUtil;
import com.aebiz.app.sys.modules.models.Sys_user;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.nutz.castor.Castors;
import org.nutz.lang.Encoding;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wizzer on 2017/1/12.
 */
public class PlatformShiroInterceptor extends HandlerInterceptorAdapter {
    private Log log = Logs.get();

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return whenUnauthenticated(request, response);
        }
        Object object = subject.getPrincipal();
        if (object.getClass().isAssignableFrom(Sys_user.class)) {
            Sys_user user = Castors.me().castTo(object, Sys_user.class);
            if (user == null || user.isDisabled() || user.getRoles() == null || user.getRoles().size() < 1) {
                return whenUnauthorized(request, response);
            }
        } else return whenUnauthorized(request, response);
        return true;
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) {
        if (ex != null) {
            if (ex instanceof UnauthenticatedException) {
                whenUnauthenticated(request, response);
            } else if (ex instanceof UnauthorizedException) {
                whenUnauthorized(request, response);
            } else {
                whenUnauthenticated(request, response);
            }
        }
    }

    /**
     * 登录失效
     *
     * @param request
     * @param response
     */
    private boolean whenUnauthenticated(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtil.isAjax(request)) {
                response.reset();
                response.addHeader("loginStatus", "accessDenied");
                response.setCharacterEncoding(Encoding.UTF8);
                response.setContentType("application/json");
                response.getWriter().write(Result.error("globals.platform.accessDenied").toString());
                response.getWriter().flush();
                response.getWriter().close();
            } else {
                //response.sendRedirect(Globals.APP_BASE + "/sso/login?returnUrl=" + request.getRequestURL());
                response.sendRedirect(Globals.APP_BASE + "/platform/login?returnUrl=" + request.getRequestURL());
            }
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    /**
     * 没有权限
     *
     * @param request
     * @param response
     */
    private boolean whenUnauthorized(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtil.isAjax(request)) {
                response.reset();
                response.addHeader("loginStatus", "unauthorized");
                response.setCharacterEncoding(Encoding.UTF8);
                response.setContentType("application/json");
                response.getWriter().write(Result.error("globals.platform.unauthorized").toString());
                response.getWriter().flush();
                response.getWriter().close();
            } else {
                response.sendRedirect(Globals.APP_BASE + "/platform/login/noPermission");
            }
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }
}
