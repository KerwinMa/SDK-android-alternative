package com.quickblox.android.framework.base.helpers;

import android.os.Environment;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 16:28
 */
public class FileHelper {

    private static String sdcardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static File getDirectory(String rootFolder) {
        File filesRoot = new File(sdcardRoot + "/" + rootFolder);
        if (!filesRoot.exists()) {
            boolean result = filesRoot.mkdir();
        }
        return filesRoot;
    }

    public static File getFileInputStream(InputStream inputStream, String fileName, String rootFolder) {
        File filesRoot = getDirectory(rootFolder);

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