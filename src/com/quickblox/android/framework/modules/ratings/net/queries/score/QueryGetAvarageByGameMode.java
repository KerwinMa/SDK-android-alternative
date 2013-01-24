package com.quickblox.android.framework.modules.ratings.net.queries.score;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.models.QBGameMode;
import com.quickblox.android.framework.modules.ratings.net.results.QBAverageResult;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 11:54
 */
public class QueryGetAvarageByGameMode extends Query {

    private QBGameMode gameMode;

    public QueryGetAvarageByGameMode(QBGameMode gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.GAME_MODES_ENDPOINT, gameMode.getId(), Consts.AVERAGE_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBAverageResult.class;
    }
}