package com.quickblox.sdk;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.quickblox.gateway.BuildConfig;
import com.quickblox.sdk.data.interfaces.IRatingsDataTableBuilder.IScoreColumns;
import com.quickblox.sdk.interfaces.IGameModeParameterValues;
import com.quickblox.sdk.interfaces.IScore;

class Score implements IScore {

	private static final String TAG = "Score";
	private String mAccount;

	
	public Score(String account, Cursor cursor){
		this.mAccount = account;
	}

	public Score(String account,JsonReader jsonReader){
		this.mAccount = account;
		try {
			jsonReader.setLenient(true);
			jsonReader.beginObject();

			while( jsonReader.hasNext()){
				final String innerName = jsonReader.nextName();
				final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;

				if( innerName.equals( IScore.Tags.game_mode_id.name() ) && !isInnerNull ) {
					this.mModeId = jsonReader.nextInt();	
				} else {
					if( innerName.equals( IScore.Tags.id.name() ) && !isInnerNull ) {
						this.mId = jsonReader.nextInt();
					} else {
						if( innerName.equals( IScore.Tags.value.name() ) && !isInnerNull ) {
							this.mValue = jsonReader.nextInt();
						} else {
							if( innerName.equals( IScore.Tags.created_at.name() ) && !isInnerNull ) {
								this.mCreatedAt = ApiHelper.mQBDateFormater.parse(jsonReader.nextString());
							} else 
								if( innerName.equals( IScore.Tags.game_mode_parameter_values.name() ) && !isInnerNull ) {
									jsonReader.beginArray();
									while( jsonReader.hasNext()){
										mGameModeParameterValues.add(new GameModeParameterValues(this.mAccount, jsonReader));
									}
									jsonReader.endArray();
								} else					{ 
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
		} catch (ParseException e) {
			if(BuildConfig.DEBUG){
				Log.e(TAG, " Session exception ParseException " + e.getMessage(),e);
			}
		} 	
	}

	private Integer mId;
	private Integer mModeId;
	private Integer mUserId;
	private Integer mValue;
	private List<IGameModeParameterValues> mGameModeParameterValues = new ArrayList<IGameModeParameterValues>();
	private Date mCreatedAt;

	@Override
	public Integer getId() {
		return this.mId;
	}

	@Override
	public Integer getModeId() {
		return this.mModeId;
	}

	@Override
	public Integer getUserId() {
		return this.mUserId;
	}

	@Override
	public Integer getValue() {
		return this.mValue;
	}

	@Override
	public List<IGameModeParameterValues> getGameModeParameters() {

		return this.mGameModeParameterValues ;
	}

	/*
		public static final String ID = "_id";
		public static final String ACCOUNT = "account";
		public static final String APPLICATION_ID = "applicationId";
		public static final String GAME_MODE_ID = "gameModeId";
		public static final String USER_ID = "userId";
		public static final String CREATED_AT = "createdAt";
		public static final String VALUE = "value";
		public static final String DIRTY = "dirty";
		public static final String SYNCED_AT = "synced_at";
		public static final String JSON_OBJECT = "jsonObject";
	 */
	
	@Override
	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		values.put(IScoreColumns.ID, getId());
		values.put(IScoreColumns.ACCOUNT, this.mAccount);
		values.put(IScoreColumns.CREATED_AT, getCreatedAt().getTime());
		values.put(IScoreColumns.GAME_MODE_ID, this.getModeId());
		values.put(IScoreColumns.USER_ID, getUserId());
		values.put(IScoreColumns.VALUE, getValue());
		return values;
	}

	@Override
	public JSONObject getJSONObject() throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getCreatedAt() {
		return this.mCreatedAt;
	}

}
