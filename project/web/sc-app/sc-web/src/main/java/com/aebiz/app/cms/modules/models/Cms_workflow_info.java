package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/10 11:52
 * 工作流管理子表
 */
@Table("cms_workflow_info")
public class Cms_workflow_info extends BaseModel implements Serializable{
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
    @Column
    @Comment("工作流id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String flowid;
    @Column
    @Comment("步骤")
    @ColDefine(type = ColType.INT)
    private Integer step;
    @Column
    @Comment("角色id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String roleid;
    @Column
    @Comment("类型")
    @ColDefine(type =ColType.INT, width = 2)
    private Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlowid() {
        return flowid;
    }

    public void setFlowid(String flowid) {
        this.flowid = flowid;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
