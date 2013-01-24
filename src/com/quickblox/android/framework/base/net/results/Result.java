/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickblox.android.framework.base.net.results;

import com.quickblox.android.framework.base.net.communication.RestResult;

/**
 * User: Igor Khomenko
 */
public class Result extends RestResult {

    public static final String LOG_MSG_OBJ_PARSED = "Object parsed from JSON : \n";
    public static final String LOG_MSG_OBJ_PARSED_XML = "Object parsed from XML : \n";

    public boolean isSuccess() {
        return getErrors().isEmpty() && !notFoundError();
    }

    protected boolean isDeserializable() {
        return isSuccess() && !isEmpty();
    }

}