package com.quickblox.android.framework.modules.content.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntityPaged;

import java.util.Collection;

/**
 * User: Oleg Soroka
 * Date: 05.10.12
 * Time: 02:20
 */
public class QBFilePaged extends QBEntityPaged {

    @SerializedName("items")
    Collection<QBFileWrap> items;

    public QBFilePaged() {
    }

    public Collection<QBFileWrap> getItems() {
        return items;
    }

    public void setItems(Collection<QBFileWrap> items) {
        this.items = items;
    }
}