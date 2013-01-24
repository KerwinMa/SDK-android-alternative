package com.quickblox.android.framework.base.net.results;

/**
 * User: Oleg Soroka
 * Date: 05.10.12
 * Time: 21:34
 */
public class QBStringResult extends Result {

    private String string;

    public QBStringResult(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}