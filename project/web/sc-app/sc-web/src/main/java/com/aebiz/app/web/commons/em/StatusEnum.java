package com.aebiz.app.web.commons.em;

import com.aebiz.baseframework.base.Message;

/**
 * @date: 2017/8/11 9:51
 * (0 待同步 1 已同步 )
 */
public enum StatusEnum {
    WAIT("0",Message.getMessage("globals.syncstatus.wait")),
    PASS("1", Message.getMessage("globals.syncstatus.pass"));
    //NOPASS("2", Message.getMessage("globals.syncstatus.nopass"));
    private String key;
    private String value;

    private StatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
