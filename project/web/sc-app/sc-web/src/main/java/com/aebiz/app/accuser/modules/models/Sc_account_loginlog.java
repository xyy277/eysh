package com.aebiz.app.accuser.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/8/10 15:51
 * 用户登录日志表
 */
@Table("sc_account_loginlog")
public class Sc_account_loginlog extends BaseModel implements Serializable{
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("帐号ID")
    private String account_id;

    @Column
    @Prev(els = @EL("$me.now()"))
    @ColDefine(type = ColType.INT)
    @Comment("登录时间")
    private Integer login_at;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 50)
    @Comment("登录IP")
    private String login_ip;

    @Column
    @Comment("浏览器版本")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String client_browser;

    @Column
    @Comment("客户端类型")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String client_type;//pc mac android  ios

    @Column
    @Comment("客户端名称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String client_name;

    @Column
    @Comment("登录类型")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String login_type;//startup 创业者、company 企业、 service 登录服务机构、expert 专家

    public String getClient_browser() {
        return client_browser;
    }

    public void setClient_browser(String client_browser) {
        this.client_browser = client_browser;
    }

    public String getClient_type() {
        return client_type;
    }

    public void setClient_type(String client_type) {
        this.client_type = client_type;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Integer getLogin_at() {
        return login_at;
    }

    public void setLogin_at(Integer login_at) {
        this.login_at = login_at;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

}
