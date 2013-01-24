package com.quickblox.android.framework.modules.location.net.queries;

import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.modules.location.models.QBLocation;

/**
 * @author: Oleg Soroka
 * Date: 17.07.12
 * Time: 14:47
 */

public class QueryBaseLocation extends Query {

    protected QBLocation location;

    public QueryBaseLocation(QBLocation location) {
        this.location = location;
        setOriginalObject(location);
    }

    public QBLocation getLocation() {
        return location;
    }

    public void setLocation(QBLocation location) {
        this.location = location;
    }
}
