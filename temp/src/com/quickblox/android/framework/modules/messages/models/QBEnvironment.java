package com.quickblox.android.framework.modules.messages.models;

/**
 * User: Oleg Soroka
 * Date: 20.09.12
 * Time: 12:10
 */
public enum QBEnvironment {

    DEVELOPMENT("development"),
    PRODUCTION("production");

    private String caption;

    private QBEnvironment(String caption) {
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