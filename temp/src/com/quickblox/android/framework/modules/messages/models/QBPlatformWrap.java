package com.quickblox.android.framework.modules.messages.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 20.09.12
 * Time: 12:25
 */
public class QBPlatformWrap {

    @SerializedName("name")
    String name;

    public QBPlatformWrap() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}