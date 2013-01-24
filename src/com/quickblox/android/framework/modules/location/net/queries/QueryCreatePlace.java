package com.quickblox.android.framework.modules.location.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.location.definitions.Consts;
import com.quickblox.android.framework.modules.location.models.QBPlace;

/**
 * @author: Oleg Soroka
 * Date: 17.07.12
 * Time: 14:47
 */

public class QueryCreatePlace extends QueryBaseCreateUpdatePlace {

    public QueryCreatePlace(QBPlace place) {
        super(place);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.PLACE_ENDPOINT);
    }
}