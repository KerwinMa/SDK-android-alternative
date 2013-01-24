package com.quickblox.android.framework.modules.ratings.net.queries.gameMode;

import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.modules.ratings.models.QBGameMode;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 9:41
 */
public class QueryBaseGameMode extends Query {

    protected QBGameMode gameMode;

    public QueryBaseGameMode(QBGameMode gameMode) {
        this.gameMode = gameMode;
        setOriginalObject(gameMode);
    }

    //

    public QBGameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(QBGameMode gameMode) {
        this.gameMode = gameMode;
    }
}
