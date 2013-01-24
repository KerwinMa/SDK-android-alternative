package com.quickblox.android.framework.base.definitions;

public class ConstsGlobal {

    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String REQUEST_FORMAT = ".json";

    public static final String API_DOMAIN = "api.";
    public static final String SERVER_DOMAIN = "quickblox.com";
    public static final String REST_API_VERSION = "0.1.1";

    // headers
    public static final String HEADER_API_VERSION = "QuickBlox-REST-API-Version";
    public static final String HEADER_TOKEN = "QB-Token";
    public static final String HEADER_FRAMEWORK_VERSION = "QB-SDK";
    public static final String HEADER_FRAMEWORK_VERSION_VALUE_PREFIX = "Android";

    // exceptions
    public static final String EXCEPTION_MISSED_AUTHORIZATION =
            "\nYou have missed the authorization call.\n"
                    + "Please insert following code inside your application:\n"
                    + "    QBAuth.authorizeApp(new QBCallback() { ... });\n"
                    + "before any other code, that uses our service. Thank you.";

    public static final String EXCEPTION_MISSED_APP_ID =
            "\nYou have missed the APPLICATION ID parameter for QBSettings.\n"
                    + "Please set parameter using following code:\n"
                    + "    QBSettings.getInstance().setApplicationId(\"123\");\n"
                    + "You can find desired value on your app settings page in QB Admin Panel (http://admin.quickblox" +
                    ".com/apps/<app_id>/edit).";

    public static final String EXCEPTION_MISSED_AUTH_KEY =
            "\nYou have missed the AUTHORIZATION KEY parameter for QBSettings.\n"
                    + "Please set parameter using following code:\n"
                    + "    QBSettings.getInstance().setAuthorizationKey(\"###############\");\n"
                    + "You can find desired value on your app settings page in QB Admin Panel (http://admin.quickblox" +
                    ".com/apps/<app_id>/edit).";

    public static final String EXCEPTION_MISSED_AUTH_SECRET =
            "\nYou have missed the AUTHORIZATION SECRET parameter for QBSettings.\n"
                    + "Please set parameter using following code:\n"
                    + "    QBSettings.getInstance().setAuthorizationSecret(\"###############\");\n"
                    + "You can find desired value on your app settings page in QB Admin Panel (http://admin.quickblox" +
                    ".com/apps/<app_id>/edit).";

    public static final String EXCEPTION_CONNECTION_FAILED = "Connection failed. Please check your internet " +
            "connection.";

    public static final String BASE_SERVICE_ERROR_TIMEOUT = "Connection closed due to timeout. Please check your internet connection.";
    public static final String BASE_SERVICE_ERROR_NOT_FOUND = "Entity you are looking for was not found.";
    public static final String BASE_SERVICE_ERROR_INTERNAL_SERVER_ERROR = "Internal Server AmazonError. Please contact the server administrator.";
    public static final String BASE_SERVICE_ERROR_VALIDATION = "Unable to perform requested action due to validation errors.";
    public static final String BASE_SERVICE_ERROR_UNAUTHORIZED = "Unable to perform requested action: authorization failed.";
    public static final String BASE_SERVICE_ERROR_UNKNOWN_CONTENT_TYPE = "Unknown content type returned (%s)";
    public static final String BASE_SERVICE_ERROR_UNEXPECTED_STATUS = "Unexpected status code returned (%d) while code (%d) was expected";
    public static final String BASE_SERVICE_ERROR_UNEXPECTED_CONTENT_TYPE = "Unexpected content type of the models (%s) while (%s) was expected";

    public static final String EMPTY_STRING = "";
}