package com.quickblox.android.framework.modules.messages.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickblox.android.framework.base.net.results.PagedResult;
import com.quickblox.android.framework.modules.messages.models.*;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBEnvironmentDeserializer;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBEventTypeDeserializer;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBNotificationChannelDeserializer;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBTagsQueryDeserializer;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 17:52
 */
public class QBEventPagedResult extends PagedResult {

    private ArrayList<QBEvent> events = new ArrayList<QBEvent>();

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
        gsonBuilder.registerTypeAdapter(QBEnvironment.class, new QBEnvironmentDeserializer());
        gsonBuilder.registerTypeAdapter(QBTagsQuery.class, new QBTagsQueryDeserializer());
        gsonBuilder.registerTypeAdapter(QBNotificationChannel.class, new QBNotificationChannelDeserializer());
        Gson gson = gsonBuilder.create();

        QBEventPaged eventPaged = gson.fromJson(stringToParse, QBEventPaged.class);
        currentPage = eventPaged.getCurrentPage();
        perPage = eventPaged.getPerPage();
        totalEntries = eventPaged.getTotalEntries();

        for (QBEventWrap1 ew : eventPaged.getItems()) {
            events.add(ew.getEvent());
        }
    }

    //

    public ArrayList<QBEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<QBEvent> events) {
        this.events = events;
    }
}