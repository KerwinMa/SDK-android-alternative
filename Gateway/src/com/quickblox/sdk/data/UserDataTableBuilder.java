package com.quickblox.sdk.data;

import uk.co.madmouse.core.Data.DataTableBuilder;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.quickblox.sdk.data.DataTableConstants.IDataTablesMatch;
import com.quickblox.sdk.data.interfaces.IUserDataTableBuilder;

public class UserDataTableBuilder extends DataTableBuilder implements IUserDataTableBuilder {

	private static final String TAG = "users";
	private static final String DELETED_TAG = "deletedUsers";

	public static final String CONTENT_TYPE = "vnd.android.cursor.item/" + DataTableConstants.MIME_CONTENT_URI + "/" + TAG;
	public static final String CONTENTS_TYPE = "vnd.android.cursor.dir/" + DataTableConstants.MIME_CONTENT_URI + "/" + TAG + "s";

	public static final Uri CONTENT_URI = Uri.parse("content://" + DataTableConstants.AUTHORITY + "/" + TAG);

	private static final int LIST = IDataTablesMatch.USER;
	private static final int ITEM = IDataTablesMatch.USER + 1;

	
	public UserDataTableBuilder(UriMatcher uriMatcher, Context context) {
		super(uriMatcher, context);
	}

	@Override
	public int getTableVersion() {
		return 0;
	}

	@Override
	public boolean supportsMatch(int match) {
		return (match == LIST || (match == ITEM));
	}

	@Override
	public String getType(int match) {
		switch (match) {
		case LIST:
			return CONTENTS_TYPE;

		case ITEM:
			return CONTENT_TYPE;
		}
		return null;
	}

	@Override
	public int delete(SQLiteDatabase db, int match, Uri uri, String selection, String[] selectionArgs) {
		switch (match) {
		case ITEM:
			String whereid_Text = IColumns.ID + " = "	+ uri.getPathSegments().get(1) ;
			if(!TextUtils.isEmpty(selection)){
				whereid_Text += " and " + selection;
			}

			if(Log.isLoggable(TAG , Log.INFO)){
				Log.i(TAG," delete " + getTableName() + " - " + whereid_Text);
			}
			return db.delete(getTableName(), selection, selectionArgs);
		}
		return 0;
	}

	@Override
	public Uri insert(SQLiteDatabase db, int match, Uri uri, ContentValues values) {
		long recordId = 0;

		switch (match) {
		case ITEM:
			if(!values.containsKey(IColumns.ID)){
				values.put(IColumns.ID, uri.getPathSegments().get(1));
			}

			if(Log.isLoggable(TAG , Log.INFO)){
				Log.i(TAG,"insert " + getTableName() + " ITEMS");
			}

			recordId = db.replace(getTableName(), null, values);
			break;
		}

		if (recordId > 0) {
			return ContentUris.withAppendedId(CONTENT_URI, recordId);
		}

		return null;
	}

	@Override
	public Cursor query(SQLiteDatabase db, int match, Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor resultCursor = null;

		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(getTableName());
		switch (match) {
		case ITEM : 

			String whereid_Text = IColumns.ID + " = "	+ uri.getPathSegments().get(1);
			if(!TextUtils.isEmpty(selection)){
				whereid_Text += " and " + selection;
			}

			if(Log.isLoggable(TAG , Log.INFO)){
				Log.i(TAG,"Query Application " + whereid_Text);
			}
			resultCursor = queryBuilder.query(db, projection, whereid_Text,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_URI);
			break;

		case LIST :
			if(Log.isLoggable(TAG , Log.INFO)){
				Log.i(TAG,"Query Application " + selection);
			}
			resultCursor = queryBuilder.query(db, projection, selection,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_URI);
			break;
		}

		return resultCursor;
	}

	@Override
	public int update(SQLiteDatabase db, int match, Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		switch (match) {
		case ITEM:
			String whereid_Text = IColumns.ID + " = "	+ uri.getPathSegments().get(1) ;
			if(!TextUtils.isEmpty(selection)){
				whereid_Text += " and " + selection;
			}

			if(Log.isLoggable(TAG , Log.INFO)){
				Log.i(TAG," update " + getTableName() + " - " + whereid_Text);
			}
			return db.update(getTableName(), values, whereid_Text, selectionArgs);
		}

		return 0;
	}

	@Override
	public boolean createTables(SQLiteDatabase db) {
		
		StringBuilder tableSB = new StringBuilder();
		tableSB.append("CREATE TABLE IF NOT EXISTS " + getTableName());
		tableSB.append(" ( ");
		tableSB.append(IColumns.ID + "  INTEGER PRIMARY KEY");
		tableSB.append("," + IColumns.ACCOUNT + " TEXT ");
		tableSB.append("," + IColumns.OWNER_ID + " INTEGER ");
		tableSB.append("," + IColumns.FULL_NAME + " TEXT ");
		tableSB.append("," + IColumns.EMAIL + " TEXT ");
		tableSB.append("," + IColumns.LOGIN + " TEXT ");
		tableSB.append("," + IColumns.PHONE + " TEXT ");
		tableSB.append("," + IColumns.WEBSITE + " TEXT ");
		tableSB.append("," + IColumns.CREATED_AT + " INTEGER ");
		tableSB.append("," + IColumns.UPDATED_AT + " INTEGER ");
		tableSB.append("," + IColumns.LAST_REQUEST_AT + " INTEGER ");
		tableSB.append("," + IColumns.EXTERNAL_USER_ID + " INTEGER ");
		tableSB.append("," + IColumns.FACEBOOK_ID + " INTEGER ");
		tableSB.append("," + IColumns.TWITTER_ID + " INTEGER ");
		tableSB.append("," + IColumns.BLOB_ID + " INTEGER ");
		tableSB.append("," + IColumns.USER_TAGS	+ " TEXT ");
		tableSB.append("," + IColumns.DIRTY	+ " BOOLEAN ");
		tableSB.append("," + IColumns.SYNCED_AT	+ " INTEGER ");
		tableSB.append("," + IColumns.JSON_OBJECT	+ " TEXT ");
		tableSB.append(" )");

		db.execSQL(tableSB.toString());

		if(Log.isLoggable(TAG, Log.INFO)){
			Log.i(TAG,"createTables : " + getTableName());
		}

		if(Log.isLoggable(TAG, Log.VERBOSE)){
			Log.v(TAG,"createTables : " + tableSB.toString());
		}

		return true;
	}

	@Override
	public String getTableName() {
		return TAG;
	}

	@Override
	public void addMatcherUri(UriMatcher uriMatcher) {
		uriMatcher.addURI(DataTableConstants.AUTHORITY, TAG + "/",	LIST);
		uriMatcher.addURI(DataTableConstants.AUTHORITY, TAG + "/#" , ITEM);
		
	}

	
}
