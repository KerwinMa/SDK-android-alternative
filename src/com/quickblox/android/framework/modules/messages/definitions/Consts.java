package com.quickblox.android.framework.modules.messages.definitions;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 11:00
 */
public class Consts {

    public static String PUSH_TOKEN_ENDPOINT = "push_tokens";

    public static String ENVIRONMENT = "push_token[environment]";
    public static String CIS = "push_token[client_identification_sequence]";

    public static final String SUBSCRIPTIONS_ENDPOINT = "subscriptions";
    public static final String NOTIFICATION_CHANNEL = "notification_channels";
    public static final String EVENTS_ENDPOINT = "events";
    public static final String PULL_EVENTS_ENDPOINT = "pull_events";

    public static final String MESSAGE_BASE64_PREFIX = "data.message=";

    // create params

    public static final String EVENT_ACTIVE = "event[active]";
    public static final String EVENT_NOTIFICATION_TYPE = "event[notification_type]";
    public static final String EVENT_PUSH_TYPE = "event[push_type]";
    public static final String EVENT_ENVIRONMENT = "event[environment]";
    public static final String EVENT_MESSAGE = "event[message]";
    public static final String EVENT_DATE = "event[date]";
    public static final String EVENT_END_DATE = "event[end_date]";
    public static final String EVENT_PERIOD = "event[period]";
    public static final String EVENT_NAME = "event[name]";
    public static final String EVENT_TYPE = "event[event_type]";
    public static final String EVENET_USER_IDS = "event[user][ids]";
    public static final String EVENET_USER_TAGS_ANY = "event[user][tags][any]";
    public static final String EVENET_USER_TAGS_ALL = "event[user][tags][all]";
    public static final String EVENET_USER_TAGS_EXCLUDE = "event[user][tags][exclude]";

}