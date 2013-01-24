package com.quickblox.android.framework.modules.ratings.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntity;

/**
 * User: Andriy Dmitrenko
 * Date: 20.08.12
 * Time: 17:30
 */
public class QBGameMode extends QBEntity {

    @SerializedName("application_id")
    private Integer appId;

    @SerializedName("title")
    private String title;

    @SerializedName("user_id")
    private Integer userId;

    //

    public QBGameMode() {
    }

    public QBGameMode(int id) {
        this.id = id;
    }

    public QBGameMode(String title) {
        this.title = title;
    }

    //

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void copyFieldsTo(QBEntity entity) {
        super.copyFieldsTo(entity);
        QBGameMode gameMode = (QBGameMode) entity;
        gameMode.setAppId(appId);
        gameMode.setTitle(title);
        gameMode.setId(getId());
        gameMode.setUserId(userId);
    }

    @Override
    public String toString() {
        return "QBGameMode{" +
                "appId=" + appId +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                '}';
    }
}