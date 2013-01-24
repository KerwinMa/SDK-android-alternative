package com.quickblox.android.framework.modules.auth.net.queries;

import com.quickblox.android.framework.base.definitions.ConstsGlobal;
import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.definitions.exceptions.BaseServiceException;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.auth.definitions.Consts;
import com.quickblox.android.framework.modules.auth.net.server.QBAuth;

public class QueryDeleteSession extends Query {

    @Override
    public Class getResultClass() {
        return Result.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }

    @Override
    public String getUrl() {

        return buildQueryUrl(Consts.AUTH_ENDPOINT);
    }

    protected void setHeaders(RestRequest request) {
        try {
            String token = QBAuth.getBaseService().getToken();
            request.getHeaders().put(ConstsGlobal.HEADER_TOKEN, token);
        } catch (BaseServiceException e) {
            e.printStackTrace();
        }
    }
}