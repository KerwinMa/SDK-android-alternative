package com.quickblox.android.framework.modules.ratings.net.queries.gameModeParameterValue;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameterValue;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.net.results.QBGameModeParameterValueResult;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 10:13
 */
public class QueryCreateGameModeParameterValue extends QueryBaseGameModeParameterValue {

    public QueryCreateGameModeParameterValue(QBGameModeParameterValue gameModeParameterValue) {
        super(gameModeParameterValue);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.GAME_MODE_PARAMETER_VALUES_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBGameModeParameterValueResult.class;
    }
}