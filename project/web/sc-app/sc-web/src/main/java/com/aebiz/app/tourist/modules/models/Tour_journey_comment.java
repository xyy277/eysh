package com.aebiz.app.tourist.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by aj907 on 2018/3/21.
 */
@Table("tour_journey_comment")
public class Tour_journey_comment extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("游记id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String journey_id;

    @Column
    @Comment("评论内容")
    @ColDefine(type = ColType.VARCHAR, width = 200)
    private String comment;

    @Column
    @Comment("审核状态")
    @ColDefine(type = ColType.INT)
    private Integer status;//0：审核不通过，1：审核通过

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJourney_id() {
        return journey_id;
    }

    public void setJourney_id(String journey_id) {
        this.journey_id = journey_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
