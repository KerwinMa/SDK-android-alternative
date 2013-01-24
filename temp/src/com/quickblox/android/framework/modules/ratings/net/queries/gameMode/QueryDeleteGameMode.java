package com.quickblox.android.framework.modules.ratings.net.queries.gameMode;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.models.QBGameMode;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 10:08
 */
public class QueryDeleteGameMode extends QueryBaseGameMode {

    public QueryDeleteGameMode(QBGameMode gameMode) {
        super(gameMode);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.GAME_MODES_ENDPOINT, gameMode.getId());
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }
}