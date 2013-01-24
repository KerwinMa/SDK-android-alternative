package com.quickblox.android.framework.modules.messages.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.quickblox.android.framework.base.net.results.ArrayResult;
import com.quickblox.android.framework.modules.messages.models.QBNotificationChannel;
import com.quickblox.android.framework.modules.messages.models.QBPlatform;
import com.quickblox.android.framework.modules.messages.models.QBSubscription;
import com.quickblox.android.framework.modules.messages.models.QBSubscriptionWrap;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBNotificationChannelDeserializer;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBPlatformDeserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 16:37
 */
public class QBSubscriptionArrayResult extends ArrayResult<QBSubscription> {

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
        gsonBuilder.registerTypeAdapter(QBPlatform.class, new QBPlatformDeserializer());
        gsonBuilder.registerTypeAdapter(QBNotificationChannel.class, new QBNotificationChannelDeserializer());
        Gson gson = gsonBuilder.create();

        Type type = new TypeToken<ArrayList<QBSubscriptionWrap>>() {}.getType();
        ArrayList<QBSubscriptionWrap> subscriptionWraps = gson.fromJson(stringToParse, type);
        for (QBSubscriptionWrap sw : subscriptionWraps) {
            items.add(sw.getSubscription());
        }
    }

    //

    public ArrayList<QBSubscription> getSubscriptions() {
        return items;
    }

    public void setSubscriptions(ArrayList<QBSubscription> subscriptions) {
        items = subscriptions;
    }
}