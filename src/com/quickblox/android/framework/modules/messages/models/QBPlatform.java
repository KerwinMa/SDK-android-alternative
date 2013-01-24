package com.quickblox.android.framework.modules.messages.models;

/**
 * User: Oleg Soroka
 * Date: 20.09.12
 * Time: 12:25
 */
public enum QBPlatform {

    ANDROID("android"),
    IOS("ios"),
    WINDOWS_PHONE("windows_phone"),
    BLACKBERRY("blackberry");

    private String caption;

    private QBPlatform(String caption) {
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