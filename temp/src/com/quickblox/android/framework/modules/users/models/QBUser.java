package com.quickblox.android.framework.modules.users.models;

import com.google.gson.annotations.SerializedName;
import com.quickblox.android.framework.base.models.QBEntity;
import com.quickblox.android.framework.base.helpers.StringifyArrayList;

/**
 * 
 * QBUser: Daniel Goncharov
 * Date: 04.07.12
 * Time: 16:09
 */
public class QBUser extends QBEntity {

    @SerializedName("full_name")
    protected String fullName;

    protected String email;
    protected String login;
    protected String phone;
    protected String website;

    @SerializedName("last_request_at")
    protected String lastRequestAt;

    @SerializedName("external_user_id")
    protected String externalId;

    @SerializedName("facebook_id")
    protected String facebookId;

    @SerializedName("twitter_id")
    protected String twitterId;

    @SerializedName("blob_id")
    protected Integer blobId;

    @SerializedName("user_tags")
    protected StringifyArrayList<String> tags = new StringifyArrayList<String>();

    protected String password;
    protected String oldPassword;

    public QBUser() { }

    public QBUser(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public QBUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public QBUser(Integer id) {
        this.id = id;
    }

    public QBUser(String login) {
        this.login = login;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLastRequestAt() {
        return lastRequestAt;
    }

    public void setLastRequestAt(String lastRequestAt) {
        this.lastRequestAt = lastRequestAt;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public Integer getFileId() {
        return blobId;
    }

    public void setFileId(int blobId) {
        this.blobId = blobId;
    }

    public StringifyArrayList<String> getTags() {
        return tags;
    }

    public void setTags(StringifyArrayList<String> tags) {
        this.tags = tags;
    }

    public void copyFieldsTo(QBUser user) {
        super.copyFieldsTo(user);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setLogin(login);
        user.setPhone(phone);
        user.setWebsite(website);
        user.setLastRequestAt(lastRequestAt);
        user.setExternalId(externalId);
        user.setFacebookId(facebookId);
        user.setTwitterId(twitterId);
        user.setTags(tags);
    }

    @Override
    public String toString() {
        return "QBUser{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", lastRequestAt='" + lastRequestAt + '\'' +
                ", externalId=" + externalId +
                ", facebookId=" + facebookId +
                ", twitterId=" + twitterId +
                ", blobId=" + blobId +
                ", tags='" + tags + '\'' +
                ", password='" + password + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }
}