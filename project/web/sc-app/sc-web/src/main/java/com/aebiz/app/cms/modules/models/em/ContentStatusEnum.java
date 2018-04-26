package com.aebiz.app.cms.modules.models.em;


import com.aebiz.baseframework.base.Message;

/**
 * @author: jxx
 * @date: 2017/8/2 17:03
 * 文章管理
 * 审核状态： 0：草稿 1：待审 2：已审 3：已终审 4：退回
 */
public enum  ContentStatusEnum {
    DRAFTS(0,Message.getMessage("channel.enum.status.drafts")),
    PENDING(1,Message.getMessage("channel.enum.status.pending")),
    EXAMINED(2,Message.getMessage("channel.enum.status.examined")),
    FINALIZED(3,Message.getMessage("channel.enum.status.finalized")),
    RETURN(4,Message.getMessage("channel.enum.status.return"));

    public static String getValue(Integer key) {
        for (ContentStatusEnum ft : ContentStatusEnum.values()) {
            if (key == ft.key) {
                return ft.value;
            }
        }
        return "";
    }

    private Integer key;
    private String value;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private ContentStatusEnum(Integer key, String value) {
        this.key=key;
        this.value=value;
    }
}
