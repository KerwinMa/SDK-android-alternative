package com.quickblox.android.framework.base.net.queries;


import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.requests.QBPagedRequestBuilder;
import com.quickblox.android.framework.base.net.rest.RestRequest;

import java.util.Map;

/**
 * User: Andriy Dmitrenko
 * Date: 09.08.12
 * Time: 14:49
 */
public class PagedQuery extends Query {

    protected QBPagedRequestBuilder requestBuilder;

    @Override
    protected void setParams(RestRequest request) {
        super.setParams(request);
        Map<String, String> parametersMap = request.getParameters();
        if (requestBuilder != null) {
            requestBuilder.fillParametersMap(parametersMap);
        }
    }
}