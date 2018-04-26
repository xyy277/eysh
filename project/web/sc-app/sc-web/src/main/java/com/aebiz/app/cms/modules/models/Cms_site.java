package com.aebiz.app.cms.modules.models;

import com.aebiz.app.sys.modules.models.Sys_role;
import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.List;
/**
 * @author: jxx
 * @date: 2017/7/7 11:06
 * 站点表
 */
@Table("cms_site")
public class Cms_site extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;
    @Column
    @Comment("网站名称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String site_name;
    @Column
    @Comment("网站简称")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String site_sname;
    @Column
    @Comment("域名")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String site_domain;
    @Column
    @Comment("路径")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String site_path;
    @Column
    @Comment("样式路径")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String site_css;
    @Column
    @Comment("发布状态")
    @ColDefine(type = ColType.INT)
    private Integer is_static;
    @Column
    @Comment("排序字段")
    @Prev({
            @SQL(db= DB.MYSQL,value = "SELECT IFNULL(MAX(location),0)+1 FROM cms_model"),
            @SQL(db= DB.ORACLE,value = "SELECT COALESCE(MAX(location),0)+1 FROM cms_model"),
            @SQL(db= DB.DM,value = "SELECT COALESCE(MAX(location),0)+1 FROM cms_model")
    })
    private Integer location;

    @ManyMany(from = "siteId", relation = "cms_site_role", to = "roleId")
    protected List<Sys_role> menus;
    /**
     * 是否存在子节点 true:存在
     */
    private boolean hasChildren;

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getSite_sname() {
        return site_sname;
    }

    public void setSite_sname(String site_sname) {
        this.site_sname = site_sname;
    }

    public String getSite_domain() {
        return site_domain;
    }

    public void setSite_domain(String site_domain) {
        this.site_domain = site_domain;
    }

    public String getSite_path() {
        return site_path;
    }

    public void setSite_path(String site_path) {
        this.site_path = site_path;
    }

    public String getSite_css() {
        return site_css;
    }

    public void setSite_css(String site_css) {
        this.site_css = site_css;
    }

    public Integer getIs_static() {
        return is_static;
    }

    public void setIs_static(int is_static) {
        this.is_static = is_static;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }
}
