package com.quickblox.android.framework.modules.messages.models;

import android.content.Context;
import android.provider.Settings;
import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 20.09.12
 * Time: 12:25
 */
public class QBDevice {

    @SerializedName("udid")
    String id;
    @SerializedName("platform")
    QBPlatform platform;

    public QBDevice() {
    }

    public QBDevice(Context context) {
        id = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        platform = QBPlatform.ANDROID;
    }

    public QBDevice(QBPlatform platform, String id) {
        this.id = id;
        this.platform = platform;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QBPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(QBPlatform platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "QBDevice{" +
                "platform=" + platform +
                ", id='" + id + '\'' +
                '}';
    }
}