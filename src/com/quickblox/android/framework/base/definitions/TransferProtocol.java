package com.quickblox.android.framework.base.definitions;

/**
 * User: Oleg Soroka
 * Date: 24.09.12
 * Time: 11:29
 */
public enum TransferProtocol {

    HTTP("http"),
    HTTPS("https");

    private String caption;

    private TransferProtocol(String caption) {
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