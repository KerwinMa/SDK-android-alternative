/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickblox.android.framework.base.net.results;

/**
 * User: Igor Khomenko
 */
public abstract class PagedResult<T> extends ArrayResult<T> {

    protected Integer currentPage;
    protected Integer perPage;
    protected Integer totalEntries;

    public PagedResult() {
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