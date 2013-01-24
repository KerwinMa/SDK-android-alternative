package com.quickblox.android.framework.modules.location.net.queries;

import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.modules.location.models.QBPlace;

/**
 * @author: Oleg Soroka
 * Date: 17.07.12
 * Time: 14:47
 */

public class QueryBasePlace extends Query {

    protected QBPlace place;

    public QueryBasePlace(QBPlace place) {
        this.place = place;
        setOriginalObject(place);
    }

    public QBPlace getPlace() {
        return place;
    }

    public void setPlace(QBPlace place) {
        this.place = place;
    }
}
