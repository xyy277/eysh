package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by wangcx on 2017/9/9.
 * 通知公告
 */
@Table("sc_notice")
public class Sc_notice extends BaseModel implements Serializable {
    /**
     * 通知类型 1：企业
     */
    public static final Integer MEMBERTYPEORG=1;
    /**
     * 通知类型 2：创业者
     */
    public static final Integer MEMBERTYPESTARTUP=2;

    /**
     * 通知类型 3:服务机构
     */
    public static final Integer TYPESERVERORG=3;
    /**
     * 通知类型 4:专家
     */
    public static final Integer TYPEEXPERT=4;

    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("标题")
    @ColDefine(type = ColType.VARCHAR, width = 80)
    private String title;

    @Column
    @Comment("短标题")
    @ColDefine(type = ColType.VARCHAR, width = 60)
    private String short_title;

    @Column
    @Comment("内容摘要")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String description;

    @Column
    @Comment("发布者")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String author;

    @Column
    @Comment("标题图")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String picurl;

    @Column
    @Comment("添加时间")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String add_time;

    @Column
    @Comment("发布时间")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String pub_time;

    @Column
    @Comment("正文内容")
    @ColDefine(type = ColType.TEXT)
    private String content;

    @Column
    @ColDefine(type = ColType.INT, width = 1)
    @Comment("通知类型")
    private Integer type;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getShort_title() {return short_title;}

    public void setShort_title(String short_title) {this.short_title = short_title;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getAuthor() {return author;}

    public void setAuthor(String author) {this.author = author;}

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getAdd_time() {return add_time;}

    public void setAdd_time(String add_time) {this.add_time = add_time;}

    public String getPub_time() {return pub_time;}

    public void setPub_time(String pub_time) {this.pub_time = pub_time;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public Integer getType() {return type;}

    public void setType(Integer type) {this.type = type;}
}
