package com.quickblox.sdk.data.interfaces;

import uk.co.madmouse.core.Data.Interfaces.IBaseColumns;
import uk.co.madmouse.core.Data.Interfaces.IDataTableBuilder;

public interface IApplicationTableBuilder extends IDataTableBuilder {
	public interface IColumns extends IBaseColumns{
		public static String APP_ID = "_id";
		public static String ID = "qbId";
		public static String ACCOUNT = "account";
		public static String AUTH_KEY = "auth_token";
		public static String AUTH_SECRET = "auth_secret";
		public static String CREATED_AT = "created_at";
		public static String DEVICE_ID = "device_id";
		public static String NONCE = "nonce";
		public static String TOKEN = "token";
		public static String TIMESTAMP = "ts";
		public static String UPDATED_AT = "updated_at";
		public static String USER_ID = "user_id";
		public static String JSON_OBJECT = "jsonObject";
	}
	
	
	
}
