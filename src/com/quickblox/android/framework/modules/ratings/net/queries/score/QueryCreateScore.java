package com.quickblox.android.framework.modules.ratings.net.queries.score;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.models.QBScore;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.net.results.QBScoreResult;

import java.util.Map;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 10:46
 */
public class QueryCreateScore extends QueryBaseScore {

    public QueryCreateScore(QBScore score) {
        super(score);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.SCORE_GAME_MODE_ID, score.getGameModeId());
        putValue(parametersMap, Consts.SCORE_VALUE, score.getValue());
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.SCORES_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBScoreResult.class;
    }
}