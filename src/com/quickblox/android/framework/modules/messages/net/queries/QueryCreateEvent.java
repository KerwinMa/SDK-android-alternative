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
 * Date: 26.09.12
 * Time: 16:01
 */
public class QueryCreateEvent extends Query {

    private QBEvent event;

    public QueryCreateEvent(QBEvent event) {
        this.event = event;
        setOriginalObject(event);
    }

    @Override
    protected void setParams(RestRequest request) {
        super.setParams(request);
        Map<String, String> parametersMap = request.getParameters();

        putValue(parametersMap, Consts.EVENT_NOTIFICATION_TYPE, event.getNotificationType());
        putValue(parametersMap, Consts.EVENT_ENVIRONMENT, event.getEnvironment());

        putValue(parametersMap, Consts.EVENET_USER_IDS, event.getUserIds().getItemsAsStringOrNull());
        putValue(parametersMap, Consts.EVENET_USER_TAGS_ALL, event.getUserTagsAll().getItemsAsStringOrNull());
        putValue(parametersMap, Consts.EVENET_USER_TAGS_ANY, event.getUserTagsAny().getItemsAsStringOrNull());
        putValue(parametersMap, Consts.EVENET_USER_TAGS_EXCLUDE, event.getUserTagsExclude().getItemsAsStringOrNull());

        putValue(parametersMap, Consts.EVENT_PUSH_TYPE, event.getPushType());
        putValue(parametersMap, Consts.EVENT_MESSAGE, event.getMessageEncoded());
        putValue(parametersMap, Consts.EVENT_ACTIVE, event.isActive());
        putValue(parametersMap, Consts.EVENT_DATE, event.getDate());
        putValue(parametersMap, Consts.EVENT_END_DATE, event.getEndDate());
        putValue(parametersMap, Consts.EVENT_PERIOD, event.getPeriod());
        putValue(parametersMap, Consts.EVENT_NAME, event.getName());
        putValue(parametersMap, Consts.EVENT_TYPE, event.getType());
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.EVENTS_ENDPOINT);
    }

    @Override
    public Class getResultClass() {
        return QBEventResult.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }
}