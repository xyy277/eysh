package com.aebiz.app.cms.modules.models;

import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * 访问日志
 * Created by zyang on 2017/8/2.
 */
@Table("cms_access_record")
public class Cms_access_record implements Serializable {
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("站点标识")
    private String siteId;
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("用户唯一标识")
    private String vid;
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    @Comment("用户ip")
    private String vip;
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    @Comment("域名")
    private String domain_;
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 500)
    @Comment("访问的路径")
    private String path;
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 500)
    @Comment("页面标题")
    private String title;
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("文章id")
    private String artId;
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 50)
    @Comment("客户端系统")
    private String os;
    @Column
    @ColDefine(type = ColType.INT, width = 1)
    @Comment("客户端系统类型")
    private Integer ostype;
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 50)
    @Comment("客户端浏览器")
    private String browser;
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 500)
    @Comment("来源")
    private String referrer;
    @Column
    @ColDefine(type = ColType.INT, width = 20)
    @Comment("访问时间")
    private Long atime;
    @Column
    @ColDefine(type = ColType.INT, width = 20)
    @Comment("该页面停留时间")
    private Long duration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getDomain_() {
        return domain_;
    }

    public void setDomain_(String domain_) {
        this.domain_ = domain_;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Integer getOstype() {
        return ostype;
    }

    public void setOstype(Integer ostype) {
        this.ostype = ostype;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public Long getAtime() {
        return atime;
    }

    public void setAtime(Long atime) {
        this.atime = atime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
