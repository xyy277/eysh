package com.aebiz.app.web.modules.controllers.open.pay.zfbpay.been;

/**
 * 支付宝Pc请求参数
 * @author: jxx
 * @date: 2017/9/19 9:47
 */
public class ZfbPayUnifiedOrder {
    private static final long serialVersionUID = 1L;
    private String appid;
    private String uid;//商户UID
    private String tradeNo;//支付宝交易号
    private String outTradeno;//订单号
    private String totalAmount;//交易金额
    private String recieveAmount;//收到金额
    private String tradestatus;//交易状态
    private String subject;//购买商品
    private String body;//购买商品详情
    private String returnurl;//成功返回
    private String notifyurl;//回调参数
    private String cj_date;//创建时间
    private String fukuan_date;//付款时间
    private String fanhui_date;//返回时间

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getCj_date() {
        return cj_date;
    }

    public void setCj_date(String cj_date) {
        this.cj_date = cj_date;
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
}
