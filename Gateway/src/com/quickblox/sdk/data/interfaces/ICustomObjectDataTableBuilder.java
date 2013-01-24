package com.quickblox.sdk.data.interfaces;

import uk.co.madmouse.core.Data.Interfaces.IDataTableBuilder;

public interface ICustomObjectDataTableBuilder extends IDataTableBuilder {
	public interface IColumns{
		public static String ID = "_id";
		public static String ACCOUNT = "account";
		public static String APPLICATION_ID = "applicationId";
		public static String CLASSNAME = "className";
		public static String PARENT_ID = "owner_id";
		public static String USER_ID = "user_id";
		public static String USER_TAGS = "user_tags";
		public static String DIRTY = "dirty";
		public static String SYNCED_AT = "synced_at";
		public static String JSON_OBJECT = "jsonObject";		
	}
}
