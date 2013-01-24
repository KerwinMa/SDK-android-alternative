package com.quickblox.android.framework.tests.content;

import com.quickblox.android.framework.base.definitions.QBErrors;
import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.net.results.QBFileUploadTaskResult;
import com.quickblox.android.framework.modules.content.net.server.QBContent;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import com.quickblox.android.framework.tests.helpers.FileHelper;

import java.io.File;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 14:07
 */
public class TestUploadFileTaskIncorrect extends ContentTestCase {

    @Override
    public void setUp() throws Exception {
        QBUsers.signIn(testUserAccount, null);
    }

    public void testUploadFileTask_FileDoesNotExist() {
        File incorrectFile = new File("some_incorrect_path");
        QBContent.uploadFileTask(incorrectFile, true, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkIfNotSuccess(result);

                QBFileUploadTaskResult fileResult = (QBFileUploadTaskResult) result;
                file = fileResult.getFile();

                assertNull(file);

                assertTrue(result.getErrors().contains(QBErrors.FILE_UPLOAD_ERROR));
                assertTrue(result.getErrors().contains(QBErrors.FILE_DOES_NOT_EXIST));
            }
        });
    }

    public void testUploadFileTask_PassedObjectIsNotFile() {
        File incorrectFile = FileHelper.getDirectory();
        QBContent.uploadFileTask(incorrectFile, true, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkIfNotSuccess(result);

                QBFileUploadTaskResult fileResult = (QBFileUploadTaskResult) result;
                file = fileResult.getFile();

                assertNull(file);

                assertTrue(result.getErrors().contains(QBErrors.FILE_UPLOAD_ERROR));
                assertTrue(result.getErrors().contains(QBErrors.PASSED_OBJECT_IS_NOT_FILE));
            }
        });
    }

}