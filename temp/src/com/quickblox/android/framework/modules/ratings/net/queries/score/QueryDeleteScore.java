package com.quickblox.android.framework.modules.ratings.net.queries.score;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.models.QBScore;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 14:07
 */
public class QueryDeleteScore extends QueryBaseScore {

    public QueryDeleteScore(QBScore score) {
        super(score);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.SCORES_ENDPOINT, score.getId());
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }
}