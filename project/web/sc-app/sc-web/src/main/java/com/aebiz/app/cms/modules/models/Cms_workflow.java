package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/10 11:52
 * 工作流管理
 */
@Table("cms_workflow")
public class Cms_workflow extends BaseModel implements Serializable {
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
    @Column
    @Comment("工作流名称")
    @ColDefine(type = ColType.VARCHAR, width = 30)
    private String name;
    @Column
    @Comment("工作流描述")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String note;
    @Column
    @Comment("排序字段")
    @Prev({
            @SQL(db= DB.MYSQL,value = "SELECT IFNULL(MAX(location),0)+1 FROM cms_workflow"),
            @SQL(db= DB.ORACLE,value = "SELECT COALESCE(MAX(location),0)+1 FROM cms_workflow"),
            @SQL(db= DB.DM,value = "SELECT COALESCE(MAX(location),0)+1 FROM cms_workflow")
    })
    private int location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
