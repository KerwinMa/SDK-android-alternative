package com.quickblox.android.framework.modules.custom.net.queries;

import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.custom.definitions.Consts;
import com.quickblox.android.framework.modules.custom.net.results.QBCustomObjectCountResult;

import java.util.Map;

/**
 * User: Oleg Soroka
 * Date: 13.08.12
 * Time: 11:46
 */
public class QueryCountCustomObject extends QueryGetCustomObjects {

    public QueryCountCustomObject(String className) {
        super(className);
    }

    @Override
    protected void setParams(RestRequest request) {
        super.setParams(request);

        Map<String, String> parametersMap = request.getParameters();
        parametersMap.put(Consts.COUNT, "1");
    }

    @Override
    public Class getResultClass() {
        return QBCustomObjectCountResult.class;
    }

}