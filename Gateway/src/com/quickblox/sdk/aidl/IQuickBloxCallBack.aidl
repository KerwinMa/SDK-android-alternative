package com.quickblox.sdk.aidl;

oneway interface IQuickBloxCallBack {
	void onProgressResponse(String account, int applicationId, int response, String responseString);
	void onCompleteResponse(String account, int applicationId, int response, String responseString);
}