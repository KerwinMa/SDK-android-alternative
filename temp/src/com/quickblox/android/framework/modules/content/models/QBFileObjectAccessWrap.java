package com.quickblox.android.framework.modules.content.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 05.10.12
 * Time: 20:56
 */
public class QBFileObjectAccessWrap {

    @SerializedName("blob_object_access")
    private QBFileObjectAccess fileObjectAccess;

    public QBFileObjectAccessWrap() {
    }

    public QBFileObjectAccess getFileObjectAccess() {
        return fileObjectAccess;
    }

    public void setFileObjectAccess(QBFileObjectAccess fileObjectAccess) {
        this.fileObjectAccess = fileObjectAccess;
    }
}