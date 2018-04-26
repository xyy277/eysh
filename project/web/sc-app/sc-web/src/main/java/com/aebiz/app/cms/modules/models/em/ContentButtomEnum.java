package com.aebiz.app.cms.modules.models.em;

import com.aebiz.baseframework.base.Message;

/**
 * @author: jxx
 * @date: 2017/8/4 10:49
 * 文章按钮权限控制：BtnAdd：新增，BtnUpdate：修改，BtnDel：删除，BtnMove：移动，BtnCheck：审核，BtnStatic：发布，BtnPush：推送，BtnShare：引用(暂未用到)
 *
 */

public enum ContentButtomEnum {
    BTNADD("BtnAdd", Message.getMessage("content.buttom.btnadd")),
    BTNUPDATE("BtnUpdate",Message.getMessage("content.buttom.btnupdate")),
    BTNDEL("BtnDel",Message.getMessage("content.buttom.btndel")),
    BTNMOVE("BtnMove",Message.getMessage("content.buttom.btnmove")),
    BTNCHECK("BtnCheck",Message.getMessage("content.buttom.btncheck")),
    BTNSTATIC("BtnStatic",Message.getMessage("content.buttom.btnstatic")),
    BTNPUSH("BtnPush",Message.getMessage("content.buttom.btnpush"));

    private String key;

    private String value;

    public static String getValue(Integer key) {
        for (ContentButtomEnum ft : ContentButtomEnum.values()) {
            if (key.equals(ft.key)) {
                return ft.value;
            }
        }
        return "";
    }
    ContentButtomEnum(String key, String value) {
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
