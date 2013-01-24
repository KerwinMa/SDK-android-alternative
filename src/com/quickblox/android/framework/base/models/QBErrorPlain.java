package com.quickblox.android.framework.base.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 21:44
 */
public class QBErrorPlain {

    // {"error":"No one can receive the message"}

    @SerializedName("error")
    String message;

    public QBErrorPlain() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}