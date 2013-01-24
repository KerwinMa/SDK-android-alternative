package com.quickblox.android.framework.modules.ratings.net.queries.gameModeParameter;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameter;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 10:03
 */
public class QueryDeleteGameModeParameter extends QueryBaseGameModeParameter {

    public QueryDeleteGameModeParameter(QBGameModeParameter gameModeParameter) {
        super(gameModeParameter);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.GAME_MODE_PARAMETERS_ENDPOINT, gameModeParameter.getId());
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }
}