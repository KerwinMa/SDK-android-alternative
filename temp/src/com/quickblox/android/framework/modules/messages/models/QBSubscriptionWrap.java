package com.quickblox.android.framework.modules.messages.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntity;

/**
 * User: Oleg Soroka
 * Date: 20.09.12
 * Time: 12:23
 */
public class QBSubscriptionWrap extends QBEntity {

    @SerializedName("subscription")
    QBSubscription subscription;

    public QBSubscriptionWrap() {
    }

    public QBSubscription getSubscription() {
        return subscription;
    }

    public void setSubscription(QBSubscription subscription) {
        this.subscription = subscription;
    }
}
