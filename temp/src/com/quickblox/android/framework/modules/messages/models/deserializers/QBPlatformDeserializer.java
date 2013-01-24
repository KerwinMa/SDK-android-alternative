package com.quickblox.android.framework.modules.messages.models.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.quickblox.android.framework.modules.messages.models.QBPlatform;

import java.lang.reflect.Type;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 11:47
 */
public class QBPlatformDeserializer implements JsonDeserializer<QBPlatform> {
    @Override
    public QBPlatform deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        QBPlatform[] platforms = QBPlatform.values();

        String jsonPlatformName = json.getAsJsonObject().get("name").getAsString();

        QBPlatform platform = QBPlatform.ANDROID;

        for (QBPlatform p : platforms) {
            if (p.getCaption().equals(jsonPlatformName)) {
                platform = p;
                break;
            }
        }

        return platform;
    }
}