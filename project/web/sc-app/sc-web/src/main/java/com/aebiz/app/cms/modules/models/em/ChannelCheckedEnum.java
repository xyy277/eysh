package com.aebiz.app.cms.modules.models.em;

import com.aebiz.baseframework.base.Message;

/**
 * @author: jxx
 * @date: 2017/8/2 16:29
 * 栏目管理
 * 审核后操作 1：不能修改删除 2：修改后退回 3：修改后不变
 */
public enum ChannelCheckedEnum {
    MODIFYDELETE("1", Message.getMessage("channel.enum.checked.modifydelete")),
    RETURN("2", Message.getMessage("channel.enum.checked.return")),
    UNCHANGED("3", Message.getMessage("channel.enum.checked.unchanged"));

    private ChannelCheckedEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(String key) {
        for (ChannelCheckedEnum ft : ChannelCheckedEnum.values()) {
            if (key == ft.key) {
                return ft.value;
            }
        }
        return "";
    }

    private String key;

    private String value;

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
