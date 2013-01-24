package com.quickblox.android.framework.modules.ratings.net.queries.gameMode;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.net.results.QBGameModeArrayResult;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 10:05
 */
public class QueryGetGameModes extends Query {

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.APPLICATION, Consts.GAME_MODES_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBGameModeArrayResult.class;
    }
}
