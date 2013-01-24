package com.quickblox.sdk.policy;

import java.io.IOException;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.quickblox.sdk.policy.interfaces.ISecurity;

class Security implements ISecurity {

	private static final String TAG = "Security";
	private Boolean mWipeDisabled;
	private Boolean mStorageEncrypted;

	public Security(JsonReader jsonReader){
		try {
			jsonReader.setLenient(true);
			jsonReader.beginObject();
			while( jsonReader.hasNext()){
				final String innerName = jsonReader.nextName();
				final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
				if( innerName.equals( Tags.allowwipedisabled.name() ) && !isInnerNull ) {
					this.mWipeDisabled = jsonReader.nextBoolean();	
				} else {
					if( innerName.equals( Tags.storageencryption.name() ) && !isInnerNull ) {
						this.mStorageEncrypted = jsonReader.nextBoolean();	
					} else {
						jsonReader.skipValue();
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

	@Override
	public Boolean isWipeDisabled() {
		return this.mWipeDisabled;
	}

	@Override
	public Boolean isStorageEncrypted() {
		return mStorageEncrypted;
	}
}
