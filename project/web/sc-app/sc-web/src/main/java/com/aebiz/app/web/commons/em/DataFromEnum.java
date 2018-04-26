package com.aebiz.app.web.commons.em;

import com.aebiz.baseframework.base.Message;

/**
 * @date: 2017/8/11 9:51
 * (1 页面新增 2 Excel导入 3其他 )
 */
public enum DataFromEnum {
    FRONT("1",Message.getMessage("globals.datafrom.front")),
    EXCEL("2", Message.getMessage("globals.datafrom.excel")),
    OTHER("3", Message.getMessage("globals.datafrom.other"));
    private String key;
    private String value;

    private DataFromEnum(String key, String value) {
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
