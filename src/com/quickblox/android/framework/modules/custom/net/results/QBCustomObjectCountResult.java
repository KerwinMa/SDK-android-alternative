package com.quickblox.android.framework.modules.custom.net.results;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.custom.models.QBCustomObjectsCountWrap;

/**
 * User: Oleg Soroka
 * Date: 14.08.12
 * Time: 17:44
 */
public class QBCustomObjectCountResult extends Result {

    private Lo lo = new Lo(this);

    private int count;

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();
        }

        lo.g(LOG_MSG_OBJ_PARSED + count);
    }

    @Override
    protected void extractEntity() {
        String stringToParse = response.getRawBody();
        Gson gson = new Gson();

//        QBCustomObjectsCount customObjectsCount = gson.fromJson(stringToParse, QBCustomObjectsCount.class);
//        count = customObjectsCount.getCount();

        QBCustomObjectsCountWrap customObjectsCountWrap = gson.fromJson(stringToParse, QBCustomObjectsCountWrap.class);
        count = customObjectsCountWrap.getCustomObjectsCount().getCount();
    }

    //

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}