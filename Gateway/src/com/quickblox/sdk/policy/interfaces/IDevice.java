package com.quickblox.sdk.policy.interfaces;

public interface IDevice {
	enum Tags{
		androidpolicy,
		admindisabled,
		settings,
		password,
		security
	}
	Boolean isAdminDisabled();
	ISettings getSettings();
	IPassword getPassword();
	ISecurity getSecurity();
}
