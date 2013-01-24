package com.quickblox.android.framework.base.definitions;

/**
 * User: Oleg Soroka
 * Date: 02.10.12
 * Time: 22:35
 */
public enum LogLevel {

    NOTHING("nothing"),
    DEBUG("debug");

    private String caption;

    private LogLevel(String caption) {
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