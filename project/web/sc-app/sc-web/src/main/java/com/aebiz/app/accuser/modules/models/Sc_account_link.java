package com.aebiz.app.accuser.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author: jxx
 * @date: 2017/8/10 14:41
 * 帐户关联表(服务机构、企业、创业者、专家)
 */
@Table("sc_account_link")
public class Sc_account_link extends BaseModel implements Serializable{

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("账户id")
    private String account_id;

    @Column
    @ColDefine(type = ColType.INT, width = 1)
    @Comment("用户类型")
    private Integer type;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("关联表id")
    private String table_id;//服务机构基本信息表、企业基本信息表、专家及创业者

    @Column
    @ColDefine(type = ColType.INT, width = 1)
    @Comment("是否主帐号")
    private Integer basic;

    @Column
    @Comment("皮肤样式")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String loginTheme;

    @Column
    private boolean loginSidebar;

    @Column
    private boolean loginBoxed;

    @Column
    private boolean loginScroll;

    @Column
    private boolean loginPjax;

    @One(target = Sc_account_user.class, field = "account_id")
    private Sc_account_user scAccountUser;

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBasic() {
        return basic;
    }

    public void setBasic(Integer basic) {
        this.basic = basic;
    }

    public Sc_account_user getScAccountUser() {
        return scAccountUser;
    }

    public void setScAccountUser(Sc_account_user scAccountUser) {
        this.scAccountUser = scAccountUser;
    }

    public String getLoginTheme() {
        return loginTheme;
    }

    public void setLoginTheme(String loginTheme) {
        this.loginTheme = loginTheme;
    }

    public boolean isLoginSidebar() {
        return loginSidebar;
    }

    public void setLoginSidebar(boolean loginSidebar) {
        this.loginSidebar = loginSidebar;
    }

    public boolean isLoginBoxed() {
        return loginBoxed;
    }

    public void setLoginBoxed(boolean loginBoxed) {
        this.loginBoxed = loginBoxed;
    }

    public boolean isLoginScroll() {
        return loginScroll;
    }

    public void setLoginScroll(boolean loginScroll) {
        this.loginScroll = loginScroll;
    }

    public boolean isLoginPjax() {
        return loginPjax;
    }

    public void setLoginPjax(boolean loginPjax) {
        this.loginPjax = loginPjax;
    }
}
