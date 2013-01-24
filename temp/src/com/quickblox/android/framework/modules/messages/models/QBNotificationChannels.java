package com.quickblox.android.framework.modules.messages.models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: Oleg Soroka
 * Date: 20.09.12
 * Time: 12:24
 */
public class QBNotificationChannels extends ArrayList<QBNotificationChannel> {

    public QBNotificationChannels(QBNotificationChannel... channels) {
        addAll(Arrays.asList(channels));
    }

    public QBNotificationChannels(QBNotificationChannel channel) {
        add(channel);
    }

    public ArrayList<String> getCaptions() {
        ArrayList<String> captions = new ArrayList<String>();
        for (QBNotificationChannel channel : this) {
            captions.add(channel.toString());
        }
        return captions;
    }

}
