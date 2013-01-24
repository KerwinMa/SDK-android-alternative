package com.quickblox.android.framework.modules.content.net.results;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.models.QBFileObjectAccess;
import com.quickblox.android.framework.modules.content.models.QBFileObjectAccessWrap;

/**
 * User: Andriy Dmitrenko
 * Date: 28.08.12
 * Time: 14:51
 */
public class QBFileObjectAccessResult extends Result {

    Lo lo = new Lo(this);

    private QBFileObjectAccess fileObjectAccess;

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();

            // Copy fields
            QBFileObjectAccess originalObject = (QBFileObjectAccess) getQuery().getOriginalObject();
            fileObjectAccess.copyFieldsTo(originalObject);

            lo.g(LOG_MSG_OBJ_PARSED + fileObjectAccess);
        }
    }

    @Override
    protected void extractEntity() {
        String stringToParse = response.getRawBody();
        Gson gson = new Gson();
        QBFileObjectAccessWrap fileObjectAccessWrap = gson.fromJson(stringToParse, QBFileObjectAccessWrap.class);
        fileObjectAccess = fileObjectAccessWrap.getFileObjectAccess();
    }

    //

    public QBFileObjectAccess getFileObjectAccess() {
        return fileObjectAccess;
    }
}