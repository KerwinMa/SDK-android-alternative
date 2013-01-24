package com.quickblox.android.framework.modules.users.net.server;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.requests.QBPagedRequestBuilder;
import com.quickblox.android.framework.base.net.server.BaseService;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.modules.users.net.queries.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author: Daniel Goncharov
 * Date: 04.07.12
 * Time: 14:43
 */
public class QBUsers extends BaseService {

    // TODO добавить социальную интеграцию http://quickblox.com/developers/Social_Networks_Integration_Manual

    // Sign in

    public static void signIn(QBUser user, QBCallback callback) {
        signIn(user, callback, null);
    }

    public static void signIn(QBUser user, QBCallback callback, Object context) {
        QuerySignIn query = new QuerySignIn(user);
        query.performAsyncWithCallback(callback, context);
    }

    public static void signIn(String login, String password, QBCallback callback) {
        signIn(login, password, callback, null);
    }

    public static void signIn(String login, String password, QBCallback callback, Object context) {
        signIn(new QBUser(login, password), callback, context);
    }

    public static void signInByEmail(String email, String password, QBCallback callback) {
        signInByEmail(email, password, callback, null);
    }

    public static void signInByEmail(QBUser user, QBCallback callback) {
        signInByEmail(user, callback, null);
    }

    public static void signInByEmail(String email, String password, QBCallback callback, Object context) {
        QBUser user = new QBUser();
        user.setEmail(email);
        user.setPassword(password);
        signInByEmail(user, callback, context);
    }

    public static void signInByEmail(QBUser user, QBCallback callback, Object context) {
        signIn(user, callback, context);
    }

    // Sign out

    public static void signOut(QBCallback callback) {
        signOut(callback, null);
    }

    public static void signOut(QBCallback callback, Object context) {
        QuerySignOut query = new QuerySignOut();
        query.performAsyncWithCallback(callback, context);
    }

    // Sign up

    public static void signUp(String login, String password, QBCallback callback) {
        signUp(new QBUser(login, password), callback, null);
    }

    public static void signUp(String login, String password, QBCallback callback, Object context) {
        signUp(new QBUser(login, password), callback, context);
    }

    public static void signUp(QBUser user, QBCallback callback) {
        signUp(user, callback, null);
    }

    public static void signUp(QBUser user, QBCallback callback, Object context) {
        QueryCreateUser query = new QueryCreateUser(user);
        query.performAsyncWithCallback(callback, context);
    }

    // Get exact user

    public static void getUser(QBUser user, QBCallback callback) {
        getUser(user, callback, null);
    }

    public static void getUser(int id, QBCallback callback) {
        getUser(id, callback, null);
    }

    public static void getUser(int id, QBCallback callback, Object context) {
        QBUser user = new QBUser();
        user.setId(id);
        getUser(user, callback, context);
    }

    public static void getUser(QBUser user, QBCallback callback, Object context) {
        QueryGetUser query = new QueryGetUser(user);
        query.performAsyncWithCallback(callback, context);
    }

    // Get users

    public static void getUsers(QBCallback callback) {
        getUsers(null, null, callback);
    }

    public static void getUsers(QBPagedRequestBuilder requestBuilder, QBCallback callback) {
        getUsers(null, requestBuilder, callback);
    }

    public static void getUsersByFullName(String fullName, QBCallback callback) {
        getUsers(fullName, null, callback);
    }

    private static void getUsersByFullName(String fullName, QBPagedRequestBuilder requestBuilder,
                                           QBCallback callback) {
        getUsers(fullName, requestBuilder, callback);
    }

    private static void getUsers(String fullName, QBPagedRequestBuilder requestBuilder,
                                 QBCallback callback) {
        QueryGetUsers query = new QueryGetUsers(fullName, requestBuilder);
        query.performAsyncWithCallback(callback);
    }

    public static void getUsersByTags(Collection<String> tags, QBCallback callback) {
        getUsersByTags(tags, null, callback);
    }

    public static void getUsersByTags(String tags, QBCallback callback) {
        Collection<String> tagsList = new ArrayList<String>(Arrays.asList(tags.split(",")));
        getUsersByTags(tagsList, null, callback);
    }

    public static void getUsersByTags(String tags, QBPagedRequestBuilder requestBuilder, QBCallback callback) {
        Collection<String> tagsList = new ArrayList<String>(Arrays.asList(tags.split(",")));
        getUsersByTags(tagsList, requestBuilder, callback);
    }

    public static void getUsersByTags(String[] tags, QBPagedRequestBuilder requestBuilder, QBCallback callback) {
        Collection<String> tagsList = new ArrayList<String>(Arrays.asList(tags));
        getUsersByTags(tagsList, requestBuilder, callback);
    }

    public static void getUsersByTags(Collection<String> tags, QBPagedRequestBuilder requestBuilder,
                                      QBCallback callback) {
        QueryGetUsers query = new QueryGetUsers(tags, requestBuilder);
        query.performAsyncWithCallback(callback);
    }

    // Get user by specified field

    private static void getFilteredUser(QBUser user, int filterType, QBCallback callback) {
        QueryGetUser query = new QueryGetUser(user, filterType);
        query.performAsyncWithCallback(callback);
    }

    public static void getUserByLogin(QBUser user, QBCallback callback) {
        getFilteredUser(user, QueryGetUser.BY_LOGIN, callback);
    }

    public static void getUserByLogin(String login, QBCallback callback) {
        QBUser user = new QBUser();
        user.setLogin(login);
        getFilteredUser(user, QueryGetUser.BY_LOGIN, callback);
    }

    public static void getUserByFacebookId(QBUser user, QBCallback callback) {
        getFilteredUser(user, QueryGetUser.BY_FACEBOOK_ID, callback);
    }

    public static void getUserByFacebookId(String facebookId, QBCallback callback) {
        QBUser user = new QBUser();
        user.setFacebookId(facebookId);
        getFilteredUser(user, QueryGetUser.BY_FACEBOOK_ID, callback);
    }

    public static void getUserByTwitterId(QBUser user, QBCallback callback) {
        getFilteredUser(user, QueryGetUser.BY_TWITTER_ID, callback);
    }

    public static void getUserByTwitterId(String twitterId, QBCallback callback) {
        QBUser user = new QBUser();
        user.setTwitterId(twitterId);
        getFilteredUser(user, QueryGetUser.BY_TWITTER_ID, callback);
    }

    public static void getUserByEmail(QBUser user, QBCallback callback) {
        getFilteredUser(user, QueryGetUser.BY_EMAIL, callback);
    }

    public static void getUserByEmail(String email, QBCallback callback) {
        QBUser user = new QBUser();
        user.setEmail(email);
        getFilteredUser(user, QueryGetUser.BY_EMAIL, callback);
    }

    public static void getUserByExternalId(QBUser user, QBCallback callback) {
        getFilteredUser(user, QueryGetUser.BY_EXTERNAL_USER_ID, callback);
    }

    public static void getUserByExternalId(String externalId, QBCallback callback) {
        QBUser user = new QBUser();
        user.setExternalId(externalId);
        getFilteredUser(user, QueryGetUser.BY_EXTERNAL_USER_ID, callback);
    }

    // Update user

    public static void updateUser(QBUser user, QBCallback callback) {
        updateUser(user, callback, null);
    }

    public static void updateUser(QBUser user, QBCallback callback, Object context) {
        QueryUpdateUser query = new QueryUpdateUser(user);
        query.performAsyncWithCallback(callback, context);
    }

    // Delete user

    public static void deleteUser(int userId, QBCallback callback) {
        deleteUser(new QBUser(userId), callback, null);
    }

    public static void deleteUser(int userId, QBCallback callback, Object context) {
        deleteUser(new QBUser(userId), callback, context);
    }

    public static void deleteUser(QBUser user, QBCallback callback) {
        deleteUser(user, callback, null);
    }

    public static void deleteUser(QBUser user, QBCallback callback, Object context) {
        QueryDeleteUser query = new QueryDeleteUser(user);
        query.performAsyncWithCallback(callback, context);
    }

    public static void deleteByExternalId(String externalId, QBCallback callback) {
        QBUser user = new QBUser();
        user.setExternalId(externalId);
        deleteByExternalId(user, callback, null);
    }

    public static void deleteByExternalId(String externalId, QBCallback callback, Object context) {
        QBUser user = new QBUser();
        user.setExternalId(externalId);
        deleteByExternalId(user, callback, context);
    }

    public static void deleteByExternalId(QBUser user, QBCallback callback) {
        deleteByExternalId(user, callback, null);
    }

    public static void deleteByExternalId(QBUser user, QBCallback callback, Object context) {
        QueryDeleteUser query = new QueryDeleteUser(user, QueryDeleteUser.BY_EXTERNAL_USER_ID);
        query.performAsyncWithCallback(callback, context);
    }

    public static void resetPassword(String email, QBCallback callback) {
        resetPassword(email, callback, null);
    }

    public static void resetPassword(String email, QBCallback callback, Object context) {
        QBUser user = new QBUser();
        user.setEmail(email);
        resetPassword(user, callback, context);
    }

    public static void resetPassword(QBUser user, QBCallback callback) {
        resetPassword(user, callback, null);
    }

    public static void resetPassword(QBUser user, QBCallback callback, Object context) {
        QueryResetPassword query = new QueryResetPassword(user);
        query.performAsyncWithCallback(callback, context);
    }
}