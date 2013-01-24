package com.quickblox.android.framework.modules.messages.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.messages.definitions.Consts;
import com.quickblox.android.framework.modules.messages.models.QBPushToken;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 14:11
 */
public class QueryDeletePushToken extends Query {

    QBPushToken pushToken;

    public QueryDeletePushToken(QBPushToken pushToken) {
        this.pushToken = pushToken;
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.PUSH_TOKEN_ENDPOINT, pushToken.getId().toString());
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }

    public QBPushToken getPushToken() {
        return pushToken;
    }

    public void setPushToken(QBPushToken pushToken) {
        this.pushToken = pushToken;
    }
}