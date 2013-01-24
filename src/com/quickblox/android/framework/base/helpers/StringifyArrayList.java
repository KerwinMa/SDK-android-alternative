package com.quickblox.android.framework.base.helpers;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * User: Oleg Soroka
 * Date: 27.09.12
 * Time: 13:50
 */
public class StringifyArrayList<T> extends ArrayList<T> {

    public StringifyArrayList() {
    }

    public StringifyArrayList(Collection<? extends T> collection) {
        super(collection);
    }

    public void add(T... items) {
        addAll(Arrays.asList(items));
    }

    public String getItemsAsString() {
        return TextUtils.join(",", this);
    }

    public String getItemsAsStringOrNull() {
        String str = "";
        if (this.size() > 0) {
            str = TextUtils.join(",", this);
        }
        if (StringUtils.isEmpty(str)) {
            str = null;
        }
        return str;
    }
}