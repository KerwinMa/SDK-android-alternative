package com.quickblox.android.framework.modules.content.net.results;

import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.models.QBFile;

/**
 * User: Oleg Soroka
 * Date: 05.10.12
 * Time: 00:37
 */
public class QBFileUploadTaskResult extends Result {

    private QBFile file;

    public QBFile getFile() {
        return file;
    }

    public void setFile(QBFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "QBFileUploadTaskResult{" +
                "file=" + file +
                '}';
    }
}