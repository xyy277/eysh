package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/21 21:07
 * 文章日志表
 */
@Table("cms_log")
public class Cms_log extends BaseModel implements Serializable {
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
    @Comment("用户id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String user_id;
    @Column
    @Comment("")
    @ColDefine(type =ColType.INT, width = 32)
    private Integer category;
    @Column
    @Comment("操作时间")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String log_time;
    @Column
    @Comment("用户ip")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String ip;
    @Column
    @Comment("操作url")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    private String url;
    @Column
    @Comment("内容标题")
    @ColDefine(type = ColType.VARCHAR, width = 225)
    private String title;
    @Column
    @Comment("修改内容")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    private String content;
    @Column
    @Comment("内容id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String content_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getLog_time() {
        return log_time;
    }

    public void setLog_time(String log_time) {
        this.log_time = log_time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }
}
