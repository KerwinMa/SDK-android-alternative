package com.quickblox.sdk.policy;

import java.io.IOException;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.quickblox.sdk.policy.interfaces.IPassword;

class Password implements IPassword {

	private static final String TAG = "Password";

	public Password(JsonReader jsonReader){
		try {
			jsonReader.setLenient(true);
			jsonReader.beginObject();
			while( jsonReader.hasNext()){
				final String innerName = jsonReader.nextName();
				final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
				if( innerName.equals( Tags.maximumfailedpasswordsforwipe.name() ) && !isInnerNull ) {
					this.mMaxFailedPasswordsForWipe = jsonReader.nextInt();	
				} else {
					if( innerName.equals( Tags.maximumtimetolock.name() ) && !isInnerNull ) {
						this.mMaxTimeToLock = jsonReader.nextInt();	
					} else {
						if( innerName.equals( Tags.expirationtimeout.name() ) && !isInnerNull ) {
							this.mExpirationTimeout = jsonReader.nextInt();	
						} else {
							if( innerName.equals( Tags.historylength.name() ) && !isInnerNull ) {
								this.mHistoryLength = jsonReader.nextInt();	
							} else {
								if( innerName.equals( Tags.minimumlength.name() ) && !isInnerNull ) {
									this.mMinLength = jsonReader.nextInt();	
								} else {
									if( innerName.equals( Tags.minimumletters.name() ) && !isInnerNull ) {
										this.mMinLetters = jsonReader.nextInt();	
									} else {
										if( innerName.equals( Tags.minimumlowercase.name() ) && !isInnerNull ) {
											this.mMinLowerCaseLetters = jsonReader.nextInt();	
										} else {
											if( innerName.equals( Tags.minimumnonletter.name() ) && !isInnerNull ) {
												this.mMinNonLetters = jsonReader.nextInt();	
											} else {
												if( innerName.equals( Tags.minimumnumeric.name() ) && !isInnerNull ) {
													this.mMinNumeric = jsonReader.nextInt();	
												} else {
													if( innerName.equals( Tags.minimumsymbols.name() ) && !isInnerNull ) {
														this.mMinSymbols = jsonReader.nextInt();	
													} else {
														if( innerName.equals( Tags.minimumuppercase.name() ) && !isInnerNull ) {
															this.mMinUpperCaseLetters = jsonReader.nextInt();	
														} else {
															if( innerName.equals( Tags.quality.name() ) && !isInnerNull ) {
																this.mQuality = jsonReader.nextInt();	
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
			jsonReader.endObject();
		} catch (IOException e) {
			if(Log.isLoggable(TAG, Log.ERROR)){
				Log.e(TAG,"IOException : " + e.getMessage(), e);
			}
		}	
		
	}
	
	
	private Integer mMaxFailedPasswordsForWipe = 0;
	private Integer mMaxTimeToLock = 0;
	private Integer mExpirationTimeout = 0;
	private Integer mHistoryLength = 0;
	private Integer mMinLength = 0;
	private Integer mMinLetters = 0;
	private Integer mMinLowerCaseLetters = 0;
	private Integer mMinNonLetters = 0;
	private Integer mMinNumeric = 0;
	private Integer mMinSymbols = 0;
	private Integer mMinUpperCaseLetters = 0;
	private Integer mQuality = 0;

	@Override
	public Integer getMaxFailedPasswordsForWipe() {
		return this.mMaxFailedPasswordsForWipe;
	}

	@Override
	public Integer getMaxTimeToLock() {
		return mMaxTimeToLock;
	}

	@Override
	public Integer getExpirationTimeout() {
		return mExpirationTimeout;
	}

	@Override
	public Integer getHistoryLength() {
		return mHistoryLength;
	}

	@Override
	public Integer getMinLength() {
		return mMinLength;
	}

	@Override
	public Integer getMinLetters() {
		return mMinLetters;
	}

	@Override
	public Integer getMinLowerCaseLetters() {
		return mMinLowerCaseLetters;
	}

	@Override
	public Integer getMinNonLetters() {
		return mMinNonLetters;
	}

	@Override
	public Integer getMinNumeric() {
		return mMinNumeric;
	}

	@Override
	public Integer getMinSymbols() {
		return mMinSymbols;
	}

	@Override
	public Integer getMinUpperCaseLetters() {
		return mMinUpperCaseLetters;
	}

	@Override
	public Integer getQuality() {
		return mQuality;
	}
}
