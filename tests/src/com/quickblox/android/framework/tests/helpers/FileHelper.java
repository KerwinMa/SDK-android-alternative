package com.quickblox.android.framework.tests.helpers;

import android.os.Environment;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 16:28
 */
public class FileHelper {

    private static String sdcardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static String sdcardTestFilesRoot = "qb_sdk_test_files";

    public static File getDirectory() {
        File filesRoot = new File(sdcardRoot + "/" + sdcardTestFilesRoot);
        if (!filesRoot.exists()) {
            boolean result = filesRoot.mkdir();
        }
        return filesRoot;
    }

    public static File getFileFromAsset(InputStream inputStream, String fileName) {
        File filesRoot = getDirectory();

        try {
            File file = new File(filesRoot, fileName);
            System.out.println(file.getAbsoluteFile());
            byte[] content = IOUtils.toByteArray(inputStream);
            FileOutputStream out = new FileOutputStream(file);
            out.write(content);
            out.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}