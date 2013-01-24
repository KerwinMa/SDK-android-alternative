package com.quickblox.android.framework.base.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 15.10.12
 * Time: 23:43
 */
public class QBEntityLimited {

    @SerializedName("class_name")
    protected String className;

    @SerializedName("skip")
    protected Integer skip;

    @SerializedName("limit")
    protected Integer limit;

    public QBEntityLimited() {
    }

    //

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}