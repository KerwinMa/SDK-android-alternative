package com.quickblox.android.framework.modules.messages.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.messages.definitions.Consts;
import com.quickblox.android.framework.modules.messages.models.QBEvent;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 20:33
 */
public class QueryDeleteEvent extends Query {

    QBEvent event;

    public QueryDeleteEvent(QBEvent event) {
        this.event = event;
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.EVENTS_ENDPOINT, event.getId());
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

    public QBEvent getEvent() {
        return event;
    }

    public void setEvent(QBEvent event) {
        this.event = event;
    }
}