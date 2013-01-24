package com.quickblox.android.framework.modules.location.net.queries;

import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.location.models.QBPlace;
import com.quickblox.android.framework.modules.location.definitions.Consts;
import com.quickblox.android.framework.modules.location.net.result.QBPlaceResult;

import java.util.Map;

/**
 * @author: Oleg Soroka
 * Date: 17.07.12
 * Time: 14:47
 */

public class QueryBaseCreateUpdatePlace extends QueryBasePlace {

    public QueryBaseCreateUpdatePlace(QBPlace place) {
        super(place);
    }

    @Override
    public Class getResultClass() {
        return QBPlaceResult.class;
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.PLACE_PHOTO_ID, place.getPhotoId());
        putValue(parametersMap, Consts.PLACE_LOCATION_ID, place.getLocationId());
        putValue(parametersMap, Consts.PLACE_TITLE, place.getTitle());
        putValue(parametersMap, Consts.PLACE_DESCRIPTION, place.getDescription());
        putValue(parametersMap, Consts.PLACE_ADDRESS, place.getAddress());
    }

}