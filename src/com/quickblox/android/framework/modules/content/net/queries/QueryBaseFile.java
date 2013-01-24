package com.quickblox.android.framework.modules.content.net.queries;

import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.modules.content.models.QBFile;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 14:55
 */
public class QueryBaseFile extends Query {

    protected QBFile file;

    public QueryBaseFile(QBFile file) {
        this.file = file;
        setOriginalObject(file);
    }

    public QBFile getFile() {
        return file;
    }

    public void setFile(QBFile file) {
        this.file = file;
    }
}
