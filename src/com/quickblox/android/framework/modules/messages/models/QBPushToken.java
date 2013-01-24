package com.quickblox.android.framework.modules.messages.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 11:11
 */
public class QBPushToken {

    @SerializedName("id")
    Integer id;
    @SerializedName("client_identification_sequence")
    String cis;
    @SerializedName("environment")
    QBEnvironment environment;

    public QBPushToken() {
    }

    public QBPushToken(QBEnvironment environment, String cis) {
        this.environment = environment;
        this.cis = cis;
    }

    public QBPushToken(int pushTokenId) {
        id = pushTokenId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCis() {
        return cis;
    }

    public void setCis(String cis) {
        this.cis = cis;
    }

    public QBEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(QBEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "QBPushToken{" +
                "id=" + id +
                ", cis='" + cis + '\'' +
                ", environment=" + environment +
                '}';
    }

    public void copyFieldsTo(QBPushToken pushToken) {
        pushToken.setId(id);
        pushToken.setEnvironment(environment);
        pushToken.setCis(cis);
    }
}
