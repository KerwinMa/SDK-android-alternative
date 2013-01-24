package com.quickblox.android.framework.tests.content;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.net.results.QBFileUploadTaskResult;
import com.quickblox.android.framework.modules.content.net.server.QBContent;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 14:07
 */
public class TestUploadFileTask extends ContentTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();

        fileObject = getRandomFile();
        QBUsers.signIn(testUserAccount, null);
    }

    public void testUploadFileTaskPublic() {
        final boolean publicFlag = true;
        QBContent.uploadFileTask(fileObject, publicFlag, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                QBFileUploadTaskResult fileResult = (QBFileUploadTaskResult) result;
                file = fileResult.getFile();

                System.out.println(file);

                assertEquals(fileObject.getName(), file.getName());
                assertEquals((Object) publicFlag, file.isPublic());
                assertEquals((int) fileObject.length(), file.getSize());
                assertTrue(!file.getDownloadUrl().equals(null));
            }
        });
    }

    public void testUploadFileTaskPrivate() {
        final boolean publicFlag = false;
        QBContent.uploadFileTask(fileObject, publicFlag, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                QBFileUploadTaskResult fileResult = (QBFileUploadTaskResult) result;
                file = fileResult.getFile();

                System.out.println(file);

                assertEquals(fileObject.getName(), file.getName());
                assertEquals((Object) publicFlag, file.isPublic());
                assertEquals((int) fileObject.length(), file.getSize());
                assertTrue(!file.getDownloadUrl().equals(null));
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        QBContent.deleteFile(file.getId(), null);
    }
}