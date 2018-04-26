package com.aebiz.app.web.commons.sso;

import com.aebiz.app.web.commons.utils.SSOUtil;
import com.hexin.cas.client.authentication.GatewayResolver;
import org.nutz.lang.Strings;

import javax.servlet.http.HttpServletRequest;

/**
 * 简单的单点url过滤规则
 * @author zyang
 * @date 2017/9/7
 */
public class SimpleGatewayStorage implements GatewayResolver {
    private String pattern = "^/sso\\b/*.*|^/member\\b/*.*|^/accuser\\b/*.*";


    /**
     * 返回 true说明本次请求不被单点拦截
     *
     * @param request
     * @param serviceUrl
     * @return
     */
    @Override
    public boolean hasGatewayedAlready(HttpServletRequest request, String serviceUrl) {
        if (!SSOUtil.isSSO()) {
            return true;
        }
        String serverletPath = request.getServletPath();
        // 需要拦截的请求
        boolean flag = serverletPath.matches(pattern);
        if (flag) {
            String r = Strings.sNull(request.getSession(true).getAttribute("_const_cas_gateway_"));
            return "yes".equalsIgnoreCase(r);
        } else {
            return true;
        }
    }

    @Override
    public String storeGatewayInformation(HttpServletRequest request, String serviceUrl) {
        request.getSession(true).setAttribute("_const_cas_gateway_", "yes");
        return serviceUrl;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
