package com.aebiz.app.wx.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by roy on 2017/9/5.
 */
@Table("wx_sc_news")
public class Wx_sc_news extends BaseModel implements Serializable{
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("标题")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String title;

    @Column
    @Comment("作者")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    private String author;

    @Column
    @Comment("原地址")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String content_source_url;

    @Column
    @Comment("缩略图")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String thumb_media;

    @Column
    @Comment("图文内容")
    @ColDefine(type = ColType.TEXT)
    private String content;

    @Column
    @Comment("简述")
    @ColDefine(type = ColType.TEXT)
    private String digest;

    @Column
    @Comment("显示封面")
    @ColDefine(type = ColType.VARCHAR,width = 500)
    protected String show_cover_pic;

    @Column
    @Comment("状态")
    @ColDefine(type = ColType.INT, width = 8)
    private Integer status;

    @Column
    @Comment("appid")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String appid;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent_source_url() {
        return content_source_url;
    }

    public void setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
    }

    public String getThumb_media() {
        return thumb_media;
    }

    public void setThumb_media(String thumb_media) {
        this.thumb_media = thumb_media;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getShow_cover_pic() {
        return show_cover_pic;
    }

    public void setShow_cover_pic(String show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
