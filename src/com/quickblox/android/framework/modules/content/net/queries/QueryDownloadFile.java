package com.quickblox.android.framework.modules.content.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.content.definitions.Consts;
import com.quickblox.android.framework.modules.content.models.QBFile;
import com.quickblox.android.framework.modules.content.net.results.QBFileDownloadResult;

/**
 * User: Andriy Dmitrenko
 * Date: 28.08.12
 * Time: 13:46
 */
public class QueryDownloadFile extends Query {

    private QBFile file;

    public QueryDownloadFile(QBFile file) {
        this.file = file;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    public String getUrl() {
        String url = buildQueryUrl(Consts.BLOBS_ENDPOINT, file.getUid());
        // If file object contains enough data, will try to download by direct amazon link
        // that looks line 'http://qbprod.s3.amazonaws.com/6f60d19e81c84e279660256e4852dc2f00'
        if (file.isPublic() != null && file.isPublic() && file.getDownloadUrl() != null) {
            url = file.getDownloadUrl();
        }
        return url;
    }

    @Override
    public Class getResultClass() {
        return QBFileDownloadResult.class;
    }
}