package com.quickblox.android.framework.modules.location.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntity;

/**
 * User: Oleg Soroka
 * Date: 17.08.12
 * Time: 10:50
 */
public class QBPlace extends QBEntity {

    private String title;
    private String description;
    private String address;

    @SerializedName("photo_id")
    private Integer photoId;

    @SerializedName("geo_data_id")
    private Integer locationId;

    public QBPlace() {
    }

    public QBPlace(int id) {
        super(id);
    }

    public QBPlace(Integer locationId, String title) {
        this.locationId = locationId;
        this.title = title;
    }

    public QBPlace(Integer locationId, String title, String description) {
        this.locationId = locationId;
        this.title = title;
        this.description = description;
    }

    public QBPlace(Integer locationId, String title, String description, String address) {
        this.locationId = locationId;
        this.title = title;
        this.description = description;
        this.address = address;
    }

    public QBPlace(Integer locationId, String title, String description, String address, Integer photoId) {
        this.locationId = locationId;
        this.title = title;
        this.description = description;
        this.address = address;
        this.photoId = photoId;
    }

    //

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public void copyFieldsTo(QBEntity entity) {
        super.copyFieldsTo(entity);
        QBPlace place = (QBPlace) entity;
        place.setTitle(title);
        place.setDescription(description);
        place.setAddress(address);
        place.setPhotoId(photoId);
        place.setLocationId(locationId);
    }

    @Override
    public String toString() {
        return "QBPlace{" +
                "id=" + id + '\'' +
                ", createdAt=" + createdAt + '\'' +
                ", updatedAt=" + updatedAt + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", photoId=" + photoId +
                ", locationId=" + locationId +
                '}';
    }
}