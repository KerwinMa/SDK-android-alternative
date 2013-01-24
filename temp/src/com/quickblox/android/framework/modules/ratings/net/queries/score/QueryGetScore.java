package com.quickblox.android.framework.modules.ratings.net.queries.score;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.models.QBScore;
import com.quickblox.android.framework.modules.ratings.net.results.QBScoreResult;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 10:58
 */
public class QueryGetScore extends Query {

    private QBScore score;

    public QueryGetScore(QBScore score) {
        this.score = score;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
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