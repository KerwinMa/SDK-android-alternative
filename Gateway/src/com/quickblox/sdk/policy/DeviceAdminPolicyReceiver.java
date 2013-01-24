package com.quickblox.sdk.policy;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.quickblox.gateway.R;

public class DeviceAdminPolicyReceiver extends DeviceAdminReceiver {
	void showToast(Context context, String msg) {
		String status = context.getString(R.string.admin_receiver_status, msg);
		Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onEnabled(Context context, Intent intent) {
		//Process Policy
		showToast(context, context.getString(R.string.admin_receiver_status_enabled));
	}

	@Override
	public CharSequence onDisableRequested(Context context, Intent intent) {
		return context.getString(R.string.admin_receiver_status_disable_warning);
	}

	@Override
	public void onDisabled(Context context, Intent intent) {
		showToast(context, context.getString(R.string.admin_receiver_status_disabled));
	}

	@Override
	public void onPasswordChanged(Context context, Intent intent) {
		showToast(context, context.getString(R.string.admin_receiver_status_pw_changed));
	}
	
	@Override
	public void onPasswordExpiring(Context context, Intent intent) {
		super.onPasswordExpiring(context, intent);
	}
	
	@Override
	public void onPasswordSucceeded(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onPasswordSucceeded(context, intent);
	}
	
	@Override
	public void onPasswordFailed(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onPasswordFailed(context, intent);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
	}
}
