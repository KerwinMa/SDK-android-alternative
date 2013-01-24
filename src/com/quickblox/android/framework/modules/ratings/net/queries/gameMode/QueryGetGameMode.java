package com.quickblox.android.framework.modules.ratings.net.queries.gameMode;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.models.QBGameMode;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.net.results.QBGameModeResult;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 9:59
 */
public class QueryGetGameMode extends QueryBaseGameMode {

    public QueryGetGameMode(QBGameMode gameMode) {
        super(gameMode);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.GAME_MODES_ENDPOINT, gameMode.getId());
    }

    @Override
    public Class getResultClass() {
        return QBGameModeResult.class;
    }

}