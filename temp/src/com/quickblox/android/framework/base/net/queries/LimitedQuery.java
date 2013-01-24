package com.quickblox.android.framework.base.net.queries;


import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.PagedResult;
import com.quickblox.android.framework.modules.custom.definitions.Consts;

import java.util.Map;

// Analogue for PagedQuery but for CustomObjects module that
// doesn't use "page", "per_page" parameter, but uses "skip" and "limit".
public class LimitedQuery extends Query {

    private Integer pagesLimit;
    private Integer pagesSkip;

    public Integer getPagesLimit() {
        return pagesLimit;
    }

    public void setPagesLimit(Integer pagesLimit) {
        this.pagesLimit = pagesLimit;
    }

    public Integer getPagesSkip() {
        return pagesSkip;
    }

    public void setPagesSkip(Integer pagesSkip) {
        this.pagesSkip = pagesSkip;
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();

        putValue(parametersMap, Consts.SKIP, pagesSkip);
        putValue(parametersMap, Consts.LIMIT, pagesLimit);
    }

    @Override
    public Class getResultClass() {
        return PagedResult.class;
    }
}