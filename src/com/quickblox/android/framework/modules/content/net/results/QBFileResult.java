package com.quickblox.android.framework.modules.content.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.models.QBFile;
import com.quickblox.android.framework.modules.content.models.QBFileWrap;
import com.quickblox.android.framework.modules.content.models.deserializers.QBFileStatusDeserializer;
import com.quickblox.android.framework.modules.messages.models.QBFileStatus;

/**
 * User: Oleg Soroka
 * Date: 28.08.12
 * Time: 14:24
 */
public class QBFileResult extends Result {

    private Lo lo = new Lo(this);

    private QBFile file;

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();

            // Copy fields
            QBFile originalObject = (QBFile) getQuery().getOriginalObject();
            file.copyFieldsTo(originalObject);

            lo.g(LOG_MSG_OBJ_PARSED + file);
        }
    }

    @Override
    protected void extractEntity() {
        String stringToParse = response.getRawBody();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(QBFileStatus.class, new QBFileStatusDeserializer());
        Gson gson = gsonBuilder.create();

        QBFileWrap fileWrap = gson.fromJson(stringToParse, QBFileWrap.class);
        file = fileWrap.getFile();
    }

    //

    public QBFile getFile() {
        return file;
    }

}