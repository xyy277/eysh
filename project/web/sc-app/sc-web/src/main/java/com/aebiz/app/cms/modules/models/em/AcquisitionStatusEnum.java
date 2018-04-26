package com.aebiz.app.cms.modules.models.em;

import com.aebiz.baseframework.base.Message;

/**
 * @author: jxx
 * @date: 2017/8/3 10:45
 * 数据采集 采集状态 1 表示开始采集 , 0 停止 , 2 暂停  , 3 继续
 */
public enum AcquisitionStatusEnum {
    START(1, Message.getMessage("acquisition.status.start")),
    STOP(0,Message.getMessage("acquisition.status.stop")),
    TIMEOUT(2,Message.getMessage("acquisition.status.timeout")),
    CARRYON(3,Message.getMessage("acquisition.status.carryon"));

    AcquisitionStatusEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
    public static String getKey(Integer key){
        for(AcquisitionStatusEnum as:AcquisitionStatusEnum.values()){
            if(as.getKey()==key){
                return as.getValue();
            }
        }
        return  "";
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
}
