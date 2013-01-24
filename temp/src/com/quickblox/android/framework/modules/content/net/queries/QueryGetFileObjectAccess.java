package com.quickblox.android.framework.modules.content.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.content.definitions.Consts;
import com.quickblox.android.framework.modules.content.models.QBFileObjectAccess;
import com.quickblox.android.framework.modules.content.net.results.QBFileObjectAccessResult;

/**
 * User: Andriy Dmitrenko
 * Date: 28.08.12
 * Time: 12:19
 */
public class QueryGetFileObjectAccess extends Query {

    private QBFileObjectAccess fileObjectAccess;

    public QueryGetFileObjectAccess(QBFileObjectAccess fileObjectAccess) {
        this.fileObjectAccess = fileObjectAccess;
        setOriginalObject(fileObjectAccess);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.BLOBS_ENDPOINT, fileObjectAccess.getFileId(), Consts.GET_BLOB_OBJECT_BY_ID);
    }

    @Override
    public Class getResultClass() {
        return QBFileObjectAccessResult.class;
    }
}
