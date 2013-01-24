/**
 *
 */
package com.quickblox.android.framework.base.models;

import com.quickblox.android.framework.base.definitions.ConstsGlobal;
import com.quickblox.android.framework.base.definitions.LogLevel;
import com.quickblox.android.framework.base.definitions.ServerZone;
import com.quickblox.android.framework.base.definitions.TransferProtocol;

import java.io.IOException;
import java.util.Properties;

/**
 * User: Igor Khomenko
 */
public final class QBSettings {

    // versionCode and versionName has been setted here, because of there is no way to get
    // app context inside library project,
    // look at http://stackoverflow.com/questions/9175695/android-library-project-how-to-get-context
    // these variables stores in file "version.properties"
    private String versionCode;
    private String versionName;

    private String applicationId;
    private String authorizationKey;
    private String authorizationSecret;

    private TransferProtocol transferProtocol = TransferProtocol.HTTPS;
    private LogLevel logLevel = LogLevel.DEBUG;
    private String serverDomain = ConstsGlobal.SERVER_DOMAIN;
    private ServerZone serverZone = ServerZone.PRODUCTION;
    private String restApiVersion = ConstsGlobal.REST_API_VERSION;
    private boolean synchronous = false;

    static QBSettings qbSettings;

    private QBSettings() {
        loadProperties();
    }

    public static synchronized QBSettings getInstance() {
        if (qbSettings == null) {
            qbSettings = new QBSettings();
        }
        return qbSettings;
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("version.properties"));
            versionCode = properties.getProperty("versionCode", "0");
            versionName = properties.getProperty("versionName", "0.0.0");
        } catch (IOException e) {
            e.printStackTrace();
            versionCode = "0";
            versionName = "0.0.0";
        }
    }

    //

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public QBSettings setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public String getServerDomain() {
        return serverDomain;
    }

    public QBSettings setServerDomain(String serverDomain) {
        this.serverDomain = serverDomain;
        return this;
    }

    public ServerZone getServerZone() {
        return serverZone;
    }

    public QBSettings setServerZone(ServerZone serverZone) {
        this.serverZone = serverZone;
        return this;
    }

    public String getApiVersion() {
        return restApiVersion;
    }

    public QBSettings setApiVersion(String restApiVersion) {
        this.restApiVersion = restApiVersion;
        return this;
    }

    public String getFinalDestination() {
        return serverZone + getServerDomain();
    }

    public String getAuthorizationKey() {
        return authorizationKey;
    }

    public QBSettings setAuthorizationKey(String authKey) {
        this.authorizationKey = authKey;
        return this;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public QBSettings setApplicationId(String applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public String getAuthorizationSecret() {
        return authorizationSecret;
    }

    public QBSettings setAuthorizationSecret(String authSecret) {
        this.authorizationSecret = authSecret;
        return this;
    }

    public QBSettings setTransferProtocol(TransferProtocol protocol) {
        this.transferProtocol = protocol;
        return this;
    }

    public TransferProtocol getTransferProtocol() {
        return transferProtocol;
    }

    public QBSettings setSynchronous(boolean synchronous) {
        this.synchronous = synchronous;
        return this;
    }

    public Boolean isSynchronous() {
        return synchronous;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    @Override
    public String toString() {
        return "QBSettings{" +
                "applicationId=" + applicationId +
                ", authorizationKey='" + authorizationKey + '\'' +
                ", authorizationSecret='" + authorizationSecret + '\'' +
                ", transferProtocol=" + transferProtocol +
                ", logLevel=" + logLevel +
                ", serverDomain='" + serverDomain + '\'' +
                ", serverZone=" + serverZone +
                ", restApiVersion='" + restApiVersion + '\'' +
                ", synchronous=" + synchronous +
                '}';
    }

    public QBSettings fastConfigInit(String appId, String authKey, String authSecret) {
        this.applicationId = appId;
        this.authorizationKey = authKey;
        this.authorizationSecret = authSecret;
        return this;
    }
}