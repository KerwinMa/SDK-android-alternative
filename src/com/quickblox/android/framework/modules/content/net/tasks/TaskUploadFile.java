package com.quickblox.android.framework.modules.content.net.tasks;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.definitions.QBErrors;
import com.quickblox.android.framework.base.helpers.ContentType;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.base.net.tasks.QueriesTask;
import com.quickblox.android.framework.modules.content.models.QBFile;
import com.quickblox.android.framework.modules.content.net.results.QBFileUploadResult;
import com.quickblox.android.framework.modules.content.net.results.QBFileUploadTaskResult;
import com.quickblox.android.framework.modules.content.net.server.QBContent;
import com.quickblox.android.framework.modules.messages.models.QBFileStatus;

import java.io.File;

/**
 * User: Oleg Soroka
 * Date: 04.10.12
 * Time: 23:45
 */
public class TaskUploadFile extends QueriesTask {

    private Lo lo = new Lo(this);

    private File file;
    private boolean publicAccess;
    private String tags;
    private int fileSize = 0;

    public TaskUploadFile(File file, boolean publicAccess, String tags, QBCallback callback) {
        super(callback, null);
        this.file = file;
        this.publicAccess = publicAccess;
        this.tags = tags;
    }

    QBFile qbfile = new QBFile();

    @Override
    public void performTask() {
        String contentType = ContentType.getContentType(file);

        qbfile.setName(file.getName());
        qbfile.setPublic(publicAccess);
        qbfile.setContentType(contentType);
        qbfile.setTags(tags);

        QBFileUploadTaskResult fileUploadTaskResult = new QBFileUploadTaskResult();

        if (file.exists() && file.isFile() && contentType != null) {
            QBContent.createFile(qbfile, createFileCallback);
        } else {
            fileUploadTaskResult.getErrors().add(QBErrors.FILE_UPLOAD_ERROR);

            if (!file.exists()) {
                fileUploadTaskResult.getErrors().add(QBErrors.FILE_DOES_NOT_EXIST);
            }

            if (!file.isFile()) {
                fileUploadTaskResult.getErrors().add(QBErrors.PASSED_OBJECT_IS_NOT_FILE);
            }

            if (contentType == null) {
                fileUploadTaskResult.getErrors().add(QBErrors.INCORRECT_CONTENT_TYPE);
            }

            completeTask(fileUploadTaskResult);
            lo.g(fileUploadTaskResult);
        }
    }

    QBCallback createFileCallback = new QBCallback() {
        @Override
        public void onComplete(Result result) {
            if (result.isSuccess()) {
                String params = qbfile.getFileObjectAccess().getParams();
                QBContent.uploadFile(file, params, uploadFileCallback);
            }
        }
    };

    QBCallback uploadFileCallback = new QBCallback() {
        @Override
        public void onComplete(Result result) {
            if (result.isSuccess()) {
                QBFileUploadResult uploadResult = (QBFileUploadResult) result;
                String downloadUrl = uploadResult.getAmazonPostResponse().getLocation();
                qbfile.setDownloadUrl(downloadUrl);

                int fileId = qbfile.getId();
                fileSize = (int) file.length();
                QBContent.declareFileUploaded(fileId, fileSize, declareFileUploadedCallback);
            }
        }
    };

    QBCallback declareFileUploadedCallback = new QBCallback() {
        @Override
        public void onComplete(Result result) {
            if (result.isSuccess()) {

                qbfile.setSize(fileSize);
                qbfile.setStatus(QBFileStatus.COMPLETE);

                QBFileUploadTaskResult fileUploadTaskResult = new QBFileUploadTaskResult();
                fileUploadTaskResult.setFile(qbfile);
                completeTask(fileUploadTaskResult);
            }
        }
    };

}