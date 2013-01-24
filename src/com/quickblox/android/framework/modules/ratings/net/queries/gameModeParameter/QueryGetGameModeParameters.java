package com.quickblox.android.framework.modules.ratings.net.queries.gameModeParameter;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.models.QBGameMode;
import com.quickblox.android.framework.modules.ratings.net.results.QBGameModeParameterArrayResult;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 9:58
 */
public class QueryGetGameModeParameters extends Query {

    private QBGameMode gameMode;

    public QueryGetGameModeParameters(QBGameMode gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.GAME_MODES_ENDPOINT, gameMode.getId(), Consts.GAME_MODE_PARAMETERS_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBGameModeParameterArrayResult.class;
    }

    //

    public QBGameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(QBGameMode gameMode) {
        this.gameMode = gameMode;
    }
}