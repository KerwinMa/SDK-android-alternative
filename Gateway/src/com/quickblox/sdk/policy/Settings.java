package com.quickblox.sdk.policy;

import java.io.IOException;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.quickblox.sdk.policy.interfaces.ISettings;

class Settings implements ISettings {

	private static final String TAG = "Settings";

	public Settings(JsonReader jsonReader){
		try {
			jsonReader.setLenient(true);
			jsonReader.beginObject();
			while( jsonReader.hasNext()){
				final String innerName = jsonReader.nextName();
				final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
				if( innerName.equals( Tags.cameradisabled.name() ) && !isInnerNull ) {
					this.mCameraDisabled = jsonReader.nextBoolean();	
				} else {
					jsonReader.skipValue();
				}	
			}
			jsonReader.endObject();
		} catch (IOException e) {
			if(Log.isLoggable(TAG, Log.ERROR)){
				Log.e(TAG,"IOException : " + e.getMessage(), e);
			}
		}	
	}

	private Boolean mCameraDisabled = false;

	@Override
	public Boolean isCameraDisabled() {
		return this.mCameraDisabled;
	}

}
