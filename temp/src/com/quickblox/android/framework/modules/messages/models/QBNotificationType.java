package com.quickblox.android.framework.modules.messages.models;

/**
 * User: Oleg Soroka
 * Date: 20.09.12
 * Time: 12:30
 */
public enum QBNotificationType {

    PUSH("push"),
    EMAIL("email"),
    REQUEST("request"),
    PULL("pull");

    private String caption;

    private QBNotificationType(String caption) {
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