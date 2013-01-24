package com.quickblox.android.framework.modules.messages.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.modules.messages.models.QBPushToken;

/**
 * User: Oleg Soroka
 * Date: 21.09.12
 * Time: 11:11
 */
public class QBPushTokenWrap {

    @SerializedName("push_token")
    QBPushToken pushToken;

    public QBPushTokenWrap() {
    }

    public QBPushToken getPushToken() {
        return pushToken;
    }

    public void setPushToken(QBPushToken pushToken) {
        this.pushToken = pushToken;
    }

    @Override
    public String toString() {
        return "QBPushTokenWrap{" +
                "pushToken=" + pushToken +
                '}';
    }
}
