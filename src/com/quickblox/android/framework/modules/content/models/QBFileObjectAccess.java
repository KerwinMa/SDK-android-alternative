package com.quickblox.android.framework.modules.content.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Andriy Dmitrenko
 * Date: 28.08.12
 * Time: 14:37
 */
public class QBFileObjectAccess {

    @SerializedName("id")
    private Integer id;

    @SerializedName("blob_id")
    private Integer fileId;

    @SerializedName("expires")
    private String expires;

    @SerializedName("object_access_type")
    private String type;

    @SerializedName("params")
    private String params;

    public QBFileObjectAccess() {
    }

    public QBFileObjectAccess(Integer fileId) {
        this.fileId = fileId;
    }

    public void copyFieldsTo(QBFileObjectAccess fileObjectAccess) {
        fileObjectAccess.setId(id);
        fileObjectAccess.setFileId(fileId);
        fileObjectAccess.setExpires(expires);
        fileObjectAccess.setType(type);
        fileObjectAccess.setParams(params);
    }

    //

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "QBFileObjectAccess{" +
                "id=" + id +
                ", fileId=" + fileId +
                ", expires='" + expires + '\'' +
                ", type='" + type + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}