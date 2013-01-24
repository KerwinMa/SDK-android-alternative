package com.quickblox.android.framework.modules.location.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.location.definitions.Consts;

import java.util.Map;

/**
 * @author: Oleg Soroka
 * Date: 17.07.12
 * Time: 14:47
 */

public class QueryDeleteObsoleteLocation extends Query {

    int daysRemain;

    public QueryDeleteObsoleteLocation(int period) {
        this.daysRemain = period;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.LOCATION_ENDPOINT);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.DAYS, daysRemain);
    }


    @Override
    public Class getResultClass() {
        return Result.class;
    }
}