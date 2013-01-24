package com.quickblox.android.framework.modules.messages.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.modules.messages.models.QBEvent;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 16:27
 */
public class QBEventWrap {

    @SerializedName("event")
    QBEvent event;

    public QBEventWrap() {
    }

    public QBEvent getEvent() {
        return event;
    }

    public void setEvent(QBEvent event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "QBEventWrap{" +
                "event=" + event +
                '}';
    }
}