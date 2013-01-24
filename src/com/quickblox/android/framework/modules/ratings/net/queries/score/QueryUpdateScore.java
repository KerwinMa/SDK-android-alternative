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
 * Time: 10:55
 */
public class QueryUpdateScore extends QueryBaseScore {

    public QueryUpdateScore(QBScore score) {
        super(score);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.SCORE_VALUE, score.getValue());
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.PUT);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.SCORES_ENDPOINT, score.getId());
    }

    @Override
    public Class getResultClass() {
        return QBScoreResult.class;
    }
}