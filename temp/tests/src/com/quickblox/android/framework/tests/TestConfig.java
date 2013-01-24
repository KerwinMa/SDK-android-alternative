package com.quickblox.android.framework.tests;

import com.quickblox.android.framework.base.models.QBSettings;
import com.quickblox.android.framework.base.definitions.ServerZone;
import com.quickblox.android.framework.base.definitions.TransferProtocol;

/**
 * User: Oleg Soroka
 * Date: 01.10.12
 * Time: 18:12
 */
public class TestConfig {

    public static String OWNER_LOGIN = "qbsdk";
    public static String OWNER_PASSWORD = "freedom334";

    public static String USER_LOGIN = "test";
    public static String USER_PASSWORD = "testtest";

    // Credentials for app 774 ("qbsdk tests")
    // http://admin.quickblox.com/
    // qbsdk / freedom334
    public static final String[] PRODUCTION_CONFIG = {
            "774",              // app id
            "aY7WwSRmu2-GbfA",  // auth key
            "ah2NKY-YEqkqUkp"   // auth secret
    };

    // Credentials for app 319 ("qbsdk tests")
    // http://admin.stage.quickblox.com/
    // qbsdk / freedom334
    public static final String[] STAGE_CONFIG = {
            "319",              // app id
            "LmZveVCEWzHxuFd",  // auth key
            "ufp4hrf3xP2AnUP"   // auth secret
    };

    public static void loadStageConfig() {
        loadGenericConfig(STAGE_CONFIG);

        QBSettings.getInstance().setServerZone(ServerZone.STAGE);
        QBSettings.getInstance().setTransferProtocol(TransferProtocol.HTTP);  // by default HTTPS
    }

    public static void loadProductionConfig() {
        loadGenericConfig(PRODUCTION_CONFIG);
    }

    public static void loadGenericConfig(String[] config) {
        QBSettings.getInstance().setApplicationId(config[0]);
        QBSettings.getInstance().setAuthorizationKey(config[1]);
        QBSettings.getInstance().setAuthorizationSecret(config[2]);
        QBSettings.getInstance().setSynchronous(true);                        // by default FALSE
    }
}