package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/8 14:49
 * 栏目、站点、角色关联表
 */
@Table("cms_channel_role")
@PK({"channel_id", "site_id", "role_id"})
public class Cms_channel_role extends BaseModel implements Serializable{
    @Column
    @Comment("栏目id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String channel_id;
    @Column
    @Comment("站点id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String site_id;
    @Column
    @Comment("角色id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String role_id;
    /**
     * 角色关联按钮权限
     */
    @Column
    @Comment("新增 Y/N")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String has_add;
    @Column
    @Comment("修改 Y/N")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String has_update;
    @Column
    @Comment("删除 Y/N")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String has_del;
    @Column
    @Comment("移动 Y/N")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String has_move;
    @Column
    @Comment("审核 Y/N")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String has_check;
    @Column
    @Comment("发布 Y/N")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String has_static;
    @Column
    @Comment("复制 Y/N")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String has_push;
    @Column
    @Comment("引用 Y/N")
    @ColDefine(type = ColType.VARCHAR, width = 2)
    private String has_share;
    /**
     * 栏目审核后权限   1：栏目设置为审核后“不能修改删除”
     */
    private String checked;

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getHas_add() {
        return has_add;
    }

    public void setHas_add(String has_add) {
        this.has_add = has_add;
    }

    public String getHas_update() {
        return has_update;
    }

    public void setHas_update(String has_update) {
        this.has_update = has_update;
    }

    public String getHas_del() {
        return has_del;
    }

    public void setHas_del(String has_del) {
        this.has_del = has_del;
    }

    public String getHas_move() {
        return has_move;
    }

    public void setHas_move(String has_move) {
        this.has_move = has_move;
    }

    public String getHas_check() {
        return has_check;
    }

    public void setHas_check(String has_check) {
        this.has_check = has_check;
    }

    public String getHas_static() {
        return has_static;
    }

    public void setHas_static(String has_static) {
        this.has_static = has_static;
    }

    public String getHas_push() {
        return has_push;
    }

    public void setHas_push(String has_push) {
        this.has_push = has_push;
    }

    public String getHas_share() {
        return has_share;
    }

    public void setHas_share(String has_share) {
        this.has_share = has_share;
    }
}
