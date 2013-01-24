package com.quickblox.android.framework.modules.auth.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickblox.android.framework.base.definitions.exceptions.BaseServiceException;
import com.quickblox.android.framework.base.net.rest.RestResponse;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.auth.models.QBSession;
import com.quickblox.android.framework.modules.auth.models.QBSessionWrap;
import com.quickblox.android.framework.modules.auth.net.server.QBAuth;

/**
 * User: Igor Khomenko
 */
public class QBSessionResult extends Result {

    private QBSession session;

    public QBSession getSession() {
        return session;
    }

    @Override
    public void setResponse(RestResponse response) {
        super.setResponse(response);
    }

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();

            try {
                QBAuth.getBaseService().setToken(session.getToken());
            } catch (BaseServiceException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void extractEntity() {
        String stringToParse = response.getRawBody();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        QBSessionWrap sessionWrap = gson.fromJson(stringToParse, QBSessionWrap.class);
        session = sessionWrap.getSession();
    }
}