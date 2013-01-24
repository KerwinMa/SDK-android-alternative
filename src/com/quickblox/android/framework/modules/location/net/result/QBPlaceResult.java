package com.quickblox.android.framework.modules.location.net.result;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.location.models.QBPlace;
import com.quickblox.android.framework.modules.location.models.QBPlaceWrap;

/**
 * User: Oleg Soroka
 * Date: 17.08.12
 * Time: 21:23
 */
public class QBPlaceResult extends Result {

    private Lo lo = new Lo(this);

    private QBPlace place;

    public QBPlace getPlace() {
        return place;
    }

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {

            extractEntity();

            // Copy fields
            QBPlace originalObject = (QBPlace) getQuery().getOriginalObject();
            place.copyFieldsTo(originalObject);

            lo.g(LOG_MSG_OBJ_PARSED + place);
        }
    }

    @Override
    protected void extractEntity() {
        String stringToParse = response.getRawBody();
        Gson gson = new Gson();
        QBPlaceWrap placeWrap = gson.fromJson(stringToParse, QBPlaceWrap.class);
        place = placeWrap.getPlace();
    }
}