package com.quickblox.android.framework.modules.custom.net.requests;

import com.quickblox.android.framework.base.helpers.GenericQueryRule;

/**
 * User: Oleg Soroka
 * Date: 14.08.12
 * Time: 12:54
 */
public class QueryRule extends GenericQueryRule {

    public static final String EQ = "eq";
    public static final String LT = "[lt]";
    public static final String LTE = "[lte]";
    public static final String GT = "[gt]";
    public static final String GTE = "[gte]";
    public static final String NE = "[ne]";
    public static final String IN = "[in]";
    public static final String NIN = "[in]";
    public static final String SORT_ASC = "sort_asc";
    public static final String SORT_DESC = "sort_desc";

    private String rule;

    public QueryRule(String paramName, String rule, Object value) {
        super(paramName, value);
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    // Returns left part of request fieldName, e.g. "score_value[lt]" ("score_value[lt]=1000").
    public String getRuleAsRequestParam() {
        if (rule.equals(QueryRule.EQ)) {
            return paramName;
        } else {
            return paramName + rule;
        }
    }

    // Returns right part of request fieldName, e.g. "1000" ("score_value[lt]=1000").
    public String getRuleAsRequestValue() {
        return value.toString();
    }

    // Returns whole string like "score_value[lt]=1000" or "game_mode_name[ne]=ctf".
    public String getRuleString() {
        return String.format("%s=%s", getRuleAsRequestParam(), getRuleAsRequestValue());
    }
}