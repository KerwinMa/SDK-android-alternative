package com.quickblox.android.framework.modules.custom.models;

import com.quickblox.android.framework.base.models.QBEntity;

import java.util.HashMap;

/**
 * User: Oleg Soroka
 * Date: 13.08.12
 * Time: 11:34
 */
public class QBCustomObject extends QBEntity {

    private String className;

    private HashMap<String, Object> fields = new HashMap<String, Object>();

    private String customObjectId;

    private Integer userId;

    public QBCustomObject() {
    }

    public QBCustomObject(String className) {
        this.className = className;
    }

    public QBCustomObject(String className, String id) {
        this.className = className;
        this.customObjectId = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public QBCustomObject put(String fieldName, Object value) {
        fields.put(fieldName, value);
        return this;
    }

    // Sets "null" for specified field.
    // It allows add "null" to request like "...&power=null&..."
    // If you will try set real null but not "null" it doesn't add parameter to request.
    public QBCustomObject setNullFor(String fieldName) {
        put(fieldName, "null");
        return this;
    }

    public HashMap<String, Object> getFields() {
        return fields;
    }

    public void setFields(HashMap<String, Object> fields) {
        this.fields = fields;
    }

    public String getCustomObjectId() {
        return customObjectId;
    }

    public void setCustomObjectId(String id) {
        this.customObjectId = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public void copyFieldsTo(QBEntity entity) {
        QBCustomObject customObject = (QBCustomObject) entity;
        customObject.setCustomObjectId(getCustomObjectId());
        customObject.setUserId(getUserId());
        customObject.setFields(getFields());
        customObject.setCreatedAt(createdAt);
        customObject.setUpdatedAt(updatedAt);
    }

    @Override
    public String toString() {
        return "QBCustomObject{" +
                "customObjectId='" + customObjectId + '\'' +
                ", userId=" + userId +
                ", className='" + className + '\'' +
                ", createdAt='" + getCreatedAt() + '\'' +
                ", updatedAt='" + getUpdatedAt() + '\'' +
                ", fields=" + fields +
                '}';
    }
}