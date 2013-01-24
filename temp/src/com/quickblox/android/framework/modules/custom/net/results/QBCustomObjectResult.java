package com.quickblox.android.framework.modules.custom.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;
import com.quickblox.android.framework.modules.custom.models.deserializers.QBCustomObjectDeserializer;
import com.quickblox.android.framework.modules.custom.net.queries.QueryBaseCustomObject;

/**
 * User: Oleg Soroka
 * Date: 13.08.12
 * Time: 11:48
 */
public class QBCustomObjectResult extends Result {

    private Lo lo = new Lo(this);

    private QBCustomObject customObject;

    public QBCustomObject getCustomObject() {
        return customObject;
    }

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();

            // Copy fields.
            QBCustomObject originalObject = (QBCustomObject) getQuery().getOriginalObject();
            customObject.copyFieldsTo(originalObject);
        }

        lo.g(LOG_MSG_OBJ_PARSED + customObject);
    }

    @Override
    protected void extractEntity() {
        QueryBaseCustomObject query = (QueryBaseCustomObject) getQuery();
        String className = query.getCustomObject().getClassName();

        String stringToParse = response.getRawBody();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(QBCustomObject.class, new QBCustomObjectDeserializer(className));
        Gson gson = gsonBuilder.create();
        customObject = gson.fromJson(stringToParse, QBCustomObject.class);
    }
}