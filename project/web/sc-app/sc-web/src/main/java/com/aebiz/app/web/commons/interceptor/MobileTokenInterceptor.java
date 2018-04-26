package com.aebiz.app.web.commons.interceptor;

import com.aebiz.app.sys.modules.services.SysApiService;
import com.aebiz.app.sys.modules.services.impl.SysApiServiceImpl;
import com.aebiz.baseframework.base.Result;
import com.aebiz.commons.utils.SpringUtil;
import org.nutz.lang.Encoding;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 手机端APP登录用户拦截器
 *
 * @author Tony 2017-10-12
 */
public class MobileTokenInterceptor extends HandlerInterceptorAdapter {
    private static final Log log = Logs.get();

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        SysApiService apiService = SpringUtil.getBean(SysApiServiceImpl.class);
        String accountId = Strings.sBlank(request.getParameter("accountId")); // 登录用户Id
        String token = Strings.sBlank(request.getParameter("token")); // 待验证token

        if (Strings.isBlank(accountId) || Strings.isBlank(token) || !apiService.verifyToken(accountId, token)) {
            response.reset();
            response.setCharacterEncoding(Encoding.UTF8);
            response.setContentType("application/json");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().write(new Result(-1, "登录失败或token已过期", null).toString());
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }
        return true;
    }
}
