package com.quickblox.android.framework.modules.ratings.net.results;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameterValue;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameterValueWrap;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 11:55
 */
public class QBGameModeParameterValueResult extends Result {

    private Lo lo = new Lo(this);

    private QBGameModeParameterValue item;

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {

            extractEntity();

            // Copy fields
            QBGameModeParameterValue originalObject = (QBGameModeParameterValue) getQuery().getOriginalObject();
            item.copyFieldsTo(originalObject);

            lo.g(LOG_MSG_OBJ_PARSED + item);
        }
    }

    public void extractEntity() {
        String stringToParse = response.getRawBody();

        Gson gson = new Gson();
        QBGameModeParameterValueWrap itemWrap = gson.fromJson(stringToParse, QBGameModeParameterValueWrap.class);

        item = itemWrap.getGameModeParameterValue();
    }

    //

    public QBGameModeParameterValue getGameModeParameterValue() {
        return item;
    }
}