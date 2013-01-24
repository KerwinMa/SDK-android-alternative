package com.quickblox.android.framework.modules.ratings.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntity;

/**
 * User: Andriy Dmitrenko
 * Date: 20.08.12
 * Time: 17:30
 */
public class QBScore extends QBEntity {

    @SerializedName("user_id")
    private Integer userId;

    @SerializedName("game_mode_id")
    private Integer gameModeId;

    @SerializedName("value")
    private Integer value;

    //

    public QBScore() {
    }

    public QBScore(int id) {
        this.id = id;
    }

    //

    public void setGameModeId(int gameModeId) {
        this.gameModeId = gameModeId;
    }

    public Integer getGameModeId() {
        return gameModeId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void copyFieldsTo(QBEntity entity) {
        super.copyFieldsTo(entity);
        QBScore score = (QBScore) entity;
        score.setGameModeId(gameModeId);
        score.setId(getId());
        score.setUserId(userId);
        score.setValue(value);
        score.setCreatedAt(createdAt);
    }

    @Override
    public String toString() {
        return "QBScore{" +
                "game_mode_id=" + gameModeId +
                ", scoreId=" + getId() +
                ", user_id=" + userId +
                ", value=" + value +
                ", created_at=" + createdAt + '\'' +
                '}';
    }

}