package com.quickblox.android.framework.base.models;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 21:44
 */
public class QBErrorWithCode {

    // {"base":null,"message":"invalid byte sequence in UTF-8"}

    String code;
    String message;

    public QBErrorWithCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}