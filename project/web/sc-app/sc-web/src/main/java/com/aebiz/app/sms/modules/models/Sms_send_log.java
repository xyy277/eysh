package com.aebiz.app.sms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by zzhu on 2017/9/5.
 */
@Table("sms_send_log_${id}")
public class Sms_send_log extends BaseModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("发送方")
    @ColDefine(type = ColType.VARCHAR,width =50)
    private String send_person;

    @Column
    @Comment("接收方")
    @ColDefine(type = ColType.VARCHAR,width =50)
    private String receive_person;

    @Column
    @Comment("模板code")
    @ColDefine(type = ColType.VARCHAR,width =50)
    private String mould_code;


    @Column
    @Comment("发送内容")
    @ColDefine(type = ColType.VARCHAR,width =500)
    private String send_msg;

    private String send_key;//需要替换的key
    private String send_value;//替换的value


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSend_person() {
        return send_person;
    }

    public void setSend_person(String send_person) {
        this.send_person = send_person;
    }

    public String getReceive_person() {
        return receive_person;
    }

    public void setReceive_person(String receive_person) {
        this.receive_person = receive_person;
    }

    public String getMould_code() {
        return mould_code;
    }

    public void setMould_code(String mould_code) {
        this.mould_code = mould_code;
    }

    public String getSend_msg() {
        return send_msg;
    }

    public void setSend_msg(String send_msg) {
        this.send_msg = send_msg;
    }

    public String getSend_key() {
        return send_key;
    }

    public void setSend_key(String send_key) {
        this.send_key = send_key;
    }

    public String getSend_value() {
        return send_value;
    }

    public void setSend_value(String send_value) {
        this.send_value = send_value;
    }
}
