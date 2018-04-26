package com.aebiz.app.web.commons.interceptor;

import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.utils.LoginMemberUtil;
import com.aebiz.commons.utils.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wizzer on 2017/1/16.
 */
public class GlobalsInterceptor  extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute("base", Globals.APP_BASE);
        request.setAttribute("app_name", Globals.APP_NAME);
        request.setAttribute("app_short_name", Globals.APP_SHORT_NAME);
        request.setAttribute("app_domain", Globals.APP_DOMAIN);
        request.setAttribute("sso_flag", Globals.SSO_FLAG);
        request.setAttribute("site_index_qq", Globals.SITE_INDEX_QQ);
        request.setAttribute("site_name", Globals.SITE_NAME);
        request.setAttribute("config", Globals.MyConfig);
        request.setAttribute("shiro", SpringUtil.getBean(ShiroUtil.class));
        request.setAttribute("date", SpringUtil.getBean(DateUtil.class));
        request.setAttribute("string", SpringUtil.getBean(StringUtil.class));
        request.setAttribute("money", SpringUtil.getBean(MoneyUtil.class));
        request.setAttribute("area", SpringUtil.getBean(MoneyUtil.class));
        request.setAttribute("member", SpringUtil.getBean(LoginMemberUtil.class));
        request.setAttribute("staticBase", Globals.STATIC_URL);
        request.setAttribute("siteDomain", Globals.SITE_URL);
        request.setAttribute("advisory_tel", Globals.ADVISORY_TEL);
        response.setHeader("X-Powered-By","aebiz/"+ Globals.APP_VERSION+" "+ Globals.APP_COPYRIGHT+" ");
        return true;
    }
}