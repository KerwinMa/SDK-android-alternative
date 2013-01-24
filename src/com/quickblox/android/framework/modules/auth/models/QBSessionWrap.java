package com.quickblox.android.framework.modules.auth.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 02.10.12
 * Time: 17:02
 */
public class QBSessionWrap {

    @SerializedName("session")
    QBSession session;

    public QBSessionWrap() {
    }

    public QBSession getSession() {
        return session;
    }

    public void setSession(QBSession session) {
        this.session = session;
    }
}