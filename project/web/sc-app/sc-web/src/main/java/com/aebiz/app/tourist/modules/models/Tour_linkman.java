package com.aebiz.app.tourist.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by aj907 on 2018/3/25.
 */
@Table("tour_linkman")
public class Tour_linkman extends BaseModel implements Serializable{

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 20)
    @Comment("联系号码")
    private String phone;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    @Comment("联系邮箱")
    private String email;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 20)
    @Comment("联系QQ")
    private String qq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
