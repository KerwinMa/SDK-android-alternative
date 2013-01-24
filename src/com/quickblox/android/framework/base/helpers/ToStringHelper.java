package com.quickblox.android.framework.base.helpers;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ToStringHelper {

    private final static String separator = "\n";
    private final static String arrow = "=";

    public static String toString(List<?> l) {
        if (l == null) {
            return "null";
        }

        StringBuilder sb = new StringBuilder("(");
        String sep = "";
        for (Object object : l) {
            sb.append(sep).append(object.toString());
            sep = separator;
        }
        return sb.append(")").toString();
    }

    public static String toString(Map<?, ?> m, String prefix) {
        if (m == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        String sep = "";
        for (Object object : m.keySet()) {
            Object value = m.get(object);
            sb.append(sep)
                    .append(prefix)
                    .append(object.toString())
                    .append(arrow)
                    .append(value.toString());

            sep = separator;
        }
        return sb.toString();
    }

    public static String toString(Set<?> s) {
        if (s == null) {
            return "null";
        }

        StringBuilder sb = new StringBuilder("{");
        String sep = "";
        for (Object object : s) {
            sb.append(sep).append(object.toString());
            sep = separator;
        }
        return sb.append("}").toString();
    }
}
