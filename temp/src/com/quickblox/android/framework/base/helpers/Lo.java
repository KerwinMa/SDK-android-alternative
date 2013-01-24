package com.quickblox.android.framework.base.helpers;

import android.util.Log;
import com.quickblox.android.framework.base.models.QBSettings;
import com.quickblox.android.framework.base.definitions.LogLevel;

import java.io.Serializable;

/**
 * User: Oleg Soroka
 * Date: 12.08.12
 * Time: 11:46
 */
public class Lo implements Serializable {

    private static String DEFAULT_TAG_PREFIX = "QBASDK"; // QuickBlox Android SDK
    private static String DEFAULT_TAG = "QBASDK";
    private String tag;
    private Boolean logPermission = false;

    public Lo(Object caller) {
        this.tag = caller.getClass().getSimpleName();
        if (QBSettings.getInstance().getLogLevel() == LogLevel.DEBUG) {
            logPermission = true;
        }
    }

    public void g(String logString) {
        if (logPermission) {
            Log.d(getFullTag(), String.format("%s", logString));
        }

    }

    public void g(String logFormat, Object... args) {
        if (logPermission) {
            Log.d(getFullTag(), String.format(logFormat, args));
        }
    }

    public void g(Object arg) {
        if (logPermission) {
            Log.d(getFullTag(), String.format("%s", arg));
        }
    }

    public String getFullTag() {
        return String.format("%s %s", DEFAULT_TAG_PREFIX, tag);
    }

    // Static log without tag

    public static void gg(String logString) {
        if (logString == null) {
            Log.d(DEFAULT_TAG, "null");
            return;
        }
        Log.d(DEFAULT_TAG, logString);
    }

    public static void gg(String logFormat, Object... args) {
        Log.d(DEFAULT_TAG, String.format(logFormat, args));
    }

    public static void gg(Object arg) {
        if (arg == null) {
            Log.d(DEFAULT_TAG, "null");
            return;
        }
        Log.d(DEFAULT_TAG, arg.toString());
    }

    // Static log with tag

    public static String getFullTag(String tag) {
        return String.format("%s %s", DEFAULT_TAG_PREFIX, tag);
    }

    public static void gt(String logTag, String logString) {
        Log.d(getFullTag(logTag), logString);
    }

    public static void gt(String logTag, String logFormat, Object... args) {
        Log.d(getFullTag(logTag), String.format(logFormat, args));
    }

    public static void gt(String logTag, Object arg) {
        Log.d(getFullTag(logTag), String.format("%s", arg.toString()));
    }
}