package com.quickblox.android.framework.modules.messages.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.messages.models.QBPushTokenWrap;
import com.quickblox.android.framework.modules.messages.models.QBEnvironment;
import com.quickblox.android.framework.modules.messages.models.QBPushToken;
import com.quickblox.android.framework.modules.messages.models.deserializers.QBEnvironmentDeserializer;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 11:11
 */
public class QBPushTokenResult extends Result {

    Lo lo = new Lo(this);

    private QBPushTokenWrap pushTokenWrap;

    QBPushToken pushToken;

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {

            extractEntity();

            // Copy fields
            QBPushToken originalObject = (QBPushToken) getQuery().getOriginalObject();
            pushToken.copyFieldsTo(originalObject);
        }
    }

    @Override
    public void extractEntity() {
        String stringToParse = response.getRawBody();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(QBEnvironment.class, new QBEnvironmentDeserializer());
        Gson gson = gsonBuilder.create();
        pushTokenWrap = gson.fromJson(stringToParse, QBPushTokenWrap.class);
        pushToken = pushTokenWrap.getPushToken();
    }

    public QBPushToken getPushToken() {
        return pushToken;
    }
}