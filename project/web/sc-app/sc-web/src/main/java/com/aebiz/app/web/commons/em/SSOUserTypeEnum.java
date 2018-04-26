package com.aebiz.app.web.commons.em;

import org.apache.commons.lang3.StringUtils;

/**
 * 单点登陆用户的类型
 * Created by zyang on 2017/9/22.
 */
public enum SSOUserTypeEnum {
    // 管理员、市级管理员、区县管理员、服务机构、企业、创业者、专家
    ADMIN("01"), SJADMIN("0101"), QXADMIN("0102"), SVR("02"), ORG("03"), STARTUP("04"), EXPERT("05");

    SSOUserTypeEnum(String val) {
        this.value = val;
    }

    String value;

    /**
     * 是不是该角色
     *
     * @param val
     * @return
     */
    public boolean match(String val) {
        if (val == null || val.isEmpty()) {
            return false;
        }
        return this.value.equalsIgnoreCase(val);
    }

    public String getValue() {
        return this.value;
    }

    /**
     * 是否是管理员
     *
     * @param type
     * @return
     */
    public static boolean isAdmin(String type) {
        return !StringUtils.isBlank(type) && (ADMIN.match(type) || SJADMIN.match(type) || QXADMIN.match(type));
    }

    /**
     * 是否是会员
     *
     * @param type
     * @return
     */
    public static boolean isMemmber(String type) {
        return !StringUtils.isBlank(type) && (SVR.match(type) || ORG.match(type) || STARTUP.match(type) || EXPERT.match(type));
    }

}
