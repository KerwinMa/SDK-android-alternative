package com.quickblox.sdk;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import android.content.ContentValues;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.quickblox.gateway.BuildConfig;
import com.quickblox.sdk.data.interfaces.IApplicationTableBuilder.IColumns;
import com.quickblox.sdk.interfaces.ISession;

class Session implements ISession {

	private static final String TAG = null;
	private String mAccount;
	private String mId;
	private String mParentId;
	private Date mDateCreated;
	private Date mDateUpdated;
	private Date mLastRequest;
	private Integer mApplicationId;
	private String mDeviceId;
	private String mUserId;
	private Date mTimeStamp;
	private String mToken;
	private Integer mNonce;

	public Session(String accountName,JsonReader jsonReader){
		this.mAccount = accountName;
		try {
			jsonReader.setLenient(true);
			jsonReader.beginObject();

			while( jsonReader.hasNext()){
				final String name = jsonReader.nextName();

				if(BuildConfig.DEBUG){
					Log.d(TAG,"Session Parser :" + name);
				}

				final boolean isNull = jsonReader.peek() == JsonToken.NULL;
				if( name.equals( ISession.Tags.session.name()) &&  !isNull ) {
					jsonReader.beginObject();
					while( jsonReader.hasNext() ) {
						final String innerName = jsonReader.nextName();
						final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
						if( innerName.equals( ISession.Tags.application_id.name() ) && !isInnerNull ) {
							this.mApplicationId = jsonReader.nextInt();	

						} else {
							if( innerName.equals( ISession.Tags.created_at.name() ) && !isInnerNull ) {
								this.mDateCreated = ApiHelper.mQBDateFormater.parse(jsonReader.nextString());
							} else {
								if( innerName.equals( ISession.Tags.device_id.name() ) && !isInnerNull ) {
									this.mDeviceId = jsonReader.nextString();
								} else {
									if( innerName.equals( ISession.Tags.id.name() ) && !isInnerNull ) {
										this.mId = jsonReader.nextString();
									} else {
										if( innerName.equals( ISession.Tags.nonce.name() ) && !isInnerNull ) {
											this.mNonce = jsonReader.nextInt();
										} else {
											if( innerName.equals( ISession.Tags.token.name() ) && !isInnerNull ) {
												this.mToken = jsonReader.nextString();
											} else {
												if( innerName.equals( ISession.Tags.ts.name() ) && !isInnerNull ) {
													Long timeStamp = jsonReader.nextLong() * 1000;
													this.mTimeStamp = new Date(timeStamp);
												} else {
													if( innerName.equals( ISession.Tags.updated_at.name() ) && !isInnerNull ) {
														this.mDateUpdated = ApiHelper.mQBDateFormater.parse(jsonReader.nextString());
													} else {
														if( innerName.equals( ISession.Tags.user_id.name() ) && !isInnerNull ) {
															this.mDateCreated = ApiHelper.mQBDateFormater.parse(jsonReader.nextString());
														} else {
															jsonReader.skipValue();
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					jsonReader.endObject();
				}
				else { 
					jsonReader.skipValue();
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
	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		
		values.put(IColumns._ID, getApplicationId());
		values.put(IColumns.ACCOUNT, getAccount());
		values.put(IColumns.CREATED_AT,getCreated().getTime());
		values.put(IColumns.DEVICE_ID, getDeviceId());
		values.put(IColumns.ID, getId());
		values.put(IColumns.NONCE, getNonce());
		values.put(IColumns.TIMESTAMP, getTimeStamp().getTime());
		values.put(IColumns.TOKEN, getToken());
		values.put(IColumns.UPDATED_AT, getUpdated().getTime());
		values.put(IColumns.USER_ID, getUserId());
		
		return values;
	}

	@Override
	public String getId() {
		return this.mId;
	}

	@Override
	public String getParentId() {
		return this.mParentId;
	}

	@Override
	public Date getCreated() {
		return this.mDateCreated;
	}

	@Override
	public Date getUpdated() {
		return this.mDateUpdated;
	}

	@Override
	public Date getLastRequested() {
		return this.mLastRequest;
	}

	@Override
	public Integer getApplicationId() {
		return this.mApplicationId;
	}

	@Override
	public String getDeviceId() {
		return this.mDeviceId;
	}

	@Override
	public String getToken() {
		return this.mToken;
	}

	@Override
	public Date getTimeStamp() {
		return mTimeStamp;
	}

	@Override
	public String getUserId() {
		return this.mUserId;
	}


	@Override
	public Integer getNonce() {
		return this.mNonce;
	}

	@Override
	public String getAccount() {
		return mAccount;
	}

}
