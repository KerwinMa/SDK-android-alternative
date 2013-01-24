package com.quickblox.sdk.interfaces;

public interface ISessionCallBack {
	void onSessionCreated(String account, Integer applicationId, ISession sessionObject);
	void onSessionDeleted(String account, Integer applicationId);
}
