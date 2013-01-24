package com.quickblox.sdk.interfaces;

import java.util.Date;

import android.content.ContentValues;

/*
{
	  "session": {
	    "application_id": 1339,
	    "created_at": "2013-01-05T22:01:26Z",
	    "device_id": null,
	    "id": 641813,
	    "nonce": 1234,
	    "token": "8c5adcc28670e94d9705c290c8ecce997da9f847",
	    "ts": 1357423252,
	    "updated_at": "2013-01-05T22:01:26Z",
	    "user_id": null
	  }
	}
*/

public interface ISession extends IBase {
	enum Tags{
		session,
		application_id,
		created_at,
		device_id,
		id,
		nonce,
		token,
		ts,
		updated_at,
		user_id;
	}
	String getAccount();
	Integer getApplicationId();
	String getDeviceId();
	String getId();
	Integer getNonce();
	String getToken();
	Date getTimeStamp();
	String getUserId();
	ContentValues getContentValues();
}
