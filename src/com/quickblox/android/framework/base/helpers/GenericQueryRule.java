package com.quickblox.android.framework.base.helpers;

/**
 * User: Oleg Soroka
 * Date: 13.09.12
 * Time: 19:11
 */
public class GenericQueryRule {

    protected String paramName;
    protected Object value;

    public GenericQueryRule(String paramName, Object value) {
        this.paramName = paramName;
        this.value = value;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Object getValue() {
        return value;
    }

    public String getParamValue() {
        return value.toString();
    }

    public void setValue(Object value) {
        this.value = value;
    }

    // Returns whole string like "created_at=1326702614".
    public String getRuleString() {
        return String.format("%s=%s", getParamName(), getValue());
    }

    @Override
    public String toString() {
        return "GenericQueryRule{" +
                "paramName='" + paramName + '\'' +
                ", value=" + value +
                '}';
    }
}
