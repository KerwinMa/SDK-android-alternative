package com.quickblox.android.framework.modules.custom.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 04.10.12
 * Time: 00:59
 */
public class QBCustomObjectsCountWrap {

    // TODO fix, look below, неправильный формат данных
    //    {
    //        "class_name":"hero",
    //            "skip":0,
    //            "limit":100,
    //            "items":{ "count":2 }
    //    }

    @SerializedName("items")
    private QBCustomObjectsCount customObjectsCount;

    public QBCustomObjectsCountWrap() {
    }

    public QBCustomObjectsCount getCustomObjectsCount() {
        return customObjectsCount;
    }

    public void setCustomObjectsCount(QBCustomObjectsCount customObjectsCount) {
        this.customObjectsCount = customObjectsCount;
    }
}