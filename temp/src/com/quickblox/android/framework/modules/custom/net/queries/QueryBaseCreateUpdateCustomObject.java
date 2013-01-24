package com.quickblox.android.framework.modules.custom.net.queries;

import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;
import com.quickblox.android.framework.modules.custom.net.results.QBCustomObjectResult;

import java.util.Map;

/**
 * User: Oleg Soroka
 * Date: 13.08.12
 * Time: 11:46
 */
public class QueryBaseCreateUpdateCustomObject extends QueryBaseCustomObject {

    public QueryBaseCreateUpdateCustomObject(QBCustomObject customObject) {
        super(customObject);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();

        for (String key : customObject.getFields().keySet()) {
            Object value = customObject.getFields().get(key);
            if (value != null) {
                putValue(parametersMap, key, value.toString());
            }
        }
    }

    @Override
    public Class getResultClass() {
        return QBCustomObjectResult.class;
    }
}