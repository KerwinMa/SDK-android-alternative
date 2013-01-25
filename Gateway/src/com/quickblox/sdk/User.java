package com.quickblox.sdk;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.quickblox.gateway.BuildConfig;
import com.quickblox.sdk.data.interfaces.IUserDataTableBuilder.IColumns;
import com.quickblox.sdk.interfaces.IUser;

class User implements IUser {

	private static final String TAG = "User";

	private Integer mId;
	private String mAccount;
	private Integer mOwnerId;
	private String mTagList;
	private String mWebsite;
	private String mPhone;
	private Integer mTwitterId;
	private Integer mFaceBookId;
	private Integer mExternalUserId;
	private Integer mBlobId;
	private String mUserLogin;
	private String mEmailAddress;
	private String mParentId;
	private Date mCreatedDate;
	private Date mUpdateDate;
	private Date mLastRequested;
	private String mFullName;

	public User(Cursor cursor){
		if(cursor != null){
			if(cursor.moveToFirst()){
				this.mId = cursor.getInt(cursor.getColumnIndexOrThrow(IColumns.ID));
				this.mAccount = cursor.getString(cursor.getColumnIndexOrThrow(IColumns.ACCOUNT));
				this.mOwnerId = cursor.getInt(cursor.getColumnIndexOrThrow(IColumns.OWNER_ID));
				this.mFullName = cursor.getString(cursor.getColumnIndexOrThrow(IColumns.FULL_NAME));
				this.mEmailAddress = cursor.getString(cursor.getColumnIndexOrThrow(IColumns.EMAIL));
				this.mUserLogin = cursor.getString(cursor.getColumnIndexOrThrow(IColumns.LOGIN));
				this.mPhone = cursor.getString(cursor.getColumnIndexOrThrow(IColumns.PHONE));
				this.mWebsite = cursor.getString(cursor.getColumnIndexOrThrow(IColumns.WEBSITE));
				this.mCreatedDate = new Date(cursor.getLong(cursor.getColumnIndexOrThrow(IColumns.CREATED_AT)));
				this.mUpdateDate = new Date(cursor.getLong(cursor.getColumnIndexOrThrow(IColumns.UPDATED_AT)));
				this.mLastRequested = new Date(cursor.getLong(cursor.getColumnIndexOrThrow(IColumns.LAST_REQUEST_AT)));
				this.mExternalUserId = cursor.getInt(cursor.getColumnIndexOrThrow(IColumns.EXTERNAL_USER_ID));
				this.mFaceBookId = cursor.getInt(cursor.getColumnIndexOrThrow(IColumns.FACEBOOK_ID));
				this.mTwitterId = cursor.getInt(cursor.getColumnIndexOrThrow(IColumns.TWITTER_ID));
				this.mBlobId = cursor.getInt(cursor.getColumnIndexOrThrow(IColumns.BLOB_ID));
				this.mTagList = cursor.getString(cursor.getColumnIndexOrThrow(IColumns.USER_TAGS));
			}
		}
	}
	
	public User(Integer applicationId, String accountName,JsonReader jsonReader){
		this.mAccount = accountName;
		if(BuildConfig.DEBUG){
			Log.d(TAG,"User Parser : " + accountName + " - " + jsonReader);
		}
		
		try {
			jsonReader.setLenient(true);
			jsonReader.beginObject();

			while( jsonReader.hasNext()){
				final String name = jsonReader.nextName();

				if(BuildConfig.DEBUG){
					Log.d(TAG,"User Parser :" + name);
				}

				final boolean isNull = jsonReader.peek() == JsonToken.NULL;
				if( name.equals( IUser.Tags.user.name()) &&  !isNull ) {
					jsonReader.beginObject();
					while( jsonReader.hasNext() ) {
						final String innerName = jsonReader.nextName();
						final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
						if( innerName.equals( IUser.Tags.id.name() ) && !isInnerNull ) {
							this.mId = jsonReader.nextInt();	
						} else {
							if( innerName.equals( IUser.Tags.created_at.name() ) && !isInnerNull ) {
								this.mCreatedDate = ApiHelper.mQBDateFormater.parse(jsonReader.nextString());
							} else {
								if( innerName.equals( IUser.Tags.blob_id.name() ) && !isInnerNull ) {
									this.mBlobId = jsonReader.nextInt();
								} else {
									if( innerName.equals( IUser.Tags.email.name() ) && !isInnerNull ) {
										this.mEmailAddress = jsonReader.nextString();
									} else {
										if( innerName.equals( IUser.Tags.external_user_id.name() ) && !isInnerNull ) {
											this.mExternalUserId = jsonReader.nextInt();
										} else {
											if( innerName.equals( IUser.Tags.facebook_id.name() ) && !isInnerNull ) {
												this.mFaceBookId = jsonReader.nextInt();
											} else {
												if( innerName.equals( IUser.Tags.full_name.name() ) && !isInnerNull ) {
													this.mFullName = jsonReader.nextString();
												} else {
													if( innerName.equals( IUser.Tags.last_request_at.name() ) && !isInnerNull ) {
														this.mLastRequested = ApiHelper.mQBDateFormater.parse(jsonReader.nextString());
													} else {
														if( innerName.equals( IUser.Tags.login.name() ) && !isInnerNull ) {
															this.mUserLogin = jsonReader.nextString();
														} else {
															if( innerName.equals( IUser.Tags.owner_id.name() ) && !isInnerNull ) {
																this.mOwnerId = jsonReader.nextInt();
															} else {
																if( innerName.equals( IUser.Tags.phone.name() ) && !isInnerNull ) {
																	this.mPhone = jsonReader.nextString();
																} else {
																	if( innerName.equals( IUser.Tags.twitter_id.name() ) && !isInnerNull ) {
																		this.mTwitterId = jsonReader.nextInt();
																	} else {
																		if( innerName.equals( IUser.Tags.updated_at.name() ) && !isInnerNull ) {
																			this.mUpdateDate = ApiHelper.mQBDateFormater.parse(jsonReader.nextString());
																		} else {
																			if( innerName.equals( IUser.Tags.user_tags.name() ) && !isInnerNull ) {
																				this.mTagList = jsonReader.nextString();
																			} else {
																				if( innerName.equals( IUser.Tags.website.name() ) && !isInnerNull ) {
																					this.mWebsite = jsonReader.nextString();
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
	public String getFullName() {
		return mFullName;
	}

	@Override
	public String getEmailAddress() {
		return mEmailAddress;
	}

	@Override
	public String getUserLogin() {
		return mUserLogin;
	}

	@Override
	public Integer getBlobId() {
		return mBlobId;
	}

	@Override
	public Integer getExternalUserId() {
		return mExternalUserId;
	}

	@Override
	public Integer getFaceBookId() {
		return mFaceBookId;
	}

	@Override
	public Integer getTwitterId() {
		return mTwitterId;
	}

	@Override
	public String getPhone() {
		return mPhone;
	}

	@Override
	public String getWebSite() {
		return mWebsite;
	}

	@Override
	public String getTagList() {
		return this.mTagList;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		values.put(IColumns.ID, getId());
		values.put(IColumns.ACCOUNT, getAccount());
		values.put(IColumns.OWNER_ID, getOwnerId());
		values.put(IColumns.FULL_NAME, getFullName());
		values.put(IColumns.EMAIL, getEmailAddress());
		values.put(IColumns.LOGIN, getUserLogin());
		values.put(IColumns.PHONE, getPhone());
		values.put(IColumns.WEBSITE, getWebSite());
		values.put(IColumns.CREATED_AT, getCreated().getTime());
		values.put(IColumns.UPDATED_AT, getUpdated().getTime());
		if (getLastRequested() != null){
			values.put(IColumns.LAST_REQUEST_AT, getLastRequested().getTime());
		}
		values.put(IColumns.EXTERNAL_USER_ID, getExternalUserId());
		values.put(IColumns.FACEBOOK_ID, getFaceBookId());
		values.put(IColumns.TWITTER_ID, getTwitterId());
		values.put(IColumns.BLOB_ID, getBlobId());
		values.put(IColumns.USER_TAGS, getTagList());

		return values;
	}

	@Override
	public Integer getId() {
		return this.mId;
	}

	@Override
	public String getAccount() {
		return this.mAccount;
	}

	@Override
	public Integer getOwnerId() {
		return this.mOwnerId;
	}

	@Override
	public String getParentId() {
		return mParentId;
	}

	@Override
	public Date getCreated() {
		return mCreatedDate;
	}

	@Override
	public Date getUpdated() {
		return mUpdateDate;
	}

	@Override
	public Date getLastRequested() {
		return mLastRequested;
	}

	
	@Override
	public JSONObject getJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		
		JSONObject jsonUser = new JSONObject();
		jsonUser.put(IUser.Tags.id.name(), this.mId);
		jsonUser.put(IUser.Tags.owner_id.name(), this.mOwnerId);
		jsonUser.put(IUser.Tags.full_name.name(), this.mFullName);
		jsonUser.put(IUser.Tags.email.name(), this.mEmailAddress);
		jsonUser.put(IUser.Tags.login.name(), this.mUserLogin);
		jsonUser.put(IUser.Tags.phone.name(), this.mPhone);
		jsonUser.put(IUser.Tags.website.name(), this.mWebsite);
		jsonUser.put(IUser.Tags.created_at.name(), this.mCreatedDate);
		jsonUser.put(IUser.Tags.updated_at.name(), this.mUpdateDate);
		jsonUser.put(IUser.Tags.last_request_at.name(), this.mLastRequested);
		jsonUser.put(IUser.Tags.external_user_id.name(), this.mExternalUserId);
		jsonUser.put(IUser.Tags.facebook_id.name(), this.mFaceBookId);
		jsonUser.put(IUser.Tags.twitter_id.name(), this.mTwitterId);
		jsonUser.put(IUser.Tags.blob_id.name(), this.mBlobId);
		jsonUser.put(IUser.Tags.user_tags.name(), this.mTagList);
		
		jsonObject.put(IUser.Tags.user.name(), jsonUser);
		
		return jsonObject;
	}

}
