package com.quickblox.android.framework.modules.location.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.location.definitions.Consts;
import com.quickblox.android.framework.modules.location.models.QBPlace;

/**
 * @author: Oleg Soroka
 * Date: 17.07.12
 * Time: 14:47
 */

public class QueryDeletePlace extends QueryBasePlace {

    public QueryDeletePlace(QBPlace place) {
        super(place);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.PLACE_ENDPOINT, place.getId());
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }
}