package com.aebiz.app.accuser.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/8/10 15:56
 * 用户资金账户表.
 */
@Table("sc_account_cash")
public class Sc_account_cash extends BaseModel implements Serializable{
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
    @ColDefine(type = ColType.INT, width = 32)
    @Comment("账户余额")
    @Default("0")
    private Integer money;

    @Column
    @ColDefine(type = ColType.INT, width = 32)
    @Comment("冻结金额")
    @Default("0")
    private Integer freeze_money;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 50)
    @Comment("银行卡号")
    private String bank_card;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    @Comment("持卡人或者企业信息")
    private String cardholder;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 400)
    @Comment("银行名称")
    private String bank_name;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 400)
    @Comment("开户行信息")
    private String bank_information;

    @One(field = "account_id")
    private Sc_account_user scAccountUser;

    public Sc_account_user getScAccountUser() {
        return scAccountUser;
    }

    public void setScAccountUser(Sc_account_user scAccountUser) {
        this.scAccountUser = scAccountUser;
    }

    public String getBank_card() {
        return bank_card;
    }

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_information() {
        return bank_information;
    }

    public void setBank_information(String bank_information) {
        this.bank_information = bank_information;
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

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getFreeze_money() {
        return freeze_money;
    }

    public void setFreeze_money(Integer freeze_money) {
        this.freeze_money = freeze_money;
    }
}
