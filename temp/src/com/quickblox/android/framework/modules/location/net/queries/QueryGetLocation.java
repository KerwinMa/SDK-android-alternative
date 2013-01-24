package com.quickblox.android.framework.modules.location.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.location.definitions.Consts;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.net.result.QBLocationResult;

/**
 * User: Oleg Soroka
 * Date: 15.08.12
 * Time: 12:27
 */
public class QueryGetLocation extends QueryBaseLocation {

    public QueryGetLocation(QBLocation location) {
        super(location);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.LOCATION_ENDPOINT, location.getId());
    }

    @Override
    public Class getResultClass() {
        return QBLocationResult.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

}