package com.aebiz.app.tourist.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by aj907 on 2018/3/21.
 */
@Table("tour_user_concern")
public class Tour_user_concern extends BaseModel implements Serializable{

    @Column
    @Comment("用户id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String user_id;

    @Column
    @Comment("关注id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String concern_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getConcern_id() {
        return concern_id;
    }

    public void setConcern_id(String concern_id) {
        this.concern_id = concern_id;
    }
}
