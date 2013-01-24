package com.quickblox.android.framework.modules.messages.models.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.quickblox.android.framework.modules.messages.models.QBEventType;

import java.lang.reflect.Type;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 23:32
 */
public class QBSubscribersSelectorDeserializer implements JsonDeserializer<QBEventType> {
    @Override
    public QBEventType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        QBEventType[] items = QBEventType.values();

        QBEventType result = QBEventType.ONE_SHOT;

        for (QBEventType item : items) {
            if (item.getCaption().equals(json.getAsString())) {
                result = item;
                break;
            }
        }

        return result;
    }
}