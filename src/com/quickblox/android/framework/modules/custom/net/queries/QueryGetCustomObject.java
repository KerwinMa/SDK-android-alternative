package com.quickblox.android.framework.modules.custom.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.custom.definitions.Consts;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;
import com.quickblox.android.framework.modules.custom.net.results.QBCustomObjectResult;


/**
 * User: Oleg Soroka
 * Date: 13.08.12
 * Time: 11:46
 */
public class QueryGetCustomObject extends QueryBaseCustomObject {

    public QueryGetCustomObject(QBCustomObject customObject) {
        super(customObject);
    }

    @Override
    public String getUrl() {
        String className = customObject.getClassName();
        String cid = customObject.getCustomObjectId();
        return buildQueryUrl(Consts.CUSTOM_OBJECT_ENDPOINT, className, cid);
    }

    @Override
    public Class getResultClass() {
        return QBCustomObjectResult.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

}