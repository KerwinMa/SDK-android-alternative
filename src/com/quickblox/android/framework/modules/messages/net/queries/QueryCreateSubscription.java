package com.quickblox.android.framework.modules.messages.net.queries;

import android.text.TextUtils;
import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.messages.definitions.Consts;
import com.quickblox.android.framework.modules.messages.models.QBNotificationChannels;
import com.quickblox.android.framework.modules.messages.models.QBSubscription;
import com.quickblox.android.framework.modules.messages.net.results.QBSubscriptionArrayResult;

import java.util.Map;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 16:32
 */
public class QueryCreateSubscription extends Query {

    QBNotificationChannels notificationChannels;
    QBSubscription subscription;

    public QueryCreateSubscription(QBSubscription subscription) {
        this.subscription = subscription;
        setOriginalObject(subscription);
    }

    public QueryCreateSubscription(QBNotificationChannels notificationChannels) {
        this.notificationChannels = notificationChannels;
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.SUBSCRIPTIONS_ENDPOINT);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        String allChannels = "";
        if (subscription != null) {
            allChannels = subscription.getNotificationChannel().toString();
        } else if (notificationChannels != null) {
            allChannels = TextUtils.join(",", notificationChannels);
        }
        putValue(parametersMap, Consts.NOTIFICATION_CHANNEL, allChannels);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    public Class getResultClass() {
        return QBSubscriptionArrayResult.class;
    }

    //

    public QBSubscription getSubscription() {
        return subscription;
    }

    public void setSubscription(QBSubscription subscription) {
        this.subscription = subscription;
    }

    public QBNotificationChannels getNotificationChannels() {
        return notificationChannels;
    }

    public void setNotificationChannels(QBNotificationChannels notificationChannels) {
        this.notificationChannels = notificationChannels;
    }
}
