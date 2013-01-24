package com.quickblox.android.framework.modules.location.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.location.definitions.Consts;
import com.quickblox.android.framework.modules.location.models.QBPlace;

/**
 * User: Oleg Soroka
 * Date: 20.08.12
 * Time: 15:22
 */
public class QueryUpdatePlace extends QueryBaseCreateUpdatePlace {

    public QueryUpdatePlace(QBPlace place) {
        super(place);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.PUT);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.PLACE_ENDPOINT, place.getId());
    }
}