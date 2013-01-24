package com.quickblox.android.framework.modules.ratings.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntityPaged;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 09.10.12
 * Time: 11:17
 */
public class QBScorePaged extends QBEntityPaged {

    @SerializedName("items")
    ArrayList<QBScoreWrap> items;

    public QBScorePaged() {
    }

    public ArrayList<QBScoreWrap> getItems() {
        return items;
    }

    public void setItems(ArrayList<QBScoreWrap> items) {
        this.items = items;
    }
}