package com.aebiz.app.web.commons.em;

import com.aebiz.baseframework.base.Message;

/**
 * @author: tony
 * @date: 2017/8/15 10:45
 * 即时汇总接口返回信息 消息编码 -1:表示系统错误(请联系管理员), 0:处理成功, 1:APPID不存在, 2:APPID被禁用
 * 3:签名失败, 4:签名失效, 5:参数错误, 99:其他错误
 */
public enum ScResultEnum {
    SYSERROR(-1, Message.getMessage("sc.result.code.syserror")),
    UNBINDERROR(-2, Message.getMessage("sc.result.code.unbind")),
    MOULDCODEONEXIST(-4, Message.getMessage("sc.result.code.mould_code_nonexist")),
    NOVM(-3, Message.getMessage("sc.result.code.novm")),
    SUCCESS(0, Message.getMessage("sc.result.code.success")),
    APPIDNONEXIST(1, Message.getMessage("sc.result.code.appid_nonexist")),
    APPIDDISABLE(2, Message.getMessage("sc.result.code.appid_disable")),
    SIGNERROR(3, Message.getMessage("sc.result.code.signerror")),
    SIGNINVALID(4, Message.getMessage("sc.result.code.signinvalid")),
    PARAMSERROR(5, Message.getMessage("sc.result.code.paramserror")),
    OTHERERROR(99, Message.getMessage("sc.result.code.othererror"));

    ScResultEnum(Integer key, String msg) {
        this.key = key;
        this.msg = msg;
    }

    public static String getValue(Integer key) {
        for (ScResultEnum as : ScResultEnum.values()) {
            if (as.getKey() == key) {
                return as.getMsg();
            }
        }
        return "";
    }


    private Integer key;
    private String msg;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
