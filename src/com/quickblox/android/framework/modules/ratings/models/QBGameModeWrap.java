package com.quickblox.android.framework.modules.ratings.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 08.10.12
 * Time: 15:42
 */
public class QBGameModeWrap {

    @SerializedName("game_mode")
    private QBGameMode gameMode;

    public QBGameModeWrap() {
    }

    public QBGameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(QBGameMode gameMode) {
        this.gameMode = gameMode;
    }
}
