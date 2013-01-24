package com.quickblox.android.framework.modules.custom.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.custom.definitions.Consts;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;


/**
 * User: Oleg Soroka
 * Date: 13.08.12
 * Time: 11:46
 */
public class QueryDeleteCustomObject extends QueryBaseCustomObject {

    public QueryDeleteCustomObject(QBCustomObject customObject) {
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
        return Result.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }

}