package com.quickblox.sdk;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.quickblox.gateway.BuildConfig;
import com.quickblox.sdk.data.interfaces.IRatingsDataTableBuilder.IGameModeParameterValuesColumns;
import com.quickblox.sdk.interfaces.IGameModeParameterValues;

public class GameModeParameterValues implements IGameModeParameterValues {

	private static final String TAG = "GameModeParameterValues";
	private Integer mId;
	private Integer mGameModeParameterId;
	private Integer mScoreId;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private Date mCreatedAt;
	private Date mUpdatedAt;
	private String mValue;
	private String mAccount;

	
	public GameModeParameterValues(String account, Cursor cursor){
		this.mAccount = account;
		if(cursor.moveToFirst()){
			this.mGameModeParameterId = cursor.getInt(cursor.getColumnIndexOrThrow(IGameModeParameterValuesColumns.GAME_MODE_PARAMETER_ID));
			this.mId = cursor.getInt(cursor.getColumnIndexOrThrow(IGameModeParameterValuesColumns.ID));
			this.mScoreId = cursor.getInt(cursor.getColumnIndexOrThrow(IGameModeParameterValuesColumns.SCORE_ID));
			this.mValue = cursor.getString(cursor.getColumnIndexOrThrow(IGameModeParameterValuesColumns.VALUE));
			this.mCreatedAt = new Date( cursor.getLong(cursor.getColumnIndexOrThrow(IGameModeParameterValuesColumns.CREATED_AT)));
			this.mUpdatedAt = new Date(cursor.getLong(cursor.getColumnIndexOrThrow(IGameModeParameterValuesColumns.UPDATED_AT)));
		}
	}

	public GameModeParameterValues(String account, JsonReader jsonReader){
		this.mAccount = account;
		try {
			jsonReader.setLenient(true);
			jsonReader.beginObject();

			while( jsonReader.hasNext()){
				final String innerName = jsonReader.nextName();
				final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;

				if( innerName.equals( IGameModeParameterValues.Tags.game_mode_parameter_id.name() ) && !isInnerNull ) {
					this.mGameModeParameterId = jsonReader.nextInt();	
				} else {

					if( innerName.equals( IGameModeParameterValues.Tags.value.name() ) && !isInnerNull ) {
						this.mValue = jsonReader.nextString();
					} else {
						if( innerName.equals( IGameModeParameterValues.Tags.created_at.name() ) && !isInnerNull ) {
							this.mCreatedAt = dateFormat.parse(jsonReader.nextString());
						} else 
							if( innerName.equals( IGameModeParameterValues.Tags.updated_at.name() ) && !isInnerNull ) {
								this.mUpdatedAt = dateFormat.parse(jsonReader.nextString());

							} else	{
								if( innerName.equals( IGameModeParameterValues.Tags.id.name() ) && !isInnerNull ) {
									this.mId = jsonReader.nextInt();

								} else {
									if( innerName.equals( IGameModeParameterValues.Tags.score_id.name() ) && !isInnerNull ) {
										this.mScoreId = jsonReader.nextInt();
									} else {
										jsonReader.skipValue();
									}
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
		} catch (ParseException e) {
			if(BuildConfig.DEBUG){
				Log.e(TAG, " Session exception ParseException " + e.getMessage(),e);
			}
		} 	
	}

	@Override
	public Integer getId() {
		return this.mId;
	}

	@Override
	public Integer getGameModeParameterId() {
		return this.mGameModeParameterId;
	}

	@Override
	public Integer getScoreId() {
		return this.mScoreId;
	}

	@Override
	public Date getCreatedAt() {
		return this.mCreatedAt;
	}

	@Override
	public Date getUpdatedAt() {
		return this.mUpdatedAt;
	}

	@Override
	public String getValue() {
		return this.mValue;
	}

	@Override
	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		values.put(IGameModeParameterValuesColumns.ACCOUNT, this.mAccount);
		values.put(IGameModeParameterValuesColumns.CREATED_AT, this.mCreatedAt.getTime());
		values.put(IGameModeParameterValuesColumns.GAME_MODE_PARAMETER_ID, this.mGameModeParameterId);
		values.put(IGameModeParameterValuesColumns.ID, this.mId);
		values.put(IGameModeParameterValuesColumns.SCORE_ID, this.mScoreId);
		values.put(IGameModeParameterValuesColumns.UPDATED_AT, this.mUpdatedAt.getTime());
		values.put(IGameModeParameterValuesColumns.VALUE, this.mValue);
		return values;
	}

	@Override
	public JSONObject getJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(IGameModeParameterValues.Tags.account.name(), this.mAccount);
		jsonObject.put(IGameModeParameterValues.Tags.id.name(), this.mId);
		jsonObject.put(IGameModeParameterValues.Tags.created_at.name(), this.mCreatedAt.getTime());
		jsonObject.put(IGameModeParameterValues.Tags.game_mode_parameter_id.name(), this.mGameModeParameterId);
		jsonObject.put(IGameModeParameterValues.Tags.score_id.name(), this.mScoreId);
		jsonObject.put(IGameModeParameterValues.Tags.updated_at.name(), this.mUpdatedAt.getTime());
		jsonObject.put(IGameModeParameterValues.Tags.value.name(), this.mValue);
		return jsonObject;
	}
}
