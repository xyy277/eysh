package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/17 23:02
 * To change this template use File | Settings | File Templates.
 */
@Table("cms_content_txt")
public class Cms_content_txt extends BaseModel implements Serializable {
    @Column
    @Comment("内容id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String content_id;
    @Column
    @Comment("内容")
    @ColDefine(type = ColType.TEXT)
    private String txt;

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
