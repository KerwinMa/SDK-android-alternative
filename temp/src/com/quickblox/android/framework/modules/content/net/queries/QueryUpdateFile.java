package com.quickblox.android.framework.modules.content.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.content.models.QBFile;
import com.quickblox.android.framework.modules.content.definitions.Consts;
import com.quickblox.android.framework.modules.content.net.results.QBFileResult;

import java.util.Map;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 15:16
 */
public class QueryUpdateFile extends QueryBaseFile {


    public QueryUpdateFile(QBFile file) {
        super(file);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.BLOB_CONTENT_TYPE, file.getContentType());
        putValue(parametersMap, Consts.BLOB_NAME, file.getName());
        putValue(parametersMap, Consts.BLOB_TAGS, file.getTags());
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.PUT);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.BLOBS_ENDPOINT, file.getId());
    }

    @Override
    public Class getResultClass() {
        return QBFileResult.class;
    }
}