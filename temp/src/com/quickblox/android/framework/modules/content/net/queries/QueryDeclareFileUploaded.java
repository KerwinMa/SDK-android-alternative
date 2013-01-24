package com.quickblox.android.framework.modules.content.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.definitions.Consts;

import java.util.Map;

/**
 * User: Oleg Soroka
 * Date: 04.10.12
 * Time: 23:02
 */
public class QueryDeclareFileUploaded extends Query {

    private int fileId;
    private int fileSize;

    public QueryDeclareFileUploaded(int fileId, int fileSize) {
        this.fileId = fileId;
        this.fileSize = fileSize;
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.BLOB_SIZE, fileSize);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.BLOBS_ENDPOINT, fileId, Consts.COMPLETE);
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }
}