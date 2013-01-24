package com.quickblox.sdk.interfaces;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;

/*
 	"user": {
        "id": 14909,
        "owner_id": 984,
        "full_name": "Leslie Drewery",
        "email": "pocketpicasa@googlemail.com",
        "login": "MadMouse",
        "phone": "+447813299327",
        "website": null,
        "created_at": "2012-10-03T19:36:07Z",
        "updated_at": "2013-01-04T13:31:33Z",
        "last_request_at": "2013-01-04T13:31:33Z",
        "external_user_id": null,
        "facebook_id": null,
        "twitter_id": null,
        "blob_id": null,
        "user_tags": null
      }

*/
public interface IUser extends IBase {
	enum Tags{
		user,
		id,
		owner_id,
		full_name,
		email,
		login,
		phone,
		website,
		created_at,
		updated_at,
		last_request_at,
		external_user_id,
		facebook_id,
		twitter_id,
		blob_id,
		user_tags,
		password;
		
	};
	
	Integer getId();
	Integer getOwnerId();
	String getFullName();
	String getEmailAddress();
	String getUserLogin();
	String getPhone();
	String getWebSite();
	Integer getExternalUserId();
	Integer getFaceBookId();
	Integer getTwitterId();
	Integer getBlobId();
	String getTagList();
	String getPassword();
	String getAccount();
	ContentValues getContentValues();
	JSONObject getJSONObject() throws JSONException;
}
