package com.quickblox.android.framework.modules.messages.models;

/**
 * User: Oleg Soroka
 * Date: 20.09.12
 * Time: 12:24
 */
public enum QBNotificationChannel {

    GCM("gcm"),
    APNS("apns"),
    EMAIL("email"),
    PULL("pull");

    private String caption;

    private QBNotificationChannel(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}