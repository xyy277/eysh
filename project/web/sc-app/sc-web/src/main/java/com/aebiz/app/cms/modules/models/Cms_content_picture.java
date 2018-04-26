package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/18 9:51
 * 图片集
 */
@Table("cms_content_picture")
public class Cms_content_picture extends BaseModel implements Serializable {
    @Column
    @Comment("内容id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String content_id;
    @Column
    @Comment("排序")
    @ColDefine(type =ColType.INT, width = 32)
    private Integer priority;
    @Column
    @Comment("图片")
    @ColDefine(type = ColType.VARCHAR, width = 150)
    private String img;

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
