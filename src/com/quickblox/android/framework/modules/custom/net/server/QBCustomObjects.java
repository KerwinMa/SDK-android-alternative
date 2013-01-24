package com.quickblox.android.framework.modules.custom.net.server;

import android.content.Context;
import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.server.BaseService;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;
import com.quickblox.android.framework.modules.custom.net.queries.*;
import com.quickblox.android.framework.modules.custom.net.requests.QBCustomObjectRequestBuilder;

/**
 * User: Oleg Soroka
 * Date: 13.08.12
 * Time: 11:36
 */
public class QBCustomObjects extends BaseService {

    // Create

    public static void createObject(QBCustomObject customObject, QBCallback callback) {
        createObject(customObject, callback, null);
    }

    public static void createObject(QBCustomObject customObject, QBCallback callback, Object context) {
        QueryCreateCustomObject query = new QueryCreateCustomObject(customObject);
        query.performAsyncWithCallback(callback, context);
    }

    // Retrieve

    public static void getObject(QBCustomObject customObject, QBCallback callback) {
        getObject(customObject, callback, null);
    }

    public static void getObject(QBCustomObject customObject, QBCallback callback, Context context) {
        QueryGetCustomObject query = new QueryGetCustomObject(customObject);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getObject(String className, String customObjectId, QBCallback callback) {
        getObject(className, customObjectId, callback, null);
    }

    public static void getObject(String className, String customObjectId, QBCallback callback, Context context) {
        QueryGetCustomObject query = new QueryGetCustomObject(new QBCustomObject(className, customObjectId));
        query.performAsyncWithCallback(callback, context);
    }

    public static void getObjects(String className, QBCallback callback) {
        getObjects(className, callback, null);
    }

    public static void getObjects(String className, QBCallback callback, Object context) {
        QueryGetCustomObjects query = new QueryGetCustomObjects(className);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getObjects(String className, QBCustomObjectRequestBuilder requestBuilder, QBCallback callback) {
        getObjects(className, requestBuilder, callback, null);
    }

    public static void getObjects(String className, QBCustomObjectRequestBuilder requestBuilder, QBCallback callback, Object context) {
        QueryGetCustomObjects query = new QueryGetCustomObjects(className);
        query.setRequestBuilder(requestBuilder);
        query.performAsyncWithCallback(callback, context);
    }

    // Update

    public static void updateObject(QBCustomObject customObject, QBCallback callback) {
        updateObject(customObject, callback, null);
    }

    public static void updateObject(QBCustomObject customObject, QBCallback callback, Object context) {
        QueryUpdateCustomObject query = new QueryUpdateCustomObject(customObject);
        query.performAsyncWithCallback(callback, context);
    }

    // Delete

    public static void deleteObject(String className, String customObjectId, QBCallback callback) {
        deleteObject(className, customObjectId, callback, null);
    }

    public static void deleteObject(String className, String customObjectId, QBCallback callback, Object context) {
        QBCustomObject customObject = new QBCustomObject();
        customObject.setCustomObjectId(customObjectId);
        customObject.setClassName(className);
        deleteObject(customObject, callback, context);
    }

    public static void deleteObject(QBCustomObject customObject, QBCallback callback) {
        deleteObject(customObject, callback, null);
    }

    public static void deleteObject(QBCustomObject customObject, QBCallback callback, Object context) {
        QueryDeleteCustomObject query = new QueryDeleteCustomObject(customObject);
        query.performAsyncWithCallback(callback, context);
    }

    // Count

    public static void countObjects(String className, QBCallback callback, Context context) {
        countObjects(className, null, callback, null);
    }

    public static void countObjects(String className, QBCallback callback) {
        countObjects(className, null, callback, null);
    }

    public static void countObjects(String className, QBCustomObjectRequestBuilder requestBuilder, QBCallback callback) {
        countObjects(className, requestBuilder, callback, null);
    }

    public static void countObjects(String className, QBCustomObjectRequestBuilder requestBuilder, QBCallback callback, Context context) {
        QueryCountCustomObject query = new QueryCountCustomObject(className);
        query.setRequestBuilder(requestBuilder);
        query.performAsyncWithCallback(callback, context);
    }

}