package com.quickblox.android.framework.modules.ratings.net.results;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.ratings.models.QBAverage;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 11:36
 */
public class QBAverageResult extends Result {

    private Lo lo = new Lo(this);

    private QBAverage average;

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();

            lo.g(LOG_MSG_OBJ_PARSED + average);
        }
    }

    @Override
    protected void extractEntity() {
        String stringToParse = response.getRawBody();
        Gson gson = new Gson();

        average = gson.fromJson(stringToParse, QBAverage.class);
    }

    //

    public QBAverage getAverage() {
        return average;
    }
}