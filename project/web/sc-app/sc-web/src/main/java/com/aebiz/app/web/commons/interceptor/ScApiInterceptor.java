package com.aebiz.app.web.commons.interceptor;

import com.aebiz.app.sys.modules.models.Sys_api;
import com.aebiz.app.sys.modules.services.SysApiService;
import com.aebiz.app.sys.modules.services.impl.SysApiServiceImpl;
import com.aebiz.app.web.commons.em.ScResultEnum;
import com.aebiz.app.web.commons.utils.SignUtil;
import com.aebiz.baseframework.base.Result;
import com.aebiz.commons.utils.SpringUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.nutz.dao.Cnd;
import org.nutz.lang.Encoding;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 即时汇总API拦截
 * Created by tony on 2017/8/16.
 */
public class ScApiInterceptor extends HandlerInterceptorAdapter {
    private static final Log log = Logs.get();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 获取传递参数
        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration<String> en = request.getParameterNames();
        while (en.hasMoreElements()) {
            String key = en.nextElement();
            System.out.println("key:: " + key + ", value:: " + request.getParameter(key));
            map.put(key, request.getParameter(key));
        }

        //根据appId获取获取对应的SysApi注册信息
        SysApiService apiService = SpringUtil.getBean(SysApiServiceImpl.class);
        String appId = Strings.sNull(map.get("appid"));
        String sign = Strings.sNull(map.get("sign"));
        String timestamp = Strings.sNull(map.get("timestamp"));
        Sys_api api = apiService.fetch(Cnd.where("appId", "=", appId));

        String msg = "";
        boolean result = true;
        //APPID不存在
        if (result && api == null) {
            msg = Result.error(ScResultEnum.APPIDNONEXIST.getKey(), ScResultEnum.APPIDNONEXIST.getMsg()).toString();
            result = false;
        }

        // APPID已经被禁用
        if (result && api.isDisabled()) {
            msg = Result.error(ScResultEnum.APPIDDISABLE.getKey(), ScResultEnum.APPIDDISABLE.getMsg()).toString();
            result = false;
        }

        // 签名失败
        if (result && !SignUtil.createSign(api.getAppKey(), map).equals(sign)) {
            msg = Result.error(ScResultEnum.SIGNERROR.getKey(), ScResultEnum.SIGNERROR.getMsg()).toString();
            result = false;
        }

        // 签名失效
        if (result && Math.abs((int) (System.currentTimeMillis() / 1000) - NumberUtils.toInt(timestamp)) > 7200) {
            msg = Result.error(ScResultEnum.SIGNINVALID.getKey(), ScResultEnum.SIGNINVALID.getMsg()).toString();
            result = false;
        }

        if (!result) {
            response.reset();
            response.setCharacterEncoding(Encoding.UTF8);
            response.setContentType("application/json");
            response.getWriter().write(msg);
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }
        return true;
    }
}
