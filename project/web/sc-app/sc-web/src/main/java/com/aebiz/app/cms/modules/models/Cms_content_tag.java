package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/14 9:34
 * tag标签表
 */
@Table("cms_content_tag")
public class Cms_content_tag extends BaseModel implements Serializable {
    @Column
    @Comment("内容id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String content_id;
    @Column
    @Comment("tag标签id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String tag_id;
    @Column
    @Comment("顺序")
    @ColDefine(type = ColType.INT, width = 2)
    private Integer priority;

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
