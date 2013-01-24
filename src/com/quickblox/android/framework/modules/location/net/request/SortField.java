package com.quickblox.android.framework.modules.location.net.request;

/**
 * User: Oleg Soroka
 * Date: 17.08.12
 * Time: 19:50
 */
public enum SortField {
    CREATED_AT("created_at"),
    LATITUDE("latitude"),
    LONGITUDE("longitude"),
    DISTANCE("distance");

    private String caption;

    private SortField(String caption) {
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