package com.aebiz.app.accuser.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: wangcx
 * @date: 2017/9/4 17:07
 * 提现支付凭证图片
 */
@Table("Sc_account_cashout_picture")
public class Sc_account_cashout_picture extends BaseModel implements Serializable {
    @Column
    @Comment("提现记录单id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String account_cashout_id;

    @Column
    @Comment("排序")
    @ColDefine(type =ColType.INT, width = 32)
    private Integer priority;

    @Column
    @Comment("图片")
    @ColDefine(type = ColType.VARCHAR, width = 150)
    private String img;

    public String getAccount_cashout_id() {
        return account_cashout_id;
    }

    public void setAccount_cashout_id(String account_cashout_id) {
        this.account_cashout_id = account_cashout_id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
