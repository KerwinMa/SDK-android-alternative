package com.quickblox.android.framework.base.definitions;

/**
 * User: Oleg Soroka
 * Date: 02.10.12
 * Time: 22:39
 */
public enum ServerZone {

    STAGE("stage."),
    PRODUCTION("");

    private String caption;

    private ServerZone(String caption) {
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