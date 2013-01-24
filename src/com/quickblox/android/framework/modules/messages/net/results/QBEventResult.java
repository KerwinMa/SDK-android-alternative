package com.quickblox.android.framework.modules.messages.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.messages.models.QBEventType;
import com.quickblox.android.framework.modules.messages.models.QBEventWrap;
import com.quickblox.android.framework.modules.messages.models.QBEnvironment;
import com.quickblox.android.framework.modules.messages.models.QBEvent;
import com.quickblox.android.framework.modules.messages.models.QBNotificationChannel;
import com.quickblox.android.framework.modules.messages.models.QBTagsQuery;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBEnvironmentDeserializer;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBEventTypeDeserializer;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBNotificationChannelDeserializer;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBTagsQueryDeserializer;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 16:39
 */
public class QBEventResult extends Result {

    QBEvent event;

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();

            System.out.println(event);

            QBEvent originalObject = (QBEvent) getQuery().getOriginalObject();
            event.copyFieldsTo(originalObject);
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
        QBEventWrap eventWrap = gson.fromJson(stringToParse, QBEventWrap.class);
        event = eventWrap.getEvent();
    }

    //

    public QBEvent getEvent() {
        return event;
    }

    public void setEvent(QBEvent event) {
        this.event = event;
    }
}