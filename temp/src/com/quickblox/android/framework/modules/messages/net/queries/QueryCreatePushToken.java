package com.quickblox.android.framework.modules.messages.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.messages.definitions.Consts;
import com.quickblox.android.framework.modules.messages.models.QBEnvironment;
import com.quickblox.android.framework.modules.messages.models.QBPushToken;
import com.quickblox.android.framework.modules.messages.net.results.QBPushTokenResult;

import java.util.Map;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 10:58
 */
public class QueryCreatePushToken extends Query {

    QBPushToken pushToken;

    public QueryCreatePushToken(QBPushToken pushToken) {
        this.pushToken = pushToken;
        setOriginalObject(pushToken);
    }

    public QueryCreatePushToken(QBEnvironment environment, String cis) {
        this(new QBPushToken(environment, cis));
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.PUSH_TOKEN_ENDPOINT);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.ENVIRONMENT, pushToken.getEnvironment().toString());
        putValue(parametersMap, Consts.CIS, pushToken.getCis());
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    public Class getResultClass() {
        return QBPushTokenResult.class;
    }

    //

    public QBPushToken getPushToken() {
        return pushToken;
    }

    public void setPushToken(QBPushToken pushToken) {
        this.pushToken = pushToken;
    }
}
