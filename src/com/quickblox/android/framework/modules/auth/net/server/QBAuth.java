/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickblox.android.framework.modules.auth.net.server;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.server.BaseService;
import com.quickblox.android.framework.modules.auth.net.queries.QueryCreateSession;
import com.quickblox.android.framework.modules.auth.net.queries.QueryDeleteSession;
import com.quickblox.android.framework.modules.messages.models.QBDevice;
import com.quickblox.android.framework.modules.users.models.QBUser;

/**
 * User: Igor Khomenko
 */
public class QBAuth extends BaseService {

    static Lo lo = new Lo(QBAuth.class.getSimpleName());

    // Plain auth

    public static void authorizeApp(QBCallback callback) {
        authorizeApp(callback, null);
    }

    public static void authorizeApp(QBCallback callback, Object context) {
        createBaseService();
        QueryCreateSession query = new QueryCreateSession();
        query.performAsyncWithCallback(callback, context);
    }

    // Auth with user

    public static void authorizeApp(String login, String password, QBCallback callback) {
        authorizeApp(login, password, callback, null);
    }

    public static void authorizeApp(QBUser user, QBCallback callback) {
        authorizeApp(user, callback, null);
    }

    public static void authorizeApp(String login, String password, QBCallback callback,
                                    Object context) {
        authorizeApp(new QBUser(login, password), callback, context);
    }

    public static void authorizeApp(QBUser user, QBCallback callback,
                                    Object context) {
        createBaseService();
        QueryCreateSession query = new QueryCreateSession(user);
        query.performAsyncWithCallback(callback, context);
    }

    public static void authorizeAppByEmail(String email, String password, QBCallback callback) {
        QBUser user = new QBUser();
        user.setEmail(email);
        user.setPassword(password);
        authorizeAppByEmail(user, callback, null);
    }

    public static void authorizeAppByEmail(String email, String password, QBCallback callback, Object context) {
        QBUser user = new QBUser();
        user.setEmail(email);
        user.setPassword(password);
        authorizeAppByEmail(user, callback, context);

    }

    public static void authorizeAppByEmail(QBUser user, QBCallback callback) {
        authorizeAppByEmail(user, callback, null);
    }

    public static void authorizeAppByEmail(QBUser user, QBCallback callback, Object context) {
        createBaseService();
        QueryCreateSession query = new QueryCreateSession(user);
        query.performAsyncWithCallback(callback, context);
    }

    // Auth with device

    public static void authorizeApp(QBDevice device, QBCallback callback, Object context) {
        createBaseService();
        QueryCreateSession query = new QueryCreateSession(device);
        query.performAsyncWithCallback(callback, context);
    }

    public static void authorizeApp(QBDevice device, QBCallback callback) {
        authorizeApp(device, callback, null);
    }

    public static void authorizeApp(String login, String password, QBDevice device, QBCallback callback) {
        authorizeApp(login, password, device, callback, null);
    }

    public static void authorizeApp(String login, String password, QBDevice device, QBCallback callback,
                                    Object context) {
        authorizeApp(new QBUser(login, password), device, callback, context);
    }

    public static void authorizeApp(QBUser user, QBDevice device, QBCallback callback) {
        authorizeApp(user, device, callback, null);
    }

    public static void authorizeApp(QBUser user, QBDevice device, QBCallback callback, Object context) {
        createBaseService();
        QueryCreateSession query = new QueryCreateSession(user, device);
        query.performAsyncWithCallback(callback, context);
    }

    // Remove session

    public static void deleteSession(QBCallback callback) {
        removeSession(callback, null);
    }

    public static void removeSession(QBCallback callback, Object context) {
        QueryDeleteSession query = new QueryDeleteSession();
        query.performAsyncWithCallback(callback, context);
    }
}