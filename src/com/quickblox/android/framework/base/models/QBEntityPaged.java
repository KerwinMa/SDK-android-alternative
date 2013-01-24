package com.quickblox.android.framework.base.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 18:03
 */
public class QBEntityPaged {

    @SerializedName("current_page")
    protected Integer currentPage;
    @SerializedName("per_page")
    protected Integer perPage;
    @SerializedName("total_entries")
    protected Integer totalEntries;

    public QBEntityPaged() {
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotalEntries() {
        return totalEntries;
    }

    public void setTotalEntries(Integer totalEntries) {
        this.totalEntries = totalEntries;
    }

}