package com.quickblox.sdk.interfaces;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;


/*
"game_mode_parameter_values": [
          {
            "created_at": "2013-01-20T20:57:17Z",
            "game_mode_parameter_id": 184,
            "id": 644,
            "score_id": 1988,
            "updated_at": "2013-01-20T20:57:17Z",
            "value": "test1"
          },
          {
            "created_at": "2013-01-20T20:57:17Z",
            "game_mode_parameter_id": 185,
            "id": 645,
            "score_id": 1988,
            "updated_at": "2013-01-20T20:57:17Z",
            "value": "test2"
          },
          {
            "created_at": "2013-01-20T20:57:17Z",
            "game_mode_parameter_id": 186,
            "id": 646,
            "score_id": 1988,
            "updated_at": "2013-01-20T20:57:17Z",
            "value": "test3"
          }
        ]
 */


public interface IGameModeParameterValues {
	enum Tags{
		account,
		game_mode_parameter_values,
		id,
		game_mode_parameter_id,
		score_id,
		value,
		created_at,
		updated_at;
	}
	
	Integer getId();
	Integer getGameModeParameterId();
	Integer getScoreId();
	Date getCreatedAt();
	Date getUpdatedAt();
	String getValue();
	
	ContentValues getContentValues();
	JSONObject getJSONObject() throws JSONException;
}
