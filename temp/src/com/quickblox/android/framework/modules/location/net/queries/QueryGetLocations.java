package com.quickblox.android.framework.modules.location.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.helpers.GenericQueryRule;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.location.definitions.Consts;
import com.quickblox.android.framework.modules.location.net.request.QBLocationRequestBuilder;
import com.quickblox.android.framework.modules.location.net.result.QBLocationPagedResult;

import java.util.Map;

/**
 * User: Oleg Soroka
 * Date: 15.08.12
 * Time: 12:27
 */
public class QueryGetLocations extends Query {

    private QBLocationRequestBuilder requestBuilder;

    public QueryGetLocations(QBLocationRequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.LOCATION_ENDPOINT, Consts.LOCATION_FIND);
    }

    @Override
    protected void setParams(RestRequest request) {
        super.setParams(request);

        Map<String, String> parametersMap = request.getParameters();

        for (GenericQueryRule qr : requestBuilder.getRules()) {
            parametersMap.put(qr.getParamName(), qr.getParamValue());
        }
    }

    @Override
    public Class getResultClass() {
        return QBLocationPagedResult.class;
    }

    //

    public QBLocationRequestBuilder getRequestBuilder() {
        return requestBuilder;
    }

    public void setRequestBuilder(QBLocationRequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }
}