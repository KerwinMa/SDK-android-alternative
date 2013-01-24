package com.quickblox.android.framework.modules.messages.models.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.quickblox.android.framework.modules.messages.models.QBNotificationChannel;

import java.lang.reflect.Type;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 11:47
 */
public class QBNotificationChannelDeserializer implements JsonDeserializer<QBNotificationChannel> {
    @Override
    public QBNotificationChannel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        QBNotificationChannel[] channels = QBNotificationChannel.values();

        String jsonChannelName = json.getAsJsonObject().get("name").getAsString();

        QBNotificationChannel channel = QBNotificationChannel.PULL;

        for (QBNotificationChannel nc : channels) {
            if (nc.getCaption().equals(jsonChannelName)) {
                channel = nc;
                break;
            }
        }

        return channel;
    }
}