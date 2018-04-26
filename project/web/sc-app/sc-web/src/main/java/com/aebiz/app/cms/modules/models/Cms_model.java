package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/7 15:51
 * 模型管理
 */
@Table("cms_model")
public class Cms_model extends BaseModel implements Serializable{
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("模型名称")
    @ColDefine(type = ColType.VARCHAR, width = 80)
    private String model_name;

    @Column
    @Comment("栏目模板前缀")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String tpl_channel_perfix;
    @Column
    @Comment("内容模板前缀")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String tpl_content_perfix;
    @Column
    @Comment("栏目标题图宽")
    @ColDefine(type = ColType.INT, width = 10)
    private Integer title_img_width;
    @Column
    @Comment("栏目标题图高")
    @ColDefine(type = ColType.INT, width = 10)
    private Integer title_img_height;
    @Column
    @Comment("栏目内容图宽")
    @ColDefine(type = ColType.INT, width = 10)
    private Integer content_img_width;
    @Column
    @Comment("栏目内容图高")
    @ColDefine(type =ColType.INT, width = 10)
    private Integer content_img_height;
    @Column
    @Comment("排序字段")
    @Prev({
            @SQL(db= DB.MYSQL,value = "SELECT IFNULL(MAX(location),0)+1 FROM cms_model"),
            @SQL(db= DB.ORACLE,value = "SELECT COALESCE(MAX(location),0)+1 FROM cms_model"),
            @SQL(db= DB.DM,value = "SELECT COALESCE(MAX(location),0)+1 FROM cms_model")
    })
    private Integer location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getTpl_channel_perfix() {
        return tpl_channel_perfix;
    }

    public void setTpl_channel_perfix(String tpl_channel_perfix) {
        this.tpl_channel_perfix = tpl_channel_perfix;
    }

    public String getTpl_content_perfix() {
        return tpl_content_perfix;
    }

    public void setTpl_content_perfix(String tpl_content_perfix) {
        this.tpl_content_perfix = tpl_content_perfix;
    }

    public Integer getTitle_img_width() {
        return title_img_width;
    }

    public void setTitle_img_width(Integer title_img_width) {
        this.title_img_width = title_img_width;
    }

    public Integer getTitle_img_height() {
        return title_img_height;
    }

    public void setTitle_img_height(Integer title_img_height) {
        this.title_img_height = title_img_height;
    }

    public Integer getContent_img_width() {
        return content_img_width;
    }

    public void setContent_img_width(Integer content_img_width) {
        this.content_img_width = content_img_width;
    }

    public Integer getContent_img_height() {
        return content_img_height;
    }

    public void setContent_img_height(Integer content_img_height) {
        this.content_img_height = content_img_height;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }
}
