/**
 *
 */
package com.quickblox.android.framework.base.net.server;

import com.quickblox.android.framework.base.models.QBSettings;
import com.quickblox.android.framework.base.definitions.ConstsGlobal;
import com.quickblox.android.framework.base.definitions.exceptions.BaseServiceException;

/**
 * User: Igor Khomenko
 */
public class BaseService {

    private static BaseService baseService;
    private String token;

    protected BaseService() {
    }

    public static synchronized BaseService getBaseService() throws BaseServiceException {
        if (baseService == null) {
            String description =
                    String.format(ConstsGlobal.EXCEPTION_MISSED_AUTHORIZATION, BaseService.class.getName());
            throw new BaseServiceException(description);
        }
        return baseService;
    }

    protected static void createBaseService() {
        if (baseService == null) {
            baseService = new BaseService();
        }
    }

    public void resetCredentials() {
        token = null;
    }

    public static String getServiceEndpointURL() {
        return String.format("%s://%s%s", QBSettings.getInstance().getTransferProtocol(), ConstsGlobal.API_DOMAIN,
                QBSettings.getInstance().getFinalDestination());
    }

    //

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}