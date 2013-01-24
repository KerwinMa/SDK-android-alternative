package com.quickblox.android.framework.modules.ratings.net.queries.gameModeParameter;

import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameter;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 14:53
 */
public class QueryBaseGameModeParameter extends Query {

    protected QBGameModeParameter gameModeParameter;

    public QueryBaseGameModeParameter(QBGameModeParameter gameModeParameter) {
        this.gameModeParameter = gameModeParameter;
        setOriginalObject(gameModeParameter);
    }

    public QBGameModeParameter getGameModeParameter() {
        return gameModeParameter;
    }

    public void setGameModeParameter(QBGameModeParameter gameModeParameter) {
        this.gameModeParameter = gameModeParameter;
    }
}