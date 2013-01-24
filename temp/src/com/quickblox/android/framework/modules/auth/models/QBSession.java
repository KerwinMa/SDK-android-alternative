package com.quickblox.android.framework.modules.auth.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntity;

/**
 * User: Igor Khomenko
 */
public class QBSession extends QBEntity {

    @SerializedName("token")
    private String token;

    @SerializedName("application_id")
    private Integer appId;

    @SerializedName("user_id")
    private Integer userId;

    @SerializedName("device_id")
    private Integer deviceId;

    @SerializedName("ts")
    private Integer timestamp;

    @SerializedName("nonce")
    private Integer nonce;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(Integer nonce) {
        this.nonce = nonce;
    }

    @Override
    public void copyFieldsTo(QBEntity entity) {
        super.copyFieldsTo(entity);

        QBSession session = (QBSession) entity;
        session.setToken(token);
        session.setAppId(appId);
        session.setUserId(userId);
        session.setDeviceId(deviceId);
        session.setTimestamp(timestamp);
        session.setNonce(nonce);
    }

    @Override
    public String toString() {
        return "QBSession{" +
                "token='" + token + '\'' +
                ", appId=" + appId +
                ", userId=" + userId +
                ", deviceId=" + deviceId +
                ", timestamp=" + timestamp +
                ", nonce=" + nonce +
                '}';
    }
}