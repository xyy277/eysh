package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/14 11:11
 * 专题管理
 */
@Table("cms_topic")
public class Cms_topic extends BaseModel implements Serializable{
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
    @Column
    @Comment("站点id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String site_id;
    @Column
    @Comment("关联栏目ids")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String channelIds;
    @Column
    @Comment("名称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String name;
    @Column
    @Comment("简称")
    @ColDefine(type = ColType.VARCHAR, width = 80)
    private String shortName;
    @Column
    @Comment("关键字")
    @ColDefine(type = ColType.VARCHAR, width = 80)
    private String keywords;
    @Column
    @Comment("描述")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String description;
    @Column
    @Comment("是否推荐")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String recommend;
    @Column
    @Comment("排序顺序")
    @Prev({
            @SQL(db= DB.MYSQL,value = "SELECT IFNULL(MAX(priority),0)+1 FROM cms_topic"),
            @SQL(db= DB.ORACLE,value = "SELECT COALESCE(MAX(priority),0)+1 FROM cms_topic"),
            @SQL(db= DB.DM,value = "SELECT COALESCE(MAX(location),0)+1 FROM cms_topic")
    })
    private Integer priority;
    @Column
    @Comment("模版")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String tplContent;

    @Column
    @Comment("标题图")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String title_img;
    @Column
    @Comment("内容图")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String content_img;
    @Column
    @Comment("标题图宽")
    @ColDefine(type = ColType.INT, width = 10)
    private Integer title_img_width;
    @Column
    @Comment("标题图高")
    @ColDefine(type = ColType.INT, width = 10)
    private Integer title_img_height;
    @Column
    @Comment("内容图宽")
    @ColDefine(type =ColType.INT, width = 10)
    private Integer content_img_width;
    @Column
    @Comment("内容图高")
    @ColDefine(type = ColType.INT, width = 10)
    private Integer content_img_height;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTplContent() {
        return tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent;
    }

    public String getTitle_img() {
        return title_img;
    }

    public void setTitle_img(String title_img) {
        this.title_img = title_img;
    }

    public String getContent_img() {
        return content_img;
    }

    public void setContent_img(String content_img) {
        this.content_img = content_img;
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

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getChannelIds() {
        return channelIds;
    }

    public void setChannelIds(String channelIds) {
        this.channelIds = channelIds;
    }
}
