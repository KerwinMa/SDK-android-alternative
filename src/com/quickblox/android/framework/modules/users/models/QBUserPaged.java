package com.quickblox.android.framework.modules.users.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntityPaged;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 27.09.12
 * Time: 16:55
 */
public class QBUserPaged extends QBEntityPaged {

    @SerializedName("items")
    ArrayList<QBUserWrap> items;

    public QBUserPaged() {
    }

    public ArrayList<QBUserWrap> getItems() {
        return items;
    }

    public void setItems(ArrayList<QBUserWrap> items) {
        this.items = items;
    }
}