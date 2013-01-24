package com.quickblox.android.framework.modules.messages.models.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.quickblox.android.framework.modules.messages.models.QBEnvironment;

import java.lang.reflect.Type;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 11:47
 */
public class QBEnvironmentDeserializer implements JsonDeserializer<QBEnvironment> {
    @Override
    public QBEnvironment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        QBEnvironment[] environments = QBEnvironment.values();

        for (QBEnvironment env : environments) {
            // If successfully parsed, returns deserialized value,
            if (env.getCaption().equals(json.getAsString())) {
                return env;
            }
        }

        // else returns default value
        return QBEnvironment.DEVELOPMENT;
    }
}