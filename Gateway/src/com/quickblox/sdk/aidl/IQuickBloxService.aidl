package com.quickblox.sdk.aidl;

import com.quickblox.sdk.aidl.IQuickBloxCallBack;

interface IQuickBloxService {
    /**
     * Often you want to allow a service to call back to its clients.
     * This shows how to do so, by registering a callback interface with
     * the service.
     */
    void registerCallback(IQuickBloxCallBack callBack);
    
    /**
     * Remove a previously registered callback interface.
     */
    void unregisterCallback(IQuickBloxCallBack callBack);
    
 	void authorizeApplication(String account,int applicationId, String authKey, String secretKey);
 	void login(String account,int applicationId , String password);
 	void logout(String account,int applicationId);
 	
 	int applicationMode(String account,int applicationId);
 	
 
    void requestImageDownload(String path, in Bundle data);
 
   
}