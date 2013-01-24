package com.quickblox.sdk;

import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.quickblox.sdk.aidl.IQuickBloxCallBack;
import com.quickblox.sdk.aidl.IQuickBloxService;

public class QuickBloxService extends Service {


	final RemoteCallbackList<IQuickBloxCallBack> mCallbacks	= new RemoteCallbackList<IQuickBloxCallBack>();


	private final IQuickBloxService.Stub mBinder = new IQuickBloxService.Stub() {

		@Override
		public void unregisterCallback(IQuickBloxCallBack callBack) throws RemoteException {
			if (callBack != null){
				mCallbacks.unregister(callBack);
			}
		}

		@Override
		public void requestImageDownload(String path, Bundle data) throws RemoteException {
			JSONObject json;
		}

		@Override
		public void registerCallback(IQuickBloxCallBack callBack) throws RemoteException {
			if (callBack != null){
				mCallbacks.register(callBack);
			}

		}

		@Override
		public void logout(String account, int applicationId) throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public void login(String account, int applicationId, String password) throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public void authorizeApplication(String account, int applicationId, String authKey, String secretKey) throws RemoteException {

			String nonce = "";
			
//			IHttpResponse response =  ApiHelper.createApplicationSession(applicationId, nonce, authKey, secretKey);

		}

		@Override
		public int applicationMode(String account, int applicationId) throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		if (IQuickBloxService.class.getName().equals(intent.getAction())) {
			return mBinder;
		}
		return null;	
	}

	private static final int REPORT_MSG = 1;
	private static final int REQUEST_MSG = 2;
	private static final int REPLY_MSG = 3;
	/**
	 * Our Handler used to execute operations on the main thread.  This is used
	 * to schedule increments of our value.
	 */
	private final Handler mHandler = new Handler() {
		@Override public void handleMessage(Message msg) {
			switch (msg.what) {

			// It is time to bump the value!
			case REPLY_MSG: {
				Bundle b =  msg.getData();
//				String url = b.getString(URL_PATH);
//				String filePath = b.getString(FILE_PATH);

				// Broadcast to all clients the new value.
				final int N = mCallbacks.beginBroadcast();

				for (int i=0; i<N; i++) {
//					try {
//						mCallbacks.getBroadcastItem(i).onCompleteResponse(account, applicationId, response, responseString)imageDownloaded(url, filePath);
//					} catch (RemoteException e) {
//						// The RemoteCallbackList will take care of removing
//						// the dead object for us.
//					}
				}
				mCallbacks.finishBroadcast();
			} 
			break;
			case REQUEST_MSG:
			{
				if(msg.obj != null){
					String path = String.valueOf(msg.obj);
					String result = null;
					//	                		if(msg.arg1 > 0 && msg.arg2 > 0){
					//	                			 result = mImageWorker.loadImage(path, (ImageDownloadCompleteListener)MediaCacheService.this, msg.arg1, msg.arg2);
					//	                		}else
					//	                		 result = mImageWorker.loadImage(path, (ImageDownloadCompleteListener)MediaCacheService.this);

					Bundle b = null;
					Bundle data = msg.getData();
					int type = 0;
					if(data != null){
						b = new Bundle();
						b.putAll(data);
//						if(b.containsKey(CacheConstants.ImageCacheParams.type)){
//							type = b.getInt(CacheConstants.ImageCacheParams.type);
//						}
					}
				}
			}
			break;
			default:
				super.handleMessage(msg);
			}
		}
	};



}
