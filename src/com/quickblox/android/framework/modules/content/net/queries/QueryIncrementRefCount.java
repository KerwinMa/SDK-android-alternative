package com.quickblox.android.framework.modules.content.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.definitions.Consts;
import com.quickblox.android.framework.modules.content.models.QBFile;

/**
 * User: Andriy Dmitrenko
 * Date: 28.08.12
 * Time: 12:31
 */
public class QueryIncrementRefCount extends Query {

    private QBFile file;

    public QueryIncrementRefCount(QBFile file) {
        this.file = file;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.PUT);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.BLOBS_ENDPOINT, file.getId(), Consts.RETAIN);
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }
}