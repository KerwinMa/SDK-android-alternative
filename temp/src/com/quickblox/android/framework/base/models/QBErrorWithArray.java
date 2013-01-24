package com.quickblox.android.framework.base.models;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 02.10.12
 * Time: 15:30
 */
public class QBErrorWithArray {

    // {'errors':['This geo datum was not found or it is bound to a place']}

    ArrayList<String> errors;

    public QBErrorWithArray() {
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }
}