package com.quickblox.android.framework.modules.custom.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.helpers.GenericQueryRule;
import com.quickblox.android.framework.base.net.queries.LimitedQuery;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.custom.definitions.Consts;
import com.quickblox.android.framework.modules.custom.net.requests.QBCustomObjectRequestBuilder;
import com.quickblox.android.framework.modules.custom.net.requests.QueryRule;
import com.quickblox.android.framework.modules.custom.net.results.QBCustomObjectLimitedResult;

import java.util.Map;

/**
 * User: Oleg Soroka
 * Date: 13.08.12
 * Time: 11:46
 */
public class QueryGetCustomObjects extends LimitedQuery {

    protected String className;
    protected QBCustomObjectRequestBuilder requestBuilder;

    public QueryGetCustomObjects(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.CUSTOM_OBJECT_ENDPOINT, className);
    }

    @Override
    public Class getResultClass() {
        return QBCustomObjectLimitedResult.class;
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();

        if (requestBuilder != null) {
            if (requestBuilder.getPagesSkip() != 0) {
                setPagesSkip(requestBuilder.getPagesSkip());
            }

            if (requestBuilder.getPagesLimit() != 0) {
                setPagesLimit(requestBuilder.getPagesLimit());
            }

            for (GenericQueryRule gqr : requestBuilder.getRules()) {
                QueryRule qr = (QueryRule) gqr;
                parametersMap.put(qr.getRuleAsRequestParam(), qr.getRuleAsRequestValue());
            }
        }

        super.setParams(request);
    }

    public void setRequestBuilder(QBCustomObjectRequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    public QBCustomObjectRequestBuilder getRequestBuilder() {
        return requestBuilder;
    }
}