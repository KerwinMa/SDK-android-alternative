package com.quickblox.android.framework.modules.ratings.net.queries.gameModeParameterValue;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameter;
import com.quickblox.android.framework.modules.ratings.models.QBScore;
import com.quickblox.android.framework.modules.ratings.net.results.QBGameModeParameterValuePagedResult;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 10:38
 */
public class QueryGetGameModeParameterValueApi extends Query {

    private QBScore score;
    private QBGameModeParameter gameModeParameter;

    public QueryGetGameModeParameterValueApi(QBScore score, QBGameModeParameter gameModeParameter) {
        this.score = score;
        this.gameModeParameter = gameModeParameter;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.SCORES_ENDPOINT, score.getId(),
                Consts.GAME_MODE_PARAMETERS_ENDPOINT,
                gameModeParameter.getId(),
                Consts.VALUE);
    }

    @Override
    public Class getResultClass() {
        return QBGameModeParameterValuePagedResult.class;
    }
}