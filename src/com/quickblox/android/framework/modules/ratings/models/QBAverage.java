package com.quickblox.android.framework.modules.ratings.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 09.10.12
 * Time: 12:09
 */
public class QBAverage {

    @SerializedName("game_mode_id")
    private Integer gameModeId;

    @SerializedName("value")
    Double value;

    public QBAverage() {
    }

    //

    public Integer getGameModeId() {
        return gameModeId;
    }

    public void setGameModeId(Integer gameModeId) {
        this.gameModeId = gameModeId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "QBAverage{" +
                "gameModeId=" + gameModeId +
                ", value=" + value +
                '}';
    }
}