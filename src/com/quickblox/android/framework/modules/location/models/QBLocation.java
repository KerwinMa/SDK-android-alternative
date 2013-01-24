package com.quickblox.android.framework.modules.location.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntity;

/**
 * User: Oleg Soroka
 * Date: 17.08.12
 * Time: 10:50
 */
public class QBLocation extends QBEntity {

    @SerializedName("user_id")
    private Integer userId;
    @SerializedName("application_id")
    private Integer applicationId;

    private Double latitude;
    private Double longitude;
    private String status;

    public QBLocation() {
    }

    public QBLocation(int id) {
        this.id = id;
    }

    public QBLocation(double latitude, double longitude, String status) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void copyFieldsTo(QBLocation location) {
        super.copyFieldsTo(location);

        location.setApplicationId(applicationId);
        location.setUserId(userId);
        location.setStatus(status);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
    }

    @Override
    public String toString() {
        return "QBLocation{" +
                "userId=" + userId +
                ", applicationId=" + applicationId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}