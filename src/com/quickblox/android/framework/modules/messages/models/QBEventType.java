package com.quickblox.android.framework.modules.messages.models;

/**
 * User: Oleg Soroka
 * Date: 20.09.12
 * Time: 12:37
 */
public enum QBEventType {

    FIXED_DATE("fixed_date"),
    PERIOD_DATE("period_date"),
    ONE_SHOT("one_shot"),
    MULTI_SHOT("multi_shot");

    private String caption;

    private QBEventType(String caption) {
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