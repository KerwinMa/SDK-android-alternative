package com.quickblox.sdk.data.interfaces;

import uk.co.madmouse.core.Data.Interfaces.IDataTableBuilder;

public interface IUserDataTableBuilder extends IDataTableBuilder {
	public interface IColumns{
		public static String ID = "_id";
		public static String ACCOUNT = "account";
		public static String APPLICATION_ID = "applicationId";
		public static String OWNER_ID = "owner_id";
		public static String FULL_NAME = "full_name";
		public static String EMAIL = "email";
		public static String LOGIN = "login";
		public static String PHONE = "phone";
		public static String WEBSITE = "website";
		public static String CREATED_AT = "created_at";
		public static String UPDATED_AT = "updated_at";
		public static String LAST_REQUEST_AT = "last_request_at";
		public static String EXTERNAL_USER_ID = "external_user_id";
		public static String FACEBOOK_ID = "facebook_id";
		public static String TWITTER_ID = "twitter_id";
		public static String BLOB_ID = "blob_id";
		public static String USER_TAGS = "user_tags";
		public static String DIRTY = "dirty";
		public static String SYNCED_AT = "synced_at";
		public static String JSON_OBJECT = "jsonObject";		
	}
}
