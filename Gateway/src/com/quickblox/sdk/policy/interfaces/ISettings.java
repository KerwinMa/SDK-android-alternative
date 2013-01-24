package com.quickblox.sdk.policy.interfaces;

public interface ISettings {
	enum Tags{
		settings,
		cameradisabled
	}
	
	Boolean isCameraDisabled();
}
