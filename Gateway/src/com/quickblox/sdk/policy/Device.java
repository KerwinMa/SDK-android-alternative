package com.quickblox.sdk.policy;

import java.io.IOException;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.quickblox.sdk.policy.interfaces.IDevice;
import com.quickblox.sdk.policy.interfaces.IPassword;
import com.quickblox.sdk.policy.interfaces.ISecurity;
import com.quickblox.sdk.policy.interfaces.ISettings;

public class Device implements IDevice {

	private static final String TAG = "Device";
	private Boolean mAdminDisabled = true;
	private ISettings mSettings = null;
	private IPassword mPassword = null;
	private ISecurity mSecurity = null;

	public Device(JsonReader jsonReader){
		try {
			jsonReader.setLenient(true);
			jsonReader.beginObject();
			while( jsonReader.hasNext()){
				final String innerName = jsonReader.nextName();
				final boolean isInnerNull = jsonReader.peek() == JsonToken.NULL;
				if( innerName.equals( Tags.admindisabled.name() ) && !isInnerNull ) {
					this.mAdminDisabled = jsonReader.nextBoolean();	
				} else {
					if( innerName.equals( Tags.settings.name() ) && !isInnerNull ) {
						this.mSettings = new Settings(jsonReader);	
					} else {
						if( innerName.equals( Tags.password.name() ) && !isInnerNull ) {
							this.mPassword = new Password(jsonReader);	
						} else {
							if( innerName.equals( Tags.security.name() ) && !isInnerNull ) {
								this.mSecurity = new Security(jsonReader);	
							} else {
								jsonReader.skipValue();
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
	
	@Override
	public Boolean isAdminDisabled() {
		return mAdminDisabled;
	}

	@Override
	public ISettings getSettings() {
		return mSettings;
	}

	@Override
	public IPassword getPassword() {
		return mPassword;
	}

	@Override
	public ISecurity getSecurity() {
		return mSecurity;
	}

}
