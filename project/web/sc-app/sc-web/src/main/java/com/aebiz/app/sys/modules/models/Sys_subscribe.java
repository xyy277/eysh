package com.aebiz.app.sys.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * 客户订阅表
 * Created by zxq on 2017/9/29.
 */
@Table("sys_subscribe")
public class Sys_subscribe extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("企业名称")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String org_name;

    @Column
    @Comment("邮箱地址")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String email;

    @Column
    @Comment("手机号码")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
