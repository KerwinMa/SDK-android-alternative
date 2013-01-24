package com.quickblox.android.framework.modules.location.net.result;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.models.QBLocationWrap;

/**
 * @author: Daniel Goncharov
 * Date: 17.07.12
 * Time: 15:18
 */
public class QBLocationResult extends Result {

    private Lo lo = new Lo(this);

    private QBLocation location;

    public QBLocation getLocation() {
        return location;
    }

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();

            // Copy fields
            QBLocation originalObject = (QBLocation) getQuery().getOriginalObject();
            location.copyFieldsTo(originalObject);

            lo.g(LOG_MSG_OBJ_PARSED + location);
        }
    }

    @Override
    protected void extractEntity() {
        String stringToParse = response.getRawBody();
        Gson gson = new Gson();
        QBLocationWrap locationWrap = gson.fromJson(stringToParse, QBLocationWrap.class);
        location = locationWrap.getEntity();
    }
}