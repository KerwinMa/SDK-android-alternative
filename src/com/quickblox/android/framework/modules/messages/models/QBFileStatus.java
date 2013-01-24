package com.quickblox.android.framework.modules.messages.models;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 19:09
 */
public enum QBFileStatus {

    COMPLETE("complete"),
    UNCOMPLETE("uncomplete");

    private String caption;

    private QBFileStatus(String caption) {
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