package com.quickblox.android.framework.modules.messages.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.messages.definitions.Consts;
import com.quickblox.android.framework.modules.messages.models.QBEvent;
import com.quickblox.android.framework.modules.messages.net.results.QBEventResult;

import java.util.Map;

/**
 * User: Oleg Soroka
 * Date: 28.09.12
 * Time: 18:54
 */
public class QueryUpdateEvent extends Query {

    QBEvent event;

    public QueryUpdateEvent(QBEvent event) {
        this.event = event;
        setOriginalObject(event);
    }

    @Override
    protected void setParams(RestRequest request) {
        super.setParams(request);
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.EVENT_ACTIVE, event.isActive());
        putValue(parametersMap, Consts.EVENT_MESSAGE, event.getMessageEncoded());
        putValue(parametersMap, Consts.EVENT_DATE, event.getDate());
        putValue(parametersMap, Consts.EVENT_PERIOD, event.getPeriod());
        putValue(parametersMap, Consts.EVENT_NAME, event.getName());
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.EVENTS_ENDPOINT, event.getId());
    }

    @Override
    public Class getResultClass() {
        return QBEventResult.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.PUT);
    }
}