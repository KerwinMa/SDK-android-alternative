package com.quickblox.android.framework.modules.messages.models;

/**
 * User: Oleg Soroka
 * Date: 27.09.12
 * Time: 14:09
 */
public class QBTagsQuery {

    String any;
    String exclude;
    String all;

    public QBTagsQuery() {
    }

    public String getAny() {
        return any;
    }

    public String[] getAnyAsString() {
        return getAsArray(any);
    }

    public void setAny(String any) {
        this.any = any;
    }

    public String getExclude() {
        return exclude;
    }

    public String[] getExcludeAsArray() {
        return getAsArray(exclude);
    }


    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getAll() {
        return all;
    }

    public String[] getAllAsArray() {
        return getAsArray(all);
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String[] getAsArray(String str) {
        return str.split(",");
    }

    @Override
    public String toString() {
        return "QBTagsQuery{" +
                "any='" + any + '\'' +
                ", exclude='" + exclude + '\'' +
                ", all='" + all + '\'' +
                '}';
    }
}