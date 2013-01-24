package com.quickblox.android.framework.modules.users.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.users.definitions.Consts;

/**
 * User: Andriy Dmitrenko
 * Date: 09.08.12
 * Time: 13:49
 */
public class QuerySignOut extends Query {

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.SIGNIN_ENDPOINT);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }
}