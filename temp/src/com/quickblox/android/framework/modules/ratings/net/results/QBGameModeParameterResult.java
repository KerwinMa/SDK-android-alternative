package com.quickblox.android.framework.modules.ratings.net.results;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameter;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameterWrap;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 11:50
 */
public class QBGameModeParameterResult extends Result {

    private Lo lo = new Lo(this);

    private QBGameModeParameter item;

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {

            extractEntity();

            // Copy fields
            QBGameModeParameter originalObject = (QBGameModeParameter) getQuery().getOriginalObject();
            item.copyFieldsTo(originalObject);

            lo.g(LOG_MSG_OBJ_PARSED + item);
        }
    }

    public void extractEntity() {
        String stringToParse = response.getRawBody();

        Gson gson = new Gson();
        QBGameModeParameterWrap itemWrap = gson.fromJson(stringToParse, QBGameModeParameterWrap.class);

        item = itemWrap.getGameModeParameter();
    }

    //

    public QBGameModeParameter getGameModeParameter() {
        return item;
    }
}