package com.quickblox.android.framework.base.net.results;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 08.10.12
 * Time: 16:07
 */
public class ArrayResult<T> extends Result {

    protected ArrayList<T> items = new ArrayList<T>();

    //

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }
}