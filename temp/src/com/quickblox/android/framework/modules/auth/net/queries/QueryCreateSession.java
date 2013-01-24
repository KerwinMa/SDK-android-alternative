package com.quickblox.android.framework.modules.auth.net.queries;

import com.quickblox.android.framework.base.definitions.ConstsGlobal;
import com.quickblox.android.framework.base.definitions.exceptions.BaseServiceException;
import com.quickblox.android.framework.base.models.QBSettings;
import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.helpers.SignHelper;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.auth.definitions.Consts;
import com.quickblox.android.framework.modules.auth.net.results.QBSessionResult;
import com.quickblox.android.framework.modules.messages.models.QBDevice;
import com.quickblox.android.framework.modules.users.models.QBUser;

import java.security.SignatureException;
import java.util.Map;
import java.util.Random;

/**
 * User: Igor Khomenko
 */
public class QueryCreateSession extends Query {

    private QBUser user;
    private QBDevice device;

    public QueryCreateSession() {
    }

    public QueryCreateSession(QBDevice device) {
        this.device = device;
    }

    public QueryCreateSession(QBUser user) {
        this.user = user;
    }

    public QueryCreateSession(QBUser user, QBDevice device) {
        this.user = user;
        this.device = device;
    }

    @Override
    public Class getResultClass() {
        return QBSessionResult.class;
    }

    private void signRequest(RestRequest request) {
        String paramsString = request.getParamsOnlyStringNotEncoded();

        String authSecret = QBSettings.getInstance().getAuthorizationSecret();

        if (authSecret != null) {
            try {
                String signature = SignHelper.calculateHMAC_SHA(paramsString, authSecret);
                request.getParameters().put(Consts.SIGNATURE, signature);
            } catch (SignatureException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new BaseServiceException(ConstsGlobal.EXCEPTION_MISSED_AUTH_SECRET);
            } catch (BaseServiceException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void setupRequest(RestRequest request) {
        super.setupRequest(request);
        signRequest(request);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parameters = request.getParameters();

        String appId = QBSettings.getInstance().getApplicationId();
        String authKey = QBSettings.getInstance().getAuthorizationKey();
        int nonce = new Random().nextInt();
        long timestamp = System.currentTimeMillis() / 1000;

        if (appId != null) {
            putValue(parameters, Consts.APP_ID, appId);
        } else {
            try {
                throw new BaseServiceException(ConstsGlobal.EXCEPTION_MISSED_APP_ID);
            } catch (BaseServiceException e) {
                e.printStackTrace();
            }
        }

        if (authKey != null) {
            putValue(parameters, Consts.AUTH_KEY, authKey);
        } else {
            try {
                throw new BaseServiceException(ConstsGlobal.EXCEPTION_MISSED_AUTH_KEY);
            } catch (BaseServiceException e) {
                e.printStackTrace();
            }

        }

        if (device != null) {
            putValue(parameters, Consts.DEVICE_PLATFORM, device.getPlatform().toString());
            putValue(parameters, Consts.DEVICE_UDID, device.getId());
        }
        putValue(parameters, Consts.NONCE, nonce);
        putValue(parameters, Consts.TIMESTAMP, timestamp);
        if (user != null) {
            putValue(parameters, Consts.USER_LOGIN, user.getLogin());
            putValue(parameters, Consts.USER_EMAIL, user.getEmail());
            putValue(parameters, Consts.USER_PASSWORD, user.getPassword());
        }
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.AUTH_ENDPOINT);
    }

    //

    public QBDevice getDevice() {
        return device;
    }

    public void setDevice(QBDevice device) {
        this.device = device;
    }

    public QBUser getUser() {
        return user;
    }

    public void setUser(QBUser user) {
        this.user = user;
    }
}