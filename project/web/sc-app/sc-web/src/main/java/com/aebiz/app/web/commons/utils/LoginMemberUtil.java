package com.aebiz.app.web.commons.utils;

import com.aebiz.commons.utils.SpringUtil;
import org.nutz.lang.Strings;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: jxx
 * @date: 2017/8/29 10:51
 * 企业或创业者登录，获取登录信息公共方法
 */
@Component
public class LoginMemberUtil {

    /**
     * 获取登录的账户id(sc_account_user)
     * @return
     */
    public static String getMemberAccountId() {
        try {
            HttpServletRequest request = SpringUtil.getRequest();
            if (request != null) {
                return Strings.sNull(request.getSession(true).getAttribute("memberUid"));
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
    public static String getMemberAccountName() {
        try {
            HttpServletRequest request = SpringUtil.getRequest();
            if (request != null) {
                return Strings.sNull(request.getSession(true).getAttribute("memberUsername"));
            }
        } catch (Exception var1) {
            ;
        }

        return "";
    }

    /**
     * 获取当前登录人的基本信息id(Sc_expert_info、Sc_org_info)
     * @return
     */
    public static String getMemberInfoId() {
        try {
            HttpServletRequest request = SpringUtil.getRequest();
            if (request != null) {
                return Strings.sNull(request.getSession(true).getAttribute("memberTableId"));
            }
        } catch (Exception var1) {
            ;
        }

        return "";
    }
    /**
     * 获取当前登录人的类型(企业或创业者)类型详见 Sc_account_link
     * @return
     */
    public static String getMemberType() {
        try {
            HttpServletRequest request = SpringUtil.getRequest();
            if (request != null) {
                return Strings.sNull(request.getSession(true).getAttribute("memberType"));
            }
        } catch (Exception var1) {
            ;
        }

        return "";
    }
}
