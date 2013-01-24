package com.quickblox.android.framework.modules.messages.models.deserializers;

import com.google.gson.*;
import com.quickblox.android.framework.modules.messages.models.QBTagsQuery;

import java.lang.reflect.Type;

/**
 * User: Oleg Soroka
 * Date: 27.09.12
 * Time: 14:45
 */
public class QBTagsQueryDeserializer implements JsonDeserializer<QBTagsQuery> {
    @Override
    public QBTagsQuery deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        QBTagsQuery tagsQuery;

        if (json.isJsonObject()) {
            Gson gson = new Gson();
            tagsQuery = gson.fromJson(json.toString(), QBTagsQuery.class);
        } else {
            tagsQuery = null;
        }

        return tagsQuery;
    }
}