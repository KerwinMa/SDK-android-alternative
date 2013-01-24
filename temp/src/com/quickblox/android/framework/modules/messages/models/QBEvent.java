package com.quickblox.android.framework.modules.messages.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntity;
import com.quickblox.android.framework.base.helpers.StringifyArrayList;
import com.quickblox.android.framework.modules.messages.definitions.Consts;
import it.sauronsoftware.base64.Base64;

import java.net.URLEncoder;
import java.util.Arrays;

/**
 * User: Oleg Soroka
 * Date: 20.09.12
 * Time: 12:27
 */
public class QBEvent extends QBEntity {

    String message;

    Boolean active;

    @Expose(deserialize = false)
    QBNotificationType notificationType;

    @SerializedName("application_id")
    Integer applicationId;

    @SerializedName("event_type")
    QBEventType type;

    Integer date;

    @SerializedName("end_date")
    Integer endDate;

    String name;

    @SerializedName("occured_count")
    Integer occuredCount;

    Integer period;

    @SerializedName("user_id")
    Integer userId;

    @SerializedName("notification_channel")
    QBNotificationChannel notificationChannel;

    @SerializedName("subscribers_selector")
    QBSubscribersSelector subscribersSelector;

    @Expose(deserialize = false)
    QBPushType pushType; // not in model

    @Expose(deserialize = false)
    StringifyArrayList<Integer> userIds = new StringifyArrayList<Integer>();

    @Expose(deserialize = false)
    StringifyArrayList<String> userTagsAny = new StringifyArrayList<String>();

    @Expose(deserialize = false)
    StringifyArrayList<String> userTagsAll = new StringifyArrayList<String>();

    @Expose(deserialize = false)
    StringifyArrayList<String> userTagsExclude = new StringifyArrayList<String>();

    public QBEvent() {
    }

    public QBEvent(int id) {
        super(id);
    }

    public StringifyArrayList<String> getUserTagsAny() {
        return userTagsAny;
    }

    public void setUserTagsAny(StringifyArrayList<String> userTagsAny) {
        this.userTagsAny = userTagsAny;
    }

    public StringifyArrayList<String> getUserTagsAll() {
        return userTagsAll;
    }

    public void setUserTagsAll(StringifyArrayList<String> userTagsAll) {
        this.userTagsAll = userTagsAll;
    }

    public StringifyArrayList<String> getUserTagsExclude() {
        return userTagsExclude;
    }

    public void setUserTagsExclude(StringifyArrayList<String> userTagsExclude) {
        this.userTagsExclude = userTagsExclude;
    }

    public StringifyArrayList<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(StringifyArrayList<Integer> userIds) {
        this.userIds = userIds;
    }

    public void addUserIds(Integer... ids) {
        userIds.addAll(Arrays.asList(ids));
    }

    public void setEnvironment(QBEnvironment environment) {
        if (subscribersSelector == null) {
            subscribersSelector = new QBSubscribersSelector();
        }
        subscribersSelector.setEnvironment(environment);
    }

    public QBEnvironment getEnvironment() {
        if (subscribersSelector != null) {
            return subscribersSelector.getEnvironment();
        } else {
            return null;
        }
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public QBEventType getType() {
        return type;
    }

    public void setType(QBEventType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOccuredCount() {
        return occuredCount;
    }

    public void setOccuredCount(Integer occuredCount) {
        this.occuredCount = occuredCount;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public QBNotificationChannel getNotificationChannel() {
        return notificationChannel;
    }

    public void setNotificationChannel(QBNotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    public QBNotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(QBNotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public QBPushType getPushType() {
        return pushType;
    }

    public void setPushType(QBPushType pushType) {
        this.pushType = pushType;
    }

    public String getMessageEncoded() {
        String prefix = Consts.MESSAGE_BASE64_PREFIX;

        if (pushType == null) {
            prefix = "";
        }

        String msg = URLEncoder.encode(message);
        String msgBase64 = prefix + Base64.encode(msg, "UTF-8");
        return msgBase64;
    }

    @Override
    public void copyFieldsTo(QBEntity entity) {
        super.copyFieldsTo(entity);
        QBEvent event = (QBEvent) entity;
        event.setActive(active);
        event.setApplicationId(applicationId);
        event.setDate(date);
        event.setEndDate(endDate);
        event.setType(type);
        event.setMessage(message);
        event.setName(name);
        event.setOccuredCount(occuredCount);
        event.setPeriod(period);
        event.setUserId(userId);
        event.setNotificationChannel(notificationChannel);
    }

    @Override
    public String toString() {
        return "QBEvent{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", active=" + active +
                ", applicationId=" + applicationId +
                ", date=" + date +
                ", endDate=" + endDate +
                ", type=" + type +
                ", message='" + message + '\'' +
                ", name='" + name + '\'' +
                ", occuredCount=" + occuredCount +
                ", period=" + period +
                ", userId=" + userId +
                ", notificationChannel=" + notificationChannel +
                ", subscribersSelector=" + subscribersSelector +
                '}';
    }
}