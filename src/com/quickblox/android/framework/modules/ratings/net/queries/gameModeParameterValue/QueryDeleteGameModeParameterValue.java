package com.quickblox.android.framework.modules.ratings.net.queries.gameModeParameterValue;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameterValue;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 10:47
 */
public class QueryDeleteGameModeParameterValue extends Query {

    private QBGameModeParameterValue gameModeParameterValue;

    public QueryDeleteGameModeParameterValue(QBGameModeParameterValue gameModeParameterValue) {
        this.gameModeParameterValue = gameModeParameterValue;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.GAME_MODE_PARAMETER_VALUES_ENDPOINT, gameModeParameterValue.getId());
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }
}