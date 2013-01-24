package com.quickblox.android.framework.modules.custom.net.requests;

import com.quickblox.android.framework.base.net.requests.QBLimitedRequestBuilder;

import java.util.Arrays;

/**
 * User: Oleg Soroka
 * Date: 14.08.12
 * Time: 12:53
 */
public class QBCustomObjectRequestBuilder extends QBLimitedRequestBuilder {

    private void addRule(String paramName, String rule, Object value) {
        rules.add(new QueryRule(paramName, rule, value));
    }

    // lt

    public QBCustomObjectRequestBuilder lt(String paramName, Object value) {
        addRule(paramName, QueryRule.LT, value);
        return this;
    }

    public QBCustomObjectRequestBuilder lte(String paramName, Object value) {
        addRule(paramName, QueryRule.LTE, value);
        return this;
    }

    // gt

    public QBCustomObjectRequestBuilder gt(String paramName, Object value) {
        addRule(paramName, QueryRule.GT, value);
        return this;
    }

    public QBCustomObjectRequestBuilder gte(String paramName, Object value) {
        addRule(paramName, QueryRule.GTE, value);
        return this;
    }

    // in

    protected String arrayToString(Object... values) {
        StringBuilder arrayString = new StringBuilder();
        String delimiter = "";
        for (Object obj : Arrays.asList(values)) {
            arrayString.append(delimiter);
            arrayString.append(obj);
            delimiter = ",";
        }
        return arrayString.toString();
    }

    public QBCustomObjectRequestBuilder in(String paramName, Object... values) {
        addRule(paramName, QueryRule.IN, arrayToString(values));
        return this;
    }

    public QBCustomObjectRequestBuilder nin(String paramName, Object... values) {
        addRule(paramName, QueryRule.NIN, arrayToString(values));
        return this;
    }


    // others

    public QBCustomObjectRequestBuilder ne(String field, Object value) {
        addRule(field, QueryRule.NE, value);
        return this;
    }


    public QBCustomObjectRequestBuilder eq(String fieldName, Object value) {
        addRule(fieldName, QueryRule.EQ, value);
        return this;
    }

    //

    public QBCustomObjectRequestBuilder sortAsc(String fieldName) {
        // That order of params is completely right.
        // That helps to generate string like "sort_asc=health" or "sort_desc=power".
        addRule(QueryRule.SORT_ASC, "", fieldName);
        return this;
    }

    public QBCustomObjectRequestBuilder sortDesc(String fieldName) {
        addRule(QueryRule.SORT_DESC, "", fieldName);
        return this;
    }
}