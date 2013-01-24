package com.quickblox.android.framework.modules.messages.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.messages.definitions.Consts;
import com.quickblox.android.framework.modules.messages.net.results.QBEventArrayResult;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 19:04
 */
public class QueryGetPullEventArray extends Query {

    @Override
    protected void setParams(RestRequest request) {
        super.setParams(request);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.PULL_EVENTS_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBEventArrayResult.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

}