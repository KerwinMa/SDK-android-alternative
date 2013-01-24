package com.quickblox.android.framework.modules.content.net.server;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.server.BaseService;
import com.quickblox.android.framework.modules.content.models.QBFile;
import com.quickblox.android.framework.modules.content.models.QBFileObjectAccess;
import com.quickblox.android.framework.modules.content.net.queries.*;
import com.quickblox.android.framework.modules.content.net.tasks.TaskGetDownloadLink;
import com.quickblox.android.framework.modules.content.net.tasks.TaskUploadFile;

import java.io.File;

/**
 * User: Oleg Soroka
 * Date: 28.08.12
 * Time: 12:36
 */
public class QBContent extends BaseService {

    // Create file

    public static void createFile(QBFile file, QBCallback callback) {
        createFile(file, callback, null);
    }

    public static void createFile(QBFile file, QBCallback callback, Object context) {
        QueryCreateFile query = new QueryCreateFile(file);
        query.performAsyncWithCallback(callback, context);
    }

    // Upload file

    public static void uploadFile(File file, String params, QBCallback callback) {
        uploadFile(file, params, callback, null);
    }

    public static void uploadFile(File file, String params, QBCallback callback, Object context) {
        QueryUploadFile query = new QueryUploadFile(file, params);
        query.performAsyncWithCallback(callback, context);
    }

    // Declare file uploaded

    public static void declareFileUploaded(int fileId, int fileSize, QBCallback callback) {
        declareFileUploaded(fileId, fileSize, callback, null);
    }

    public static void declareFileUploaded(int fileId, int fileSize, QBCallback callback, Object context) {
        QueryDeclareFileUploaded query = new QueryDeclareFileUploaded(fileId, fileSize);
        query.performAsyncWithCallback(callback, context);
    }

    // Upload file task

    public static void uploadFileTask(File file, boolean publicAccess, String tags, QBCallback callback) {
        TaskUploadFile uploadFileTask = new TaskUploadFile(file, publicAccess, tags, callback);
        uploadFileTask.performTask();
    }

    public static void uploadFileTask(File file, boolean publicAccess, QBCallback callback) {
        uploadFileTask(file, publicAccess, null, callback);
    }

    public static void uploadFileTask(File file, QBCallback callback) {
        uploadFileTask(file, false, null, callback);
    }

    public static void uploadFileTask(String filePath, boolean publicAccess, String tags, QBCallback callback) {
        uploadFileTask(new File(filePath), publicAccess, tags, callback);
    }

    public static void uploadFileTask(String filePath, boolean publicAccess, QBCallback callback) {
        uploadFileTask(new File(filePath), publicAccess, null, callback);
    }

    public static void uploadFileTask(String filePath, QBCallback callback) {
        uploadFileTask(new File(filePath), false, null, callback);
    }

    // Get file

    public static void getFile(QBFile file, QBCallback callback) {
        getFile(file, callback, null);
    }

    public static void getFile(QBFile file, QBCallback callback, Object context) {
        QueryGetFile query = new QueryGetFile(file);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getFile(int fileId, QBCallback callback) {
        getFile(new QBFile(fileId), callback, null);
    }

    public static void getFile(int fileId, QBCallback callback, Object context) {
        getFile(new QBFile(fileId), callback, context);
    }

    // Get files

    public static void getFiles(QBCallback callback) {
        getFiles(callback, null);
    }

    public static void getFiles(QBCallback callback, Object context) {
        QueryGetFiles query = new QueryGetFiles();
        query.performAsyncWithCallback(callback, context);
    }

    // Update

    public static void updateFile(QBFile file, QBCallback callback) {
        updateFile(file, callback, null);
    }

    public static void updateFile(QBFile file, QBCallback callback, Object context) {
        QueryUpdateFile query = new QueryUpdateFile(file);
        query.performAsyncWithCallback(callback, context);
    }

    // Blob object access

    public static void getFileObjectAccess(int fileId, QBCallback callback) {
        getFileObjectAccess(fileId, callback, null);
    }

    public static void getFileObjectAccess(QBFileObjectAccess fileObjectAccess, QBCallback callback) {
        getFileObjectAccess(fileObjectAccess, callback, null);
    }

    public static void getFileObjectAccess(int fileId, QBCallback callback, Object context) {
        getFileObjectAccess(new QBFileObjectAccess(fileId), callback, context);
    }

    public static void getFileObjectAccess(QBFileObjectAccess fileObjectAccess, QBCallback callback, Object context) {
        QueryGetFileObjectAccess query = new QueryGetFileObjectAccess(fileObjectAccess);
        query.performAsyncWithCallback(callback, context);
    }

    // Get download link

    public static void getFileDownloadLink(QBFile file, QBCallback callback) {
        TaskGetDownloadLink task = new TaskGetDownloadLink(file, callback);
        task.performTask();
    }

    public static void getFileDownloadLink(int fileId, QBCallback callback) {
        getFileDownloadLink(new QBFile(fileId), callback);
    }

    // Download

    // TODO важно! реализовать загрузку файла напрямую из InputStream

    public static void downloadFile(String uid, QBCallback callback) {
        downloadFile(new QBFile(uid), callback, null);
    }

    public static void downloadFile(String uid, QBCallback callback, Object context) {
        downloadFile(new QBFile(uid), callback, context);
    }

    public static void downloadFile(QBFile file, QBCallback callback) {
        downloadFile(file, callback, null);
    }

    public static void downloadFile(QBFile file, QBCallback callback, Object context) {
        QueryDownloadFile query = new QueryDownloadFile(file);
        query.performAsyncWithCallback(callback, context);
    }

    // Delete

    public static void deleteFile(int fileId, QBCallback callback) {
        deleteFile(new QBFile(fileId), callback, null);
    }

    public static void deleteFile(int fileId, QBCallback callback, Object context) {
        deleteFile(new QBFile(fileId), callback, context);
    }

    public static void deleteFile(QBFile file, QBCallback callback) {
        deleteFile(file, callback, null);
    }

    public static void deleteFile(QBFile file, QBCallback callback, Object context) {
        QueryDeleteFile query = new QueryDeleteFile(file);
        query.performAsyncWithCallback(callback, context);
    }

    // Increment ref counter

    public static void incrementRefCount(int fileId, QBCallback callback) {
        incrementRefCount(new QBFile(fileId), callback, null);
    }

    public static void incrementRefCount(int fileId, QBCallback callback, Object context) {
        incrementRefCount(new QBFile(fileId), callback, context);
    }

    public static void incrementRefCount(QBFile file, QBCallback callback) {
        incrementRefCount(file, callback, null);
    }

    public static void incrementRefCount(QBFile file, QBCallback callback, Object context) {
        QueryIncrementRefCount query = new QueryIncrementRefCount(file);
        query.performAsyncWithCallback(callback, context);
    }

}