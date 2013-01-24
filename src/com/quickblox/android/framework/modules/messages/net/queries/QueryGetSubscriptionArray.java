package com.quickblox.android.framework.modules.messages.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.messages.definitions.Consts;
import com.quickblox.android.framework.modules.messages.net.results.QBSubscriptionArrayResult;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 21:36
 */
public class QueryGetSubscriptionArray extends Query {

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.SUBSCRIPTIONS_ENDPOINT);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public Class getResultClass() {
        return QBSubscriptionArrayResult.class;
    }
}