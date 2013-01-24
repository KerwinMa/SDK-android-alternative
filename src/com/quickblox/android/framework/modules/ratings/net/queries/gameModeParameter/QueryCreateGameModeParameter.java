package com.quickblox.android.framework.modules.ratings.net.queries.gameModeParameter;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameter;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.net.results.QBGameModeParameterResult;

import java.util.Map;

/**
 * User: Andriy Dmitrenko
 * Date: 22.08.12
 * Time: 14:54
 */
public class QueryCreateGameModeParameter extends QueryBaseGameModeParameter {

    public QueryCreateGameModeParameter(QBGameModeParameter gameModeParameter) {
        super(gameModeParameter);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.GAME_MODE_PARAMETER_GAME_MODE_ID, gameModeParameter.getGameModeId());
        putValue(parametersMap, Consts.GAME_MODE_PARAMETER_NAME, gameModeParameter.getName());
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.GAME_MODE_PARAMETERS_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBGameModeParameterResult.class;
    }
}