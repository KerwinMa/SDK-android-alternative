package com.quickblox.android.framework.base.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 09.10.12
 * Time: 12:00
 */
public class QBNumber {

    @SerializedName("value")
    Double value;

    public QBNumber() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}