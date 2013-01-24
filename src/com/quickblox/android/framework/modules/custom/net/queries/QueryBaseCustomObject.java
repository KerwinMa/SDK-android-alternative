package com.quickblox.android.framework.modules.custom.net.queries;

import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;

/**
 * User: Oleg Soroka
 * Date: 13.08.12
 * Time: 11:46
 */
public class QueryBaseCustomObject extends Query {

    public QueryBaseCustomObject(QBCustomObject customObject) {
        this.customObject = customObject;
        setOriginalObject(customObject);
    }

    protected QBCustomObject customObject;

    public void setCustomObject(QBCustomObject customObject) {
        this.customObject = customObject;
    }

    public QBCustomObject getCustomObject() {
        return customObject;
    }

}