package com.aebiz.app.sms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by zzhu on 2017/9/4.
 */
@Table("sms_tele_info")
public class Sms_tele_info extends BaseModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("虚拟号码")
    @ColDefine(type = ColType.VARCHAR,width =20)
    private String phone_no;

    @Column
    @Comment("绑定截止时间")
    @ColDefine(type = ColType.INT,width =32)
    private Integer end_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public Integer getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Integer end_time) {
        this.end_time = end_time;
    }
}
