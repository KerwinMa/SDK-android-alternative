package com.quickblox.android.framework.modules.location.net.queries;

import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.definitions.Consts;
import com.quickblox.android.framework.modules.location.net.result.QBLocationResult;

import java.util.Map;

/**
 * @author: Oleg Soroka
 * Date: 17.07.12
 * Time: 14:47
 */

public class QueryBaseCreateUpdateLocation extends QueryBaseLocation {

    public QueryBaseCreateUpdateLocation(QBLocation location) {
        super(location);
    }

    @Override
    public Class getResultClass() {
        return QBLocationResult.class;
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.LATITUDE, location.getLatitude());
        putValue(parametersMap, Consts.LONGITUDE, location.getLongitude());
        putValue(parametersMap, Consts.STATUS, location.getStatus());
    }

}