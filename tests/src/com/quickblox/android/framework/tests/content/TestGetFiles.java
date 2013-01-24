package com.quickblox.android.framework.tests.content;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.models.QBFile;
import com.quickblox.android.framework.modules.content.net.results.QBFilePagedResult;
import com.quickblox.android.framework.modules.content.net.results.QBFileUploadTaskResult;
import com.quickblox.android.framework.modules.content.net.server.QBContent;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import org.apache.http.HttpStatus;

import java.io.File;
import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 19:56
 */
public class TestGetFiles extends ContentTestCase {

    ArrayList<File> fileObjects;
    ArrayList<QBFile> expectedFiles = new ArrayList<QBFile>();

    @Override
    public void setUp() throws Exception {
        super.setUp();

        fileObjects = getRandomFiles();
        QBUsers.signIn(testUserAccount, null);

        for (File f : fileObjects) {
            QBContent.uploadFileTask(f, new QBCallback() {
                @Override
                public void onComplete(Result result) {
                    QBFile file = ((QBFileUploadTaskResult) result).getFile();
                    expectedFiles.add(file);
                }
            });
        }
    }

    public void testGetFiles() {
        QBContent.getFiles(new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);

                QBFilePagedResult pagedResult = (QBFilePagedResult) result;
                ArrayList<QBFile> actualFiles = pagedResult.getFiles();
                for (int i = 0; i < actualFiles.size(); i++) {
                    QBFile af = actualFiles.get(i);
                    QBFile ef = expectedFiles.get(i);
                    assertEquals(ef.getId(), af.getId());
                    assertEquals(ef.getStatus(), af.getStatus());
                }
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        for (QBFile f : expectedFiles) {
            QBContent.deleteFile(f.getId(), null);
        }
    }
}