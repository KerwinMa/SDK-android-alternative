package com.quickblox.android.framework.modules.content.models.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.quickblox.android.framework.modules.messages.models.QBFileStatus;

import java.lang.reflect.Type;

/**
 * User: Oleg Soroka
 * Date: 04.10.12
 * Time: 12:44
 */
public class QBFileStatusDeserializer implements JsonDeserializer<QBFileStatus> {
    @Override
    public QBFileStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        QBFileStatus[] items = QBFileStatus.values();
        QBFileStatus status = QBFileStatus.UNCOMPLETE;
        for (QBFileStatus item : items) {
            if (item.getCaption().equals(json.getAsString())) {
                status = item;
                break;
            }
        }

        return status;
    }
}