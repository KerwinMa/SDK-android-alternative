package com.quickblox.android.framework.modules.messages.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.requests.QBPagedRequestBuilder;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.messages.definitions.Consts;
import com.quickblox.android.framework.modules.messages.net.results.QBEventPagedResult;

import java.util.Map;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 16:37
 */
public class QueryGetEventArray extends Query {

    QBPagedRequestBuilder requestBuilder;

    public QueryGetEventArray(QBPagedRequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    @Override
    protected void setParams(RestRequest request) {
        super.setParams(request);
        if (requestBuilder != null) {
            Map<String, String> parametersMap = request.getParameters();
            requestBuilder.fillParametersMap(parametersMap);
        }
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.EVENTS_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBEventPagedResult.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }
}