package com.quickblox.android.framework.modules.custom.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.LimitedResult;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;
import com.quickblox.android.framework.modules.custom.models.QBCustomObjectLimited;
import com.quickblox.android.framework.modules.custom.models.deserializers.QBCustomObjectDeserializer;
import com.quickblox.android.framework.modules.custom.net.queries.QueryGetCustomObjects;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 13.08.12
 * Time: 11:48
 */
public class QBCustomObjectLimitedResult extends LimitedResult<QBCustomObject> {

    private Lo lo = new Lo(this);

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();
        }

        lo.g(LOG_MSG_OBJ_PARSED + items);
    }

    @Override
    protected void extractEntity() {
        QueryGetCustomObjects query = (QueryGetCustomObjects) getQuery();
        String className = query.getClassName();
        if (query.getRequestBuilder() != null) {
            setPagesSkip(query.getRequestBuilder().getPagesSkip());
            setPagesLimit(query.getRequestBuilder().getPagesLimit());
        }

        String stringToParse = response.getRawBody();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(QBCustomObject.class, new QBCustomObjectDeserializer(className));

        Gson gson = gsonBuilder.create();

        QBCustomObjectLimited customObjectLimited = gson.fromJson(stringToParse, QBCustomObjectLimited.class);

        pagesSkip = customObjectLimited.getSkip();
        pagesLimit = customObjectLimited.getLimit();

        items.addAll(customObjectLimited.getItems());
    }

    //

    public ArrayList<QBCustomObject> getCustomObjects() {
        return items;
    }

    public void setCustomObjects(ArrayList<QBCustomObject> customObjectList) {
        this.items = customObjectList;
    }
}