package com.aebiz.app.cms.modules.models.em;

import com.aebiz.baseframework.base.Message;

/**
 * @author: jxx
 * @date: 2017/8/2 17:33
 * 发布状态 0：待发布 1:已发布
 */
public enum CmsIsStaticEnum {
    RELEASED(0, Message.getMessage("cms.enum.static.released")),
    PUBLISHED(1, Message.getMessage("cms.enum.static.published"));

    public static String getValue(Integer  key) {
        for (CmsIsStaticEnum ft : CmsIsStaticEnum.values()) {
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

    private CmsIsStaticEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
