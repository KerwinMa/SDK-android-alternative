package com.quickblox.android.framework.modules.location.models;

import com.quickblox.android.framework.base.models.QBEntityWrap;

/**
 * User: Oleg Soroka
 * Date: 02.10.12
 * Time: 19:27
 */
public class QBPlaceWrap extends QBEntityWrap<QBPlace> {

    private QBPlace place;

    public QBPlaceWrap() {
    }

    public QBPlace getPlace() {
        return place;
    }

    public void setPlace(QBPlace place) {
        this.place = place;
    }

    @Override
    public QBPlace getEntity() {
        return place;
    }
}