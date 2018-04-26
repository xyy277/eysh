package com.aebiz.app.sms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by zzhu on 2017/9/4.
 */
@Table("sms_mould_info")
public class  Sms_mould_info extends BaseModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("模板code")
    @ColDefine(type = ColType.VARCHAR,width =50)
    private String code;

    @Column
    @Comment("阿里大于模板code")
    @ColDefine(type = ColType.VARCHAR,width =50)
    private String ali_code;

    @Column
    @Comment("模板名称")
    @ColDefine(type = ColType.VARCHAR,width =30)
    private String name;

    @Column
    @Comment("模板类型")
    @ColDefine(type = ColType.VARCHAR,width =32)
    private String type;

    @Column
    @Comment("模板内容")
    @ColDefine(type = ColType.VARCHAR,width =500)
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAli_code() {
        return ali_code;
    }

    public void setAli_code(String ali_code) {
        this.ali_code = ali_code;
    }
}
