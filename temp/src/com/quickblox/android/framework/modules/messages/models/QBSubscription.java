package com.quickblox.android.framework.modules.messages.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 20.09.12
 * Time: 12:23
 */
public class QBSubscription {

    @SerializedName("id")
    Integer id;
    @SerializedName("notification_channel")
    QBNotificationChannel notificationChannel;
    @SerializedName("device")
    QBDevice device;

    public QBSubscription() {
    }

    public QBSubscription(Integer id) {
        this.id = id;
    }

    public QBSubscription(QBNotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    public QBNotificationChannel getNotificationChannel() {
        return notificationChannel;
    }

    public void setNotificationChannel(QBNotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    public QBDevice getDevice() {
        return device;
    }

    public void setDevice(QBDevice device) {
        this.device = device;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void copyFieldsTo(QBSubscription subscription) {
        subscription.setId(id);
        subscription.setNotificationChannel(notificationChannel);
        subscription.setDevice(device);
    }

    @Override
    public String toString() {
        return "QBSubscription{" +
                "id=" + id +
                ", notificationChannel=" + notificationChannel +
                ", device=" + device +
                '}';
    }
}
