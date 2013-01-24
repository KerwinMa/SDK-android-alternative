package com.quickblox.android.framework.modules.custom.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntityLimited;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 15.10.12
 * Time: 23:39
 */
public class QBCustomObjectLimited extends QBEntityLimited {

    @SerializedName("items")
    private ArrayList<QBCustomObject> items;

    public QBCustomObjectLimited() {
    }

    public ArrayList<QBCustomObject> getItems() {
        return items;
    }

    public void setItems(ArrayList<QBCustomObject> items) {
        this.items = items;
    }
}