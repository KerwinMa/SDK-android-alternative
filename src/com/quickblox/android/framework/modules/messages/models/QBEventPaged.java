package com.quickblox.android.framework.modules.messages.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntityPaged;

import java.util.Collection;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 16:12
 */
public class QBEventPaged extends QBEntityPaged {

    @SerializedName("items")
    Collection<QBEventWrap1> items;

    public QBEventPaged() {
    }

    public Collection<QBEventWrap1> getItems() {
        return items;
    }

    public void setItems(Collection<QBEventWrap1> items) {
        this.items = items;
    }
}