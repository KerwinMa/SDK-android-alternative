package com.quickblox.sdk.policy;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.quickblox.gateway.BuildConfig;
import com.quickblox.sdk.policy.interfaces.IDevice;

class PolicyManager {
	private static final String TAG = "PolicyManager";
	private static final int REQUEST_CODE_ENABLE_ADMIN = 1;
	
	private ComponentName mDeviceAdmin;
	private DevicePolicyManager mDevicePolicyManager;
	private Context mContext;
	private IDevice mDevice;
	
	public PolicyManager(Context context, Class<?> receiverClass){
		this.mContext = context;
		this.mDeviceAdmin  = new ComponentName(context, receiverClass);
		this.mDevicePolicyManager = (DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
	}
	
	public Boolean isAdminActive(){
		if((mDevicePolicyManager != null) && (mDeviceAdmin != null)){
			return mDevicePolicyManager.isAdminActive(mDeviceAdmin);
		}
		return false;
	}

	public void disableActiveAdmin(){
		if(BuildConfig.DEBUG){
			Log.d(TAG,"DisableActiveAdmin adding MDM");
		}
		
		if(mDeviceAdmin != null && mDevicePolicyManager != null){
			this.mDevicePolicyManager.removeActiveAdmin(mDeviceAdmin);
		}
	}
	
	public void enableActiveAdmin(Activity activity, String explanation){
		if(BuildConfig.DEBUG){
			Log.d(TAG,"EnableActiveAdmin adding MDM");
		}
		
		if(mDeviceAdmin != null && mDevicePolicyManager != null){
	        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdmin);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, explanation);
            activity.startActivityForResult(intent, REQUEST_CODE_ENABLE_ADMIN);
		}
	}
	
	public void applyDevicePolicy(Activity activity, IDevice devicePolicy, String explanation){
		if(BuildConfig.DEBUG){
			Log.d(TAG,"Applying Device Policy");
		}
		mDevice = devicePolicy;
		if(mDevice != null && mContext != null &&  mDeviceAdmin != null && mDevicePolicyManager != null){
			if(devicePolicy.isAdminDisabled()){
				disableActiveAdmin();
			} else {
				if(!isAdminActive()){
					enableActiveAdmin(activity, explanation);
				} else {
					processEnabledPolicy(mDevice);
				}
			}
		}
	}
	
	private void processEnabledPolicy(IDevice devicePolicy){
		if(BuildConfig.DEBUG){
			Log.d(TAG,"Processing Enabled Policy");
		}
		
		if(mDevice != null &&  mDeviceAdmin != null && mDevicePolicyManager != null){
			if(mDevice.getPassword() != null){
				mDevicePolicyManager.setMaximumFailedPasswordsForWipe(mDeviceAdmin, mDevice.getPassword().getMaxFailedPasswordsForWipe());
				mDevicePolicyManager.setMaximumTimeToLock(mDeviceAdmin, mDevice.getPassword().getMaxTimeToLock());
				mDevicePolicyManager.setPasswordExpirationTimeout(mDeviceAdmin, mDevice.getPassword().getMaxTimeToLock());
				mDevicePolicyManager.setPasswordHistoryLength(mDeviceAdmin, mDevice.getPassword().getHistoryLength());
				mDevicePolicyManager.setPasswordMinimumLength(mDeviceAdmin, mDevice.getPassword().getMinLength());
				mDevicePolicyManager.setPasswordMinimumLetters(mDeviceAdmin, mDevice.getPassword().getMinLetters());
				mDevicePolicyManager.setPasswordMinimumLowerCase(mDeviceAdmin, mDevice.getPassword().getMinLowerCaseLetters());
				mDevicePolicyManager.setPasswordMinimumNonLetter(mDeviceAdmin, mDevice.getPassword().getMinNonLetters());
				mDevicePolicyManager.setPasswordMinimumNumeric(mDeviceAdmin, mDevice.getPassword().getMinNumeric());
				mDevicePolicyManager.setPasswordMinimumSymbols(mDeviceAdmin, mDevice.getPassword().getMinSymbols());
				mDevicePolicyManager.setPasswordMinimumUpperCase(mDeviceAdmin, mDevice.getPassword().getMinUpperCaseLetters());
				mDevicePolicyManager.setPasswordQuality(mDeviceAdmin, mDevice.getPassword().getQuality());
			};
			
			if(mDevice.getSecurity() != null){
				mDevicePolicyManager.setStorageEncryption(mDeviceAdmin, mDevice.getSecurity().isStorageEncrypted());
			}
			
			if(mDevice.getSettings() != null){
				mDevicePolicyManager.setCameraDisabled(mDeviceAdmin, mDevice.getSecurity().isStorageEncrypted());
			}
		}
	}
}
