package com.quickblox.android.framework.modules.users.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntityWrap;

/**
 * User: Oleg Soroka
 * Date: 27.09.12
 * Time: 16:57
 */
public class QBUserWrap extends QBEntityWrap<QBUser> {

    @SerializedName("user")
    QBUser user;

    public QBUserWrap() {
    }

    public QBUser getUser() {
        return user;
    }

    public void setUser(QBUser user) {
        this.user = user;
    }

    @Override
    public QBUser getEntity() {
        return user;
    }
}