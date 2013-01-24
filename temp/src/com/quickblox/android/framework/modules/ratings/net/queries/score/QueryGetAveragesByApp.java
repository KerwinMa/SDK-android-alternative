package com.quickblox.android.framework.modules.ratings.net.queries.score;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.net.results.QBAverageArrayResult;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 11:59
 */
public class QueryGetAveragesByApp extends Query {

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.APPLICATION, Consts.AVERAGES_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBAverageArrayResult.class;
    }
}
