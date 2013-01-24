package com.quickblox.android.framework.modules.ratings.net.queries.score;

import com.quickblox.android.framework.base.net.queries.PagedQuery;
import com.quickblox.android.framework.base.net.requests.QBPagedRequestBuilder;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.net.results.QBScorePagedResult;
import com.quickblox.android.framework.modules.users.models.QBUser;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 11:45
 */
public class QueryGetScores extends PagedQuery {

    private QBUser user;

    public QueryGetScores(QBUser user, QBPagedRequestBuilder requestBuilder) {
        this.user = user;
        this.requestBuilder = requestBuilder;
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.USERS, user.getId(), Consts.SCORES_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBScorePagedResult.class;
    }

}