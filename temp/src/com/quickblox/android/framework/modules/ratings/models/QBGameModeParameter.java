package com.quickblox.android.framework.modules.ratings.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntity;

/**
 * User: Andriy Dmitrenko
 * Date: 22.08.12
 * Time: 14:23
 */
public class QBGameModeParameter extends QBEntity {

    @SerializedName("game_mode_id")
    private Integer gameModeId;

    @SerializedName("name")
    private String name;

    public QBGameModeParameter() {
    }

    public QBGameModeParameter(int id) {
        this.id = id;
    }

    //

    public void setGameModeId(int gameModeId) {
        this.gameModeId = gameModeId;
    }

    public Integer getGameModeId() {
        return gameModeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void copyFieldsTo(QBEntity entity) {
        super.copyFieldsTo(entity);
        QBGameModeParameter gameModeParameter = (QBGameModeParameter) entity;
        gameModeParameter.setGameModeId(gameModeId);
        gameModeParameter.setId(getId());
        gameModeParameter.setName(name);
    }

    @Override
    public String toString() {
        return "QBGameModeParameters{" +
                "game_mode_id=" + gameModeId +
                ", id=" + getId() +
                ", name=" + name + '\'' +
                '}';
    }
}