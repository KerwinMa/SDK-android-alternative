package com.quickblox.android.framework.base.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 21:44
 */
public class QBErrorWithBase {

    // {"base":["No one can receive the message for GCM (Android Push)"]}

    @SerializedName("base")
    ArrayList<String> base;

    public QBErrorWithBase() {
    }

    public ArrayList<String> getBase() {
        return base;
    }

    public void setBase(ArrayList<String> base) {
        this.base = base;
    }
}