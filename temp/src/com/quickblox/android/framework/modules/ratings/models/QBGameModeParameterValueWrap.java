package com.quickblox.android.framework.modules.ratings.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 08.10.12
 * Time: 18:51
 */
public class QBGameModeParameterValueWrap {

    @SerializedName("game_mode_parameter_value")
    private QBGameModeParameterValue gameModeParameterValue;

    public QBGameModeParameterValueWrap() {
    }

    public QBGameModeParameterValue getGameModeParameterValue() {
        return gameModeParameterValue;
    }

    public void setGameModeParameterValue(QBGameModeParameterValue gameModeParameterValue) {
        this.gameModeParameterValue = gameModeParameterValue;
    }
}