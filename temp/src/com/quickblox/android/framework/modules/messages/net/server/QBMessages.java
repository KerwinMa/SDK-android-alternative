package com.quickblox.android.framework.modules.messages.net.server;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.requests.QBPagedRequestBuilder;
import com.quickblox.android.framework.modules.messages.models.*;
import com.quickblox.android.framework.modules.messages.net.queries.*;

/**
 * User: Oleg Soroka
 * Date: 19.09.12
 * Time: 18:36
 */
public class QBMessages {

    /* Push token */

    // Create push token

    public static void createPushToken(QBPushToken pushToken, QBCallback callback) {
        QueryCreatePushToken query = new QueryCreatePushToken(pushToken);
        query.performAsyncWithCallback(callback);
    }

    public static void createPushToken(QBEnvironment environment, String cis, QBCallback callback) {
        QueryCreatePushToken query = new QueryCreatePushToken(environment, cis);
        query.performAsyncWithCallback(callback);

    }

    // Delete push token

    public static void deletePushToken(QBPushToken pushToken, QBCallback callback) {
        QueryDeletePushToken query = new QueryDeletePushToken(pushToken);
        query.performAsyncWithCallback(callback);
    }

    public static void deletePushToken(int pushTokenId, QBCallback callback) {
        QueryDeletePushToken query = new QueryDeletePushToken(new QBPushToken(pushTokenId));
        query.performAsyncWithCallback(callback);
    }

    /* Subscription */

    // Create subscription

    public static void createSubscription(QBSubscription qbSubscription, QBCallback callback) {
        QueryCreateSubscription query = new QueryCreateSubscription(qbSubscription);
        query.performAsyncWithCallback(callback);
    }


    public static void createSubscription(QBNotificationChannels notificationChannels, QBCallback callback) {
        QueryCreateSubscription query = new QueryCreateSubscription(notificationChannels);
        query.performAsyncWithCallback(callback);
    }

    public static void createSubscription(QBNotificationChannel notificationChannel, QBCallback callback) {
        QueryCreateSubscription query = new QueryCreateSubscription(new QBNotificationChannels(notificationChannel));
        query.performAsyncWithCallback(callback);
    }

    // Get subscriptions

    public static void getSubscriptions(QBCallback callback) {
        QueryGetSubscriptionArray query = new QueryGetSubscriptionArray();
        query.performAsyncWithCallback(callback);
    }

    // Delete subscription

    public static void deleteSubscription(QBSubscription subscription, QBCallback callback) {
        QueryDeleteSubscription query =
                new QueryDeleteSubscription(subscription);
        query.performAsyncWithCallback(callback);
    }

    public static void deleteSubscription(int subscriptionId, QBCallback callback) {
        deleteSubscription(new QBSubscription((subscriptionId)), callback);
    }

    /* Events */

    // Create event

    public static void createEvent(QBEvent event, QBCallback callback) {
        QueryCreateEvent query = new QueryCreateEvent(event);
        query.performAsyncWithCallback(callback);
    }

    // Get events

    public static void getEvents(QBPagedRequestBuilder requestBuilder, QBCallback callback) {
        QueryGetEventArray query = new QueryGetEventArray(requestBuilder);
        query.performAsyncWithCallback(callback);
    }

    public static void getEvents(QBCallback callback) {
        getEvents(null, callback);
    }

    public static void getEvent(QBEvent event, QBCallback callback) {
        QueryGetEvent query = new QueryGetEvent(event);
        query.performAsyncWithCallback(callback);
    }

    public static void getEvent(int eventId, QBCallback callback) {
        getEvent(new QBEvent(eventId), callback);
    }

    public static void getPullEvents(QBCallback callback) {
        QueryGetPullEventArray query = new QueryGetPullEventArray();
        query.performAsyncWithCallback(callback);
    }

    // Update event

    // Just for fields active, message, date, preiod, name.
    public static void updateEvent(QBEvent event, QBCallback callback) {
        QueryUpdateEvent query = new QueryUpdateEvent(event);
        query.performAsyncWithCallback(callback);
    }

    // Delete event

    public static void deleteEvent(int eventId, QBCallback callback) {
        deleteEvent(new QBEvent(eventId), callback);
    }

    public static void deleteEvent(QBEvent event, QBCallback callback) {
        QueryDeleteEvent query = new QueryDeleteEvent(event);
        query.performAsyncWithCallback(callback);
    }

    /* Others */

    // Retrieve CIS

    private static void retrieveCis() {
        // TODO важно! инкапуслировать получение CIS внутри фреймворка
    }
}