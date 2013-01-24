package com.quickblox.android.framework.modules.messages.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 26.09.12
 * Time: 16:27
 */
public class QBEventWrap1 {

    // TODO [REST API] исправить events на event когда поправят в апи
    @SerializedName("events")
    QBEvent event;

    public QBEventWrap1() {
    }

    public QBEvent getEvent() {
        return event;
    }

    public void setEvent(QBEvent event) {
        this.event = event;
    }
}