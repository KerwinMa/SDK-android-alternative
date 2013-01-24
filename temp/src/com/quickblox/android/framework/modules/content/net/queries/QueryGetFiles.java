package com.quickblox.android.framework.modules.content.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.content.definitions.Consts;
import com.quickblox.android.framework.modules.content.net.results.QBFilePagedResult;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 15:34
 */
public class QueryGetFiles extends Query {

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.BLOBS_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBFilePagedResult.class;
    }
}
