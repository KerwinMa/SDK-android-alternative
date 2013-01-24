package com.quickblox.android.framework.modules.ratings.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 08.10.12
 * Time: 17:31
 */
public class QBGameModeParameterWrap {

    @SerializedName("game_mode_parameter")
    private QBGameModeParameter gameModeParameter;

    public QBGameModeParameterWrap() {
    }

    public QBGameModeParameter getGameModeParameter() {
        return gameModeParameter;
    }

    public void setGameModeParameter(QBGameModeParameter gameModeParameter) {
        this.gameModeParameter = gameModeParameter;
    }
}