package com.quickblox.android.framework.base.models;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Igor Khomenko
 */
public class QBEntity {

    @SerializedName("id")
    protected Integer id;

    @SerializedName("created_at")
    protected Date createdAt;

    @SerializedName("updated_at")
    protected Date updatedAt;

    public QBEntity() {
    }

    public QBEntity(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void copyFieldsTo(QBEntity entity) {
        entity.setId(id);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);
    }

    //

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");

    public String getFCreatedAt() {
        return sdf.format(createdAt);
    }

    public String getFUpdatedAt() {
        return sdf.format(updatedAt);
    }


    @Override
    public String toString() {
        return "QBEntity{" +
                "id=" + id +
                ", createdAt=" + getFCreatedAt() +
                ", updatedAt=" + getFUpdatedAt() +
                '}';
    }
}