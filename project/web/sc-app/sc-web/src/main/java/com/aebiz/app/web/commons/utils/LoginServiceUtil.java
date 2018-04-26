package com.aebiz.app.web.commons.utils;

import com.aebiz.commons.utils.SpringUtil;
import org.nutz.lang.Strings;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: jxx
 * @date: 2017/8/29 10:51
 * 服务机构登录，获取登录信息公共方法
 */
@Component
public class LoginServiceUtil {

    /**
     * 获取登录的账户id(sc_account_user)
     * @return
     */
    public static String getServiceAccountId() {
        try {
            HttpServletRequest request = SpringUtil.getRequest();
            if (request != null) {
                return Strings.sNull(request.getSession(true).getAttribute("accuserUid"));
            }
        } catch (Exception var1) {
            ;
        }

        return "";
    }

    /**
     * 获取当前登录的账户名称(sc_account_user)
     * @return
     */
    public static String getServiceAccountName() {
        try {
            HttpServletRequest request = SpringUtil.getRequest();
            if (request != null) {
                return Strings.sNull(request.getSession(true).getAttribute("accuserUsername"));
            }
        } catch (Exception var1) {
            ;
        }

        return "";
    }

    /**
     * 获取当前登录人所在的服务机构id(sc_service_org_info)
     * @return
     */
    public static String getServiceOrgId() {
        try {
            HttpServletRequest request = SpringUtil.getRequest();
            if (request != null) {
                return Strings.sNull(request.getSession(true).getAttribute("tableId"));
            }
        } catch (Exception var1) {
            ;
        }

        return "";
    }
    /**
     * 获取当前登录人的类型(专家或服务机构)类型详见 Sc_account_link
     * @return
     */
    public static String getServiceType() {
        try {
            HttpServletRequest request = SpringUtil.getRequest();
            if (request != null) {
                return Strings.sNull(request.getSession(true).getAttribute("serviceType"));
            }
        } catch (Exception var1) {
            ;
        }

        return "";
    }
}
