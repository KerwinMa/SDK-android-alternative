package com.quickblox.android.framework.modules.content.net.tasks;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.QBStringResult;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.base.net.tasks.QueriesTask;
import com.quickblox.android.framework.modules.content.models.QBFile;
import com.quickblox.android.framework.modules.content.models.QBFileObjectAccess;
import com.quickblox.android.framework.modules.content.net.server.QBContent;

/**
 * User: Oleg Soroka
 * Date: 05.10.12
 * Time: 21:16
 */
public class TaskGetDownloadLink extends QueriesTask {

    private QBFile file;

    public TaskGetDownloadLink(QBFile file, QBCallback callback) {
        super(callback, null);
        this.file = file;
    }

    private QBFileObjectAccess fileObjectAccess;

    public void performTask() {
        fileObjectAccess = new QBFileObjectAccess(file.getId());
        QBContent.getFileObjectAccess(fileObjectAccess, fileObjectAccessCallback);
    }

    QBCallback fileObjectAccessCallback = new QBCallback() {
        @Override
        public void onComplete(Result result) {
            String downloadLink = null;
            if (result.isSuccess()) {
                downloadLink = fileObjectAccess.getParams();
            }
            QBStringResult stringResult = new QBStringResult(downloadLink);
            completeTask(stringResult);
        }
    };
}