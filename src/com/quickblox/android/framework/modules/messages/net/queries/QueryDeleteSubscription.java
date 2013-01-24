package com.quickblox.android.framework.modules.messages.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.messages.definitions.Consts;
import com.quickblox.android.framework.modules.messages.models.QBSubscription;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 23:19
 */
public class QueryDeleteSubscription extends Query {

    QBSubscription subscription;

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.SUBSCRIPTIONS_ENDPOINT, subscription.getId());
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }

    //

    public QueryDeleteSubscription(QBSubscription subscription) {
        this.subscription = subscription;
    }

    public QBSubscription getSubscription() {
        return subscription;
    }

    public void setSubscription(QBSubscription subscription) {
        this.subscription = subscription;
    }
}
