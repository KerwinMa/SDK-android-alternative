package com.quickblox.android.framework.base.net.requests;

import com.quickblox.android.framework.modules.location.net.request.QueryRule;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 18:27
 */
public class QBPagedRequestBuilder extends QBRequestBuilder {

    private int currentPage;
    private int perPage;

    public QBPagedRequestBuilder() {
    }

    public QBPagedRequestBuilder(int perPage, int currentPage) {
        this.currentPage = currentPage;
        this.perPage = perPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public QBPagedRequestBuilder setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        addRule(QueryRule.CURRENT_PAGE, currentPage);
        return this;
    }

    public int getPerPage() {
        return perPage;
    }

    public QBPagedRequestBuilder setPerPage(int perPage) {
        this.perPage = perPage;
        addRule(QueryRule.PER_PAGE, perPage);
        return this;
    }
}