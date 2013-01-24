package com.quickblox.android.framework.base.net.requests;

/**
 * User: Oleg Soroka
 * Date: 09.10.12
 * Time: 17:30
 */
public class QBLimitedRequestBuilder extends QBRequestBuilder {

    private int pagesSkip;
    private int pagesLimit;

    //

    public int getPagesSkip() {
        return pagesSkip;
    }

    public void setPagesSkip(int pagesSkip) {
        this.pagesSkip = pagesSkip;
    }

    public int getPagesLimit() {
        return pagesLimit;
    }

    public void setPagesLimit(int pagesLimit) {
        this.pagesLimit = pagesLimit;
    }
}