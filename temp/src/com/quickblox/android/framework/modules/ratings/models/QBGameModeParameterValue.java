package com.quickblox.android.framework.modules.ratings.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntity;

/**
 * User: Andriy Dmitrenko
 * Date: 22.08.12
 * Time: 14:38
 */
public class QBGameModeParameterValue extends QBEntity {

    @SerializedName("game_mode_parameter_id")
    private Integer gameModeParameterId;

    @SerializedName("score_id")
    private Integer scoreId;

    @SerializedName("value")
    private Integer value;

    public QBGameModeParameterValue() {
    }

    public QBGameModeParameterValue(int id) {
        super(id);
    }

    //

    public void setGameModeParameterId(int gameModeParameterId) {
        this.gameModeParameterId = gameModeParameterId;
    }

    public Integer getGameModeParameterId() {
        return gameModeParameterId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void copyFieldsTo(QBEntity entity) {
        super.copyFieldsTo(entity);
        QBGameModeParameterValue gameModeParameterValue = (QBGameModeParameterValue) entity;
        gameModeParameterValue.setCreatedAt(getCreatedAt());
        gameModeParameterValue.setGameModeParameterId(gameModeParameterId);
        gameModeParameterValue.setId(getId());
        gameModeParameterValue.setScoreId(scoreId);
        gameModeParameterValue.setValue(value);
    }

    @Override
    public String toString() {
        return "QBGameModeParameterValue{" +
                "gameModeParameterId=" + gameModeParameterId +
                ", scoreId=" + scoreId +
                ", value=" + value +
                '}';
    }
}