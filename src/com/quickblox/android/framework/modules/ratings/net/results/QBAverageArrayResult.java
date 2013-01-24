package com.quickblox.android.framework.modules.ratings.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.ArrayResult;
import com.quickblox.android.framework.modules.ratings.models.QBAverage;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 11:36
 */
public class QBAverageArrayResult extends ArrayResult<QBAverage> {

    private Lo lo = new Lo(this);

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();

            lo.g(LOG_MSG_OBJ_PARSED + items);
        }
    }

    public void extractEntity() {
        String stringToParse = response.getRawBody();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Type type = new TypeToken<ArrayList<QBAverage>>() {}.getType();

        items = gson.fromJson(stringToParse, type);
    }

    //

    public ArrayList<QBAverage> getAverages() {
        return items;
    }

    public void setAverages(ArrayList<QBAverage> avareges) {
        this.items = items;
    }
}