package com.quickblox.android.framework.modules.location.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntityPaged;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 17.09.12
 * Time: 16:17
 */
public class QBLocationPaged extends QBEntityPaged {

    @SerializedName("items")
    private ArrayList<QBLocationWrap> items;

    public QBLocationPaged() {
    }

    public ArrayList<QBLocationWrap> getItems() {
        return items;
    }

    public void setItems(ArrayList<QBLocationWrap> locations) {
        this.items = locations;
    }
}