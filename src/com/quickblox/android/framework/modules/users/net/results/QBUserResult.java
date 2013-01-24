package com.quickblox.android.framework.modules.users.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.helpers.StringifyArrayList;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.modules.users.models.QBUserWrap;
import com.quickblox.android.framework.modules.users.models.deserializers.QBStringifyArrayListDeserializer;

/**
 * QBUser: Daniel Goncharov
 * Date: 04.07.12
 * Time: 15:37
 */
public class QBUserResult extends Result {

    Lo lo = new Lo(this);

    QBUser user;

    public QBUser getUser() {
        return user;
    }

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) { // nt добавить везде этот метод
            extractEntity();

            // Copy fields
            QBUser originalObject = (QBUser) getQuery().getOriginalObject();
            user.copyFieldsTo(originalObject);

            lo.g(LOG_MSG_OBJ_PARSED + user);
        }
    }

    @Override
    protected void extractEntity() {
        String stringToParse = response.getRawBody();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(StringifyArrayList.class, new QBStringifyArrayListDeserializer());
        Gson gson = gsonBuilder.create();
        QBUserWrap userWrap = gson.fromJson(stringToParse, QBUserWrap.class);
        user = userWrap.getUser();
    }
}