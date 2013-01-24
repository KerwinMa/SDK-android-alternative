package com.quickblox.android.framework.base.helpers;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;

/**
 * User: Oleg Soroka
 * Date: 06.10.12
 * Time: 11:31
 */
public class ContentType {

    public static String getContentType(File file) {
        if (file != null) {
            String filePath = file.getAbsolutePath();
            return getContentType(filePath);
        }
        return null;
    }

    public static String getContentType(String filePath) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(filePath);
        return type;
    }

}