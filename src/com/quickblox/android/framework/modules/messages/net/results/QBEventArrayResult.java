package com.quickblox.android.framework.modules.messages.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.quickblox.android.framework.base.net.results.ArrayResult;
import com.quickblox.android.framework.modules.messages.models.QBEvent;
import com.quickblox.android.framework.modules.messages.models.QBEventType;
import com.quickblox.android.framework.modules.messages.models.QBEventWrap;
import com.quickblox.android.framework.modules.messages.models.QBNotificationChannel;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBEventTypeDeserializer;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBNotificationChannelDeserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 19:17
 */
public class QBEventArrayResult extends ArrayResult<QBEvent> {

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();
        }
    }

    @Override
    protected void extractEntity() {
        String stringToParse = response.getRawBody();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(QBEventType.class, new QBEventTypeDeserializer());
        gsonBuilder.registerTypeAdapter(QBNotificationChannel.class, new QBNotificationChannelDeserializer());
        Gson gson = gsonBuilder.create();

        Type type = new TypeToken<ArrayList<QBEventWrap>>() {}.getType();

        ArrayList<QBEventWrap> eventWraps = gson.fromJson(stringToParse, type);

        for (QBEventWrap wrap : eventWraps) {
            items.add(wrap.getEvent());
        }
    }

    //

    public ArrayList<QBEvent> getEvents() {
        return items;
    }

    public void setEvents(ArrayList<QBEvent> events) {
        this.items = events;
    }
}