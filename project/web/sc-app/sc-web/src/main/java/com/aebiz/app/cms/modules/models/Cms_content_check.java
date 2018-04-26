package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/14 9:25
 * 内容审核表
 */
@Table("cms_content_check")
public class Cms_content_check extends BaseModel implements Serializable {
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
    @Column
    @Comment("内容id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String content_id;
    @Column
    @Comment("用户id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String user_id;
    @Column
    @Comment("工作流id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String flowid;
    @Column
    @Comment("审核步骤")
    @ColDefine(type =ColType.INT, width = 32)
    private Integer check_step;
    @Column
    @Comment("审核描述")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String check_opinion;
    @Column
    @Comment("是否通过")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String is_checked;
    @Column
    @Comment("审核人")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String reviewer;
    @Column
    @Comment("审核日期")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String check_date;
    @Column
    @Comment("审核状态1：通过")
    @ColDefine(type =ColType.INT, width = 2)
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFlowid() {
        return flowid;
    }

    public void setFlowid(String flowid) {
        this.flowid = flowid;
    }

    public Integer getCheck_step() {
        return check_step;
    }

    public void setCheck_step(Integer check_step) {
        this.check_step = check_step;
    }

    public String getCheck_opinion() {
        return check_opinion;
    }

    public void setCheck_opinion(String check_opinion) {
        this.check_opinion = check_opinion;
    }

    public String getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(String is_checked) {
        this.is_checked = is_checked;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
