package com.quickblox.android.framework.modules.content.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.definitions.Consts;
import com.quickblox.android.framework.modules.content.models.QBFile;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 15:49
 */
public class QueryDeleteFile extends Query {

    private QBFile file;

    public QueryDeleteFile(QBFile file) {
        this.file = file;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.BLOBS_ENDPOINT, file.getId());
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }
}