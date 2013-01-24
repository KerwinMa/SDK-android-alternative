package com.quickblox.sdk.interfaces;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;

/*
 {
      "score": {
        "created_at": "2012-03-15T15:30:11Z",
        "game_mode_id": 11,
        "id": 27,
        "user_id": 3,
        "value": 4695,
        "game_mode_parameter_values": [
 
        ]
      }
    }
 */
public interface IScore  {
	enum Tags{
		score,
		account,
		created_at,
		game_mode_id,
		id,
		user_id,
		value,
		game_mode_parameter_values;
	}
	Integer getId();
	Integer getModeId();
	Integer getUserId();
	Integer getValue();
	Date getCreatedAt();
	List<IGameModeParameterValues> getGameModeParameters();
	
	ContentValues getContentValues();
	JSONObject getJSONObject() throws JSONException;

}
