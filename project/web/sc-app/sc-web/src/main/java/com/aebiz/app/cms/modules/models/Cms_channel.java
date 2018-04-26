package com.aebiz.app.cms.modules.models;

import java.io.Serializable;

import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.SQL;
import org.nutz.dao.entity.annotation.Table;
import com.aebiz.baseframework.base.model.BaseModel;
/**
 * @author: jxx
 * @date: 2017/7/7 11:06
 * 栏目表
 */
@Table("cms_channel")
public class Cms_channel extends BaseModel implements Serializable {
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
    @Comment("模型id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String model_id;
    @Column
    @Comment("栏目名称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String name;
    @Column
    @Comment("栏目路径")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String path;
    @Column
    @Comment("父级ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String parentId;
    @Column
    @Comment("meta标题")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String title;
    @Column
    @Comment("meta关键字")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String keywords;
    @Column
    @Comment("meta描述")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String description;
    @Column
    @Comment("栏目模板")
    @ColDefine(type = ColType.VARCHAR, width = 150)
    private String tpl_channel;
    @Column
    @Comment("是否在前台显示")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String is_display;
    @Column
    @Comment("发布状态")
    @ColDefine(type = ColType.INT)
    private Integer is_static;
    @Column
    @Comment("是否新窗口打开")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String blank;
    @Column
    @Comment("外部链接")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String linkurl;
    @Column
    @Comment("工作流id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String flowid;
    @Column
    @Comment("是否选中")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String checked;
    @Column
    @Comment("排序字段")
    @Prev({
            @SQL(db= DB.MYSQL,value = "SELECT IFNULL(MAX(location),0)+1 FROM cms_channel"),
            @SQL(db= DB.ORACLE,value = "SELECT COALESCE(MAX(location),0)+1 FROM cms_channel"),
            @SQL(db= DB.DM,value = "SELECT COALESCE(MAX(location),0)+1 FROM cms_channel")
    })
    private Integer location;
    @Column
    @Comment("有子节点")
    private boolean hasChildren;
    @Column
    @Comment("每页记录数")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String pagesize;

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTpl_channel() {
        return tpl_channel;
    }

    public void setTpl_channel(String tpl_channel) {
        this.tpl_channel = tpl_channel;
    }

    public String getIs_display() {
        return is_display;
    }

    public void setIs_display(String is_display) {
        this.is_display = is_display;
    }

    public Integer getIs_static() {
        return is_static;
    }

    public void setIs_static(int is_static) {
        this.is_static = is_static;
    }

    public String getBlank() {
        return blank;
    }

    public void setBlank(String blank) {
        this.blank = blank;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    public String getFlowid() {
        return flowid;
    }

    public void setFlowid(String flowid) {
        this.flowid = flowid;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }
}
