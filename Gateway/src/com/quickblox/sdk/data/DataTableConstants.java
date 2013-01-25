package com.quickblox.sdk.data;

interface DataTableConstants {
	public static final String AUTHORITY = "com.quickblox.datastore";
	public static final String MIME_CONTENT_URI = "vnd.quickblox.datastore";
	
	public static final String DATABASE_NAME = "quickblox.db";
	public static final Integer DATABASE_VERSION = 1;
	

	public interface IDataTablesMatch {
		final static int MULTIPLIER = 1000;
		final static int APPLICATION = 1 * MULTIPLIER;
		final static int USER = 2 * MULTIPLIER;
		final static int CUSTOMOBJECT = 3 * MULTIPLIER;
		final static int GAMEMODE = 4 * MULTIPLIER;
		final static int RATING = 5 * MULTIPLIER;
		
	}
}
