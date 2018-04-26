package com.aebiz.app.sys.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;
import java.io.Serializable;

/**
 * 支付宝电脑支付接口
 * Created by bin on 2017/9/15.
 */

@Table("sys_alipay")
public class Sys_alipay extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("appid")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String appid;

    @Column
    @Comment("商户UID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String suid;

    @Column
    @Comment("支付宝交易号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String tradeNo;

    @Column
    @Comment("订单号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    //客户端必须唯一
    private String outTradeno;

    @Column
    @Comment("交易金额")
    @ColDefine(type = ColType.VARCHAR,width = 16)
    private String totalAmount;

    @Column
    @Comment("收到金额")
    @ColDefine(type = ColType.VARCHAR,width = 16)
    private String recieveAmount;

    @Column
    @Comment("交易状态")
    @ColDefine(type = ColType.VARCHAR,width = 16)
    private String tradestatus;

    @Column
    @Comment("购买商品")
    @ColDefine(type = ColType.VARCHAR,width = 32)
    private String subject;

    @Column
    @Comment("购买商品详情")
    @ColDefine(type = ColType.VARCHAR,width = 256)
    private String body;

    @Column
    @Comment("成功返回")
    @ColDefine(type = ColType.VARCHAR,width = 256)
    private String returnurl;

    @Column
    @Comment("回调参数")
    @ColDefine(type = ColType.VARCHAR,width = 256)
    private String notifyurl;

    @Column
    @Comment("创建时间")
    @ColDefine(type = ColType.VARCHAR,width = 16)
    private String cj_date;

    @Column
    @Comment("付款时间")
    @ColDefine(type = ColType.VARCHAR,width = 16)
    private String fukuan_date;

    @Column
    @Comment("返回时间")
    @ColDefine(type = ColType.VARCHAR,width = 16)
    private String fanhui_date;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeno() {
        return outTradeno;
    }

    public void setOutTradeno(String outTradeno) {
        this.outTradeno = outTradeno;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReturnurl() {
        return returnurl;
    }

    public void setReturnurl(String returnurl) {
        this.returnurl = returnurl;
    }

    public String getNotifyurl() {
        return notifyurl;
    }

    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl;
    }

    public String getRecieveAmount() {
        return recieveAmount;
    }

    public void setRecieveAmount(String recieveAmount) {
        this.recieveAmount = recieveAmount;
    }

    public String getTradestatus() {
        return tradestatus;
    }

    public void setTradestatus(String tradestatus) {
        this.tradestatus = tradestatus;
    }

    public String getFukuan_date() {
        return fukuan_date;
    }

    public void setFukuan_date(String fukuan_date) {
        this.fukuan_date = fukuan_date;
    }

    public String getFanhui_date() {
        return fanhui_date;
    }

    public void setFanhui_date(String fanhui_date) {
        this.fanhui_date = fanhui_date;
    }

    public String getCj_date() {
        return cj_date;
    }

    public void setCj_date(String cj_date) {
        this.cj_date = cj_date;
    }
}
