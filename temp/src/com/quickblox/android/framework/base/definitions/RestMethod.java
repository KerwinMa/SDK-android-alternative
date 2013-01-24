package com.quickblox.android.framework.base.definitions;

/**
 * User: Oleg Soroka
 * Date: 02.10.12
 * Time: 22:50
 */
public enum RestMethod {

    DELETE("DELETE"),
    GET("GET"),
    POST("POST"),
    PUT("PUT");

    private String caption;

    private RestMethod(String caption) {
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