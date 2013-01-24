package com.quickblox.android.framework.modules.content.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntity;
import com.quickblox.android.framework.modules.messages.models.QBFileStatus;

import java.util.Date;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 14:38
 */
public class QBFile extends QBEntity {

    @SerializedName("uid")
    private String uid;

    @SerializedName("content_type")
    private String contentType;

    @SerializedName("name")
    private String name;

    @SerializedName("size")
    private int size;

    @SerializedName("ref_count")
    private Integer referencesCount;

    @SerializedName("blob_status")
    private QBFileStatus status;

    @SerializedName("set_completed_at")
    private Date completedAt;

    @SerializedName("public")
    private Boolean publicFlag;

    @SerializedName("last_read_access_ts")
    private Integer lastReadAccessTime;

    @SerializedName("lifetime")
    private Integer lifeTime;

    @SerializedName("tags")
    private String tags;

    @SerializedName("blob_object_access")
    QBFileObjectAccess fileObjectAccess;

    public QBFile() {
    }

    public QBFile(Integer id) {
        super(id);
    }

    public QBFile(String uid) {
        this.uid = uid;
    }

    @Override
    public void copyFieldsTo(QBEntity entity) {
        super.copyFieldsTo(entity);

        QBFile file = (QBFile) entity;
        file.setUid(uid);
        file.setContentType(contentType);
        file.setName(name);
        file.setSize(size);
        file.setReferencesCount(referencesCount);
        file.setStatus(status);
        file.setCompletedAt(completedAt);
        file.setPublic(publicFlag);
        file.setLastReadAccessTime(lastReadAccessTime);
        file.setLifeTime(lifeTime);
        file.setFileObjectAccess(fileObjectAccess);
    }

    //

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Integer getReferencesCount() {
        return referencesCount;
    }

    public void setReferencesCount(Integer referencesCount) {
        this.referencesCount = referencesCount;
    }

    public QBFileStatus getStatus() {
        return status;
    }

    public void setStatus(QBFileStatus status) {
        this.status = status;
    }

    public void setStatus(boolean status) {
        if (status) {
            this.setStatus(QBFileStatus.COMPLETE);
        } else {
            this.setStatus(QBFileStatus.UNCOMPLETE);
        }
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Boolean isPublic() {
        return publicFlag;
    }

    public void setPublic(Boolean publicFlag) {
        this.publicFlag = publicFlag;
    }

    public Integer getLastReadAccessTime() {
        return lastReadAccessTime;
    }

    public void setLastReadAccessTime(Integer lastReadAccessTime) {
        this.lastReadAccessTime = lastReadAccessTime;
    }

    public Integer getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Integer lifeTime) {
        this.lifeTime = lifeTime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public QBFileObjectAccess getFileObjectAccess() {
        return fileObjectAccess;
    }

    public void setFileObjectAccess(QBFileObjectAccess fileObjectAccess) {
        this.fileObjectAccess = fileObjectAccess;
    }

    @Override
    public String toString() {
        return "QBFile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uid='" + uid + '\'' +
                ", status=" + status +
                ", createdAt=" + getFCreatedAt() +
                ", updatedAt=" + getFUpdatedAt() +
                ", contentType='" + contentType + '\'' +
                ", size=" + size +
                ", referencesCount=" + referencesCount +
                ", completedAt=" + completedAt +
                ", publicFlag=" + publicFlag +
                ", lastReadAccessTime=" + lastReadAccessTime +
                ", lifeTime=" + lifeTime +
                ", tags='" + tags + '\'' +
                ", fileObjectAccess=" + fileObjectAccess +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }

    //

    @Expose
    String downloadUrl;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}