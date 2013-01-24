package com.quickblox.android.framework.modules.chat.server;

import com.quickblox.android.framework.base.models.QBSettings;
import com.quickblox.android.framework.modules.chat.definitions.Consts;
import com.quickblox.android.framework.modules.users.models.QBUser;

/**
 * User: Oleg Soroka
 * Date: 10.10.12
 * Time: 22:05
 */
public class QBChat {

    public static String getChatServerDomain() {
        return Consts.QB_CHAT_SERVER_DOMAIN;
    }

    public static String getChatLoginShort(QBUser user) {
        String appId = QBSettings.getInstance().getApplicationId();
        String chatLogin = String.format("%s-%s", user.getId(), appId);
        return chatLogin;
    }

    public static String getChatLoginShort(int userId) {
        return getChatLoginShort(new QBUser(userId));
    }

    public static String getChatLoginFull(QBUser user) {
        String postfix = Consts.QB_CHAT_JID_POSTFIX;
        return getChatLoginShort(user) + postfix;
    }

    public static String getChatLoginFull(int userId) {
        return getChatLoginFull(new QBUser(userId));
    }

}