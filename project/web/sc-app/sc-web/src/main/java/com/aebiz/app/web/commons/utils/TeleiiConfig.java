package com.aebiz.app.web.commons.utils;

/**
 * Created by zyang on 2017/9/12.
 */
public class TeleiiConfig {
    // 应用id
    private String id;
    // 应用key
    private String key;
    // 时间戳
    private Long timestamp = System.currentTimeMillis();
    // 话单通知地址
    // 用于商户侧接收TELEII平台推送的相关商务号的话单记录。
    // 当无需该功能通知时，不填写该字段
    private String notifyCallUrl = "";
    // 商务号发送短信后收到的状态报告，调用该通知地址
    // 用于商户侧接收TELEII平台推送的相关商务号发送短信的状态报告记录。
    private String notifyMtReceUrl = "";
    // 商务号收到短信后，调用该通知地址
    // 用于商户侧接收TELEII平台推送的相关商务号收到的短信记录。
    // 当无需该功能通知时，不填写该字段
    private String notifyMoUrl = "";
    // 媒体音状态变更回送通知地址 | NO
    private String notifyAudioStatusUrl = "";
    // 媒体音通知地址 | NO
    private String notifyMediaStreamUrl = "";
    // 用户认证状态变更通知地址 | NO
    private String notifyAuthStatusUrl = "";
    // 保密号生成和解绑通知地址 | NO
    private String notifySecretRelationshipUrl = "";
    // 保密号关系每日同步通知地址 | NO
    private String syncRelationshipUrl = "";

    /**
     * 生成签名
     * 算法：Md5(key+id+timestamp+notifyCallUrl+ notifyMtReceUrl+notifyMoUrl)
     *
     * @return
     */
    public String generateSign() {
        ;
        return StringUtil.getMD5(this.key + this.id + this.timestamp + this.notifyCallUrl + this.notifyMtReceUrl + this.notifyMoUrl);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNotifyCallUrl() {
        return notifyCallUrl;
    }

    public void setNotifyCallUrl(String notifyCallUrl) {
        this.notifyCallUrl = notifyCallUrl;
    }

    public String getNotifyMtReceUrl() {
        return notifyMtReceUrl;
    }

    public void setNotifyMtReceUrl(String notifyMtReceUrl) {
        this.notifyMtReceUrl = notifyMtReceUrl;
    }

    public String getNotifyMoUrl() {
        return notifyMoUrl;
    }

    public void setNotifyMoUrl(String notifyMoUrl) {
        this.notifyMoUrl = notifyMoUrl;
    }

    public String getNotifyAudioStatusUrl() {
        return notifyAudioStatusUrl;
    }

    public void setNotifyAudioStatusUrl(String notifyAudioStatusUrl) {
        this.notifyAudioStatusUrl = notifyAudioStatusUrl;
    }

    public String getNotifyMediaStreamUrl() {
        return notifyMediaStreamUrl;
    }

    public void setNotifyMediaStreamUrl(String notifyMediaStreamUrl) {
        this.notifyMediaStreamUrl = notifyMediaStreamUrl;
    }

    public String getNotifyAuthStatusUrl() {
        return notifyAuthStatusUrl;
    }

    public long getTimestamp() {
        return timestamp == null ? System.currentTimeMillis() : timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setNotifyAuthStatusUrl(String notifyAuthStatusUrl) {
        this.notifyAuthStatusUrl = notifyAuthStatusUrl;
    }

    public String getNotifySecretRelationshipUrl() {
        return notifySecretRelationshipUrl;
    }

    public void setNotifySecretRelationshipUrl(String notifySecretRelationshipUrl) {
        this.notifySecretRelationshipUrl = notifySecretRelationshipUrl;
    }

    public String getSyncRelationshipUrl() {
        return syncRelationshipUrl;
    }

    public void setSyncRelationshipUrl(String syncRelationshipUrl) {
        this.syncRelationshipUrl = syncRelationshipUrl;
    }
}
