package com.quickblox.android.framework.base.net.requests;

import android.text.TextUtils;
import com.quickblox.android.framework.base.helpers.GenericQueryRule;
import com.quickblox.android.framework.modules.location.net.request.QueryRule;

import java.util.ArrayList;
import java.util.Map;

/**
 * User: Oleg Soroka
 * Date: 13.09.12
 * Time: 19:29
 */
public class QBRequestBuilder {

    protected ArrayList<GenericQueryRule> rules = new ArrayList<GenericQueryRule>();

    protected String arrayToString(Object... values) {
        return TextUtils.join(",", values);
    }

    protected void addRule(String param, Object value) {
        rules.add(new QueryRule(param, value));
    }

    public void fillParametersMap(Map<String, String> parametersMap) {
        for (GenericQueryRule qr : rules) {
            parametersMap.put(qr.getParamName(), qr.getParamValue());
        }
    }

    //

    public ArrayList<GenericQueryRule> getRules() {
        return rules;
    }

    public void setRules(ArrayList<GenericQueryRule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "QBRequestBuilder{" +
                "rules=" + rules +
                '}';
    }
}