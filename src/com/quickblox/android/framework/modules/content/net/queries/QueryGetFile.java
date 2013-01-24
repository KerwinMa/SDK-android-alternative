package com.quickblox.android.framework.modules.content.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.content.definitions.Consts;
import com.quickblox.android.framework.modules.content.models.QBFile;
import com.quickblox.android.framework.modules.content.net.results.QBFileResult;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 15:40
 */
public class QueryGetFile extends Query {

    private QBFile file;

    public QueryGetFile(QBFile file) {
        this.file = file;
        setOriginalObject(file);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.BLOBS_ENDPOINT, file.getId());
    }

    @Override
    public Class getResultClass() {
        return QBFileResult.class;
    }

    //

    public QBFile getFile() {
        return file;
    }

    public void setFile(QBFile file) {
        this.file = file;
    }
}