package com.quickblox.sdk;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.quickblox.gateway.BuildConfig;
import com.quickblox.sdk.data.interfaces.IRatingsDataTableBuilder;
import com.quickblox.sdk.interfaces.IGameMode;

class GameMode implements IGameMode {

	private static final String TAG = "GameMode";

	public GameMode(Cursor cursor){
		if(cursor.moveToFirst()){
			this.mApplicationId = cursor.getInt(cursor.getColumnIndexOrThrow(IGameMode.Tags.application_id.name()));
			this.mId = cursor.getInt(cursor.getColumnIndexOrThrow(IGameMode.Tags.id.name()));
			this.mTitle = cursor.getString(cursor.getColumnIndexOrThrow(IGameMode.Tags.title.name()));
			this.mUserId = cursor.getInt(cursor.getColumnIndexOrThrow(IGameMode.Tags.user_id.name()));
		}
	}

	public GameMode(JsonReader jsonReader){
		try {
			jsonReader.setLenient(true);
			jsonReader.beginObject();

			while( jsonReader.hasNext()){
				final String innerName = jsonReader.nextName();
				final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
				if( innerName.equals( IGameMode.Tags.application_id.name() ) && !isInnerNull ) {
					this.mApplicationId = jsonReader.nextInt();	
				} else {
					if( innerName.equals( IGameMode.Tags.id.name() ) && !isInnerNull ) {
						this.mId = jsonReader.nextInt();
					} else {
						if( innerName.equals( IGameMode.Tags.user_id.name() ) && !isInnerNull ) {
							this.mUserId = jsonReader.nextInt();
						} else {
							if( innerName.equals( IGameMode.Tags.title.name() ) && !isInnerNull ) {
								this.mTitle = jsonReader.nextString();
							} else { 
								jsonReader.skipValue();
							} 
						}
					} 
				}
			}
			jsonReader.endObject();

		} catch (IllegalStateException e) {
			if(BuildConfig.DEBUG){
				Log.e(TAG, " Session exception IllegalStateException " + e.getMessage(),e);
			}
		} catch (IOException e) {
			if(BuildConfig.DEBUG){
				Log.e(TAG, " Session exception IOException " + e.getMessage(),e);
			}
		} 
	}

	private Integer mApplicationId;
	private Integer mId;
	private Integer mUserId;
	private String mTitle;

	@Override
	public Integer getApplicationId() {
		return this.mApplicationId;
	}

	@Override
	public Integer getId() {
		return mId;
	}

	@Override
	public Integer getUserId() {
		return this.mUserId;
	}

	@Override
	public String getTitle() {
		return this.mTitle;
	}

	@Override
	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		values.put(IRatingsDataTableBuilder.IGameModeColumns.APPLICATION_ID, getApplicationId());
		values.put(IRatingsDataTableBuilder.IGameModeColumns.ID, getId());
		values.put(IRatingsDataTableBuilder.IGameModeColumns.USER_ID, getUserId());
		values.put(IRatingsDataTableBuilder.IGameModeColumns.TITLE, getTitle());
		return values;
	}

	@Override
	public JSONObject getJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();

		JSONObject obj = new JSONObject();
		obj.put(IGameMode.Tags.id.name(), getId());
		obj.put(IGameMode.Tags.user_id.name(), getUserId());
		obj.put(IGameMode.Tags.application_id.name(), getApplicationId());
		obj.put(IGameMode.Tags.title.name(), getTitle());

		jsonObject.put(IGameMode.Tags.game_mode.name(), obj);
		return jsonObject;
	}


}
