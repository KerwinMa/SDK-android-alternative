/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickblox.android.framework.base.net.results;

/**
 * User: Oleg Soroka
 * Date: 13.08.12
 * Time: 18:52
 */
public class LimitedResult<T> extends ArrayResult<T> {

    protected int pagesSkip;
    protected int pagesLimit;

    public Integer getPagesSkip() {
        return pagesSkip;
    }

    public void setPagesSkip(Integer pagesSkip) {
        this.pagesSkip = pagesSkip;
    }

    public Integer getPagesLimit() {
        return pagesLimit;
    }

    public void setPagesLimit(Integer pagesLimit) {
        this.pagesLimit = pagesLimit;
    }
}