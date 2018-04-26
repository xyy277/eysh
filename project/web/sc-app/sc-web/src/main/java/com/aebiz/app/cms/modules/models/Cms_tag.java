package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/14 9:54
 * 内容标签
 */
@Table("cms_tag")
public class Cms_tag extends BaseModel implements Serializable{
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
    @Column
    @Comment("标签名称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String name;
    @Column
    @Comment("顺序")
    @ColDefine(type =ColType.INT, width = 30)
    private Integer counter;

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

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
