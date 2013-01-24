package com.quickblox.android.framework.modules.messages.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.modules.messages.models.QBEnvironment;
import com.quickblox.android.framework.modules.messages.models.QBTagsQuery;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 27.09.12
 * Time: 10:01
 */
public class QBSubscribersSelector {

    QBEnvironment environment;

    @SerializedName("tags_query")
    QBTagsQuery tagsQuery;

    @SerializedName("user_ids")
    ArrayList<Integer> userIds;

    public QBSubscribersSelector() {
    }

    public ArrayList<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(ArrayList<Integer> userIds) {
        this.userIds = userIds;
    }

    public QBEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(QBEnvironment environment) {
        this.environment = environment;
    }

    public QBTagsQuery getTagsQuery() {
        return tagsQuery;
    }

    public void setTagsQuery(QBTagsQuery tagsQuery) {
        this.tagsQuery = tagsQuery;
    }

    @Override
    public String toString() {
        return "QBSubscribersSelector{" +
                "environment=" + environment +
                ", tagsQuery=" + tagsQuery +
                ", userIds=" + userIds +
                '}';
    }
}