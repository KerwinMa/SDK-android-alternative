package com.quickblox.android.framework.tests.content;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.content.net.results.QBFileDownloadResult;
import com.quickblox.android.framework.modules.content.net.results.QBFileUploadTaskResult;
import com.quickblox.android.framework.modules.content.net.server.QBContent;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 20:27
 */
public class TestDownloadFile extends ContentTestCase {

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

    public void testDownloadFile() {
        QBContent.downloadFile(file, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                QBFileDownloadResult fileDownloadResult = (QBFileDownloadResult) result;

                try {
                    InputStream in = new java.io.FileInputStream(fileObject);
                    byte[] expectedContent = IOUtils.toByteArray(in);
                    byte[] actualContent = fileDownloadResult.getContent();
                    assertTrue(Arrays.equals(expectedContent, actualContent));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        QBContent.deleteFile(file.getId(), null);
    }
}