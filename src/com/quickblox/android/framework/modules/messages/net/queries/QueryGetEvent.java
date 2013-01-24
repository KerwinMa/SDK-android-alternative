package com.quickblox.android.framework.modules.messages.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.messages.definitions.Consts;
import com.quickblox.android.framework.modules.messages.models.QBEvent;
import com.quickblox.android.framework.modules.messages.net.results.QBEventResult;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 16:37
 */
public class QueryGetEvent extends Query {

    QBEvent event;

    public QueryGetEvent(QBEvent event) {
        this.event = event;
        setOriginalObject(event);
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
        request.setMethod(RestMethod.GET);
    }

    //

    public QBEvent getEvent() {
        return event;
    }

    public void setEvent(QBEvent event) {
        this.event = event;
    }
}