package com.quickblox.android.framework.tests.content;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.QBStringResult;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.models.QBFile;
import com.quickblox.android.framework.modules.content.net.results.QBFileUploadTaskResult;
import com.quickblox.android.framework.modules.content.net.server.QBContent;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import org.apache.http.HttpStatus;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 19:56
 */
public class TestGetFile extends ContentTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();

        fileObject = getRandomFile();
        QBUsers.signIn(testUserAccount, null);
        QBContent.uploadFileTask(fileObject, true, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                file = ((QBFileUploadTaskResult) result).getFile();
            }
        });
    }

    public void testGetFile() {
        final QBFile newFile = new QBFile(file.getId());
        QBContent.getFile(newFile, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                assertEquals(file.getId(), newFile.getId());
                assertEquals(file.getUid(), newFile.getUid());
                assertEquals(file.getContentType(), newFile.getContentType());
                assertEquals(file.getSize(), newFile.getSize());
                assertEquals(file.getName(), newFile.getName());
                assertEquals(file.getStatus(), newFile.getStatus());
                assertEquals(file.getTags(), newFile.getTags());
            }
        });
    }

    public void testGetFileDownloadLink() {
        final QBFile newFile = new QBFile(file.getId());
        QBContent.getFileDownloadLink(file, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                QBStringResult stringResult = (QBStringResult) result;
                String url = stringResult.getString();
                assertNotNull(url);
                // TODO починить сравнение ссылок
//                assertEquals(file.getDownloadUrl(), url);
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        QBContent.deleteFile(file.getId(), null);
    }
}