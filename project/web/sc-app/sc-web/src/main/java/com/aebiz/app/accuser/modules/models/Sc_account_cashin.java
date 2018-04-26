package com.aebiz.app.accuser.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/8/10 16:07
 * 用户充值表
 */
@Table("sc_account_cashin")
public class Sc_account_cashin extends BaseModel implements Serializable {
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
    @Comment("充值金额")
    private Integer money;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("充值方式")
    private Integer type;////PayTypeEnum

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    @Comment("账户名")
    private String accname;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    @Comment("开户行")
    private String accyh;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    @Comment("帐户号")
    private String acc;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 50)
    @Comment("交易流水号")
    private String trade_no;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("申请时间")
    private String apply_at;

    @Column
    @ColDefine(type = ColType.INT, width = 1)
    @Comment("支付状态")
    private Integer pay_status;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("支付时间")
    private String pay_at;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("支付处理人")
    private String pay_by;

    @Column
    @ColDefine(type = ColType.INT, width = 1)
    @Comment("处理状态")
    private Integer check_status;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("处理时间")
    private String check_at;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("处理人")
    private String check_by;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getAccyh() {
        return accyh;
    }

    public void setAccyh(String accyh) {
        this.accyh = accyh;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getApply_at() {
        return apply_at;
    }

    public void setApply_at(String apply_at) {
        this.apply_at = apply_at;
    }

    public Integer getPay_status() {
        return pay_status;
    }

    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
    }

    public String getPay_at() {
        return pay_at;
    }

    public void setPay_at(String pay_at) {
        this.pay_at = pay_at;
    }

    public String getPay_by() {
        return pay_by;
    }

    public void setPay_by(String pay_by) {
        this.pay_by = pay_by;
    }

    public Integer getCheck_status() {
        return check_status;
    }

    public void setCheck_status(Integer check_status) {
        this.check_status = check_status;
    }

    public String getCheck_at() {
        return check_at;
    }

    public void setCheck_at(String check_at) {
        this.check_at = check_at;
    }

    public String getCheck_by() {
        return check_by;
    }

    public void setCheck_by(String check_by) {
        this.check_by = check_by;
    }
}
