package com.aebiz.app.accuser.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/8/10 16:16
 * 用户提现表
 */
@Table("sc_account_cashout")
public class Sc_account_cashout extends BaseModel implements Serializable {
    /**
     * 打款状态1：成功
     */
    public static final Integer CASHOUT_PAY_STATUS=1;
    /**
     * 提现类型0:服务机构
     */
    public static final Integer CASHOUT_TYPE_SERVICE=0;

    /**
     * 提现类型1：专家
     */
    public static final Integer CASHOUT_TYPE_EXPERT=1;
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
    @Comment("提现金额")
    private Integer money;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 1)
    @Comment("提现类型")
    private Integer type;//服务机构或专家

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 200)
    @Comment("账户名")
    private String accname;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 200)
    @Comment("开户行")
    private String accyh;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    @Comment("帐户号")
    private String acc;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("申请时间")
    private String apply_at;

    @Column
    @ColDefine(type = ColType.INT, width = 1)
    @Comment("支付状态")
    private Integer pay_status;//打款状态：1：成功

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("支付时间")
    private String pay_at;//确认打款时间

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("支付处理人")
    private String pay_by;//确认打款人

    @Column
    @ColDefine(type = ColType.INT, width = 1)
    @Comment("审核状态")
    private Integer check_status;//StatusEnum

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("审核时间")
    private String check_at;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("审核人")
    private String check_by;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Comment("审核描述")
    private String check_note;

    @One(field = "account_id")
    private Sc_account_user scAccountUser;

    public String getCheck_note() {
        return check_note;
    }

    public void setCheck_note(String check_note) {
        this.check_note = check_note;
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
    public Sc_account_user getScAccountUser() {
        return scAccountUser;
    }

    public void setScAccountUser(Sc_account_user scAccountUser) {
        this.scAccountUser = scAccountUser;
    }
}
