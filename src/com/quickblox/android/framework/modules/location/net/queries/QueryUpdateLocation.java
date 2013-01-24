package com.quickblox.android.framework.modules.location.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.location.definitions.Consts;
import com.quickblox.android.framework.modules.location.models.QBLocation;

/**
 * User: Oleg Soroka
 * Date: 08.08.12
 * Time: 12:57
 */
public class QueryUpdateLocation extends QueryBaseCreateUpdateLocation {

    public QueryUpdateLocation(QBLocation location) {
        super(location);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.PUT);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.LOCATION_ENDPOINT, location.getId());
    }
}