package com.quickblox.android.framework.tests.content;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.net.results.QBFileUploadTaskResult;
import com.quickblox.android.framework.modules.content.net.server.QBContent;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import org.apache.http.HttpStatus;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 19:44
 */
public class TestDeleteFile extends ContentTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();

        fileObject = getRandomFile();
        QBUsers.signIn(testUserAccount, null);
        QBContent.uploadFileTask(fileObject, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                file = ((QBFileUploadTaskResult) result).getFile();
            }
        });
    }

    public void testDeleteFile() {
        QBContent.deleteFile(file, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                checkEmptyResponseBody(result);
            }
        });
    }
}