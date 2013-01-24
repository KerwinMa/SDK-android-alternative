package com.quickblox.sdk.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;

/*
{
    "game_mode": {
      "application_id": 140,
      "id": 1,
      "title": "111",
      "user_id": 563
    }
  }
*/
public interface IGameMode {
	enum Tags{
		game_mode,
		application_id,
		id,
		title,
		user_id;
	}

	Integer getApplicationId();
	Integer getId();
	Integer getUserId();
	String getTitle();
	ContentValues getContentValues();
	JSONObject getJSONObject() throws JSONException;
}
