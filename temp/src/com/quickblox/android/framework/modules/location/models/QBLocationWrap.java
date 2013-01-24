package com.quickblox.android.framework.modules.location.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntityWrap;

/**
 * User: Oleg Soroka
 * Date: 17.08.12
 * Time: 10:50
 */
public class QBLocationWrap extends QBEntityWrap<QBLocation> {

    @SerializedName("geo_datum")
    QBLocation location;

    public QBLocationWrap() {
    }

    public QBLocation getLocation() {
        return location;
    }

    public void setLocation(QBLocation location) {
        this.location = location;
    }

    @Override
    public QBLocation getEntity() {
        return location;
    }

    @Override
    public String toString() {
        return "QBLocationWrap{" +
                "location=" + location +
                '}';
    }
}