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
import com.quickblox.sdk.data.interfaces.IRatingsDataTableBuilder;

public class RatingDataTableBuilder extends DataTableBuilder implements IRatingsDataTableBuilder {

	private static final String GAMEMODE_TAG = "gameMode";
	private static final String GAMEMODE_DELETED_TAG = "deletedGameMode";
	private static final String SCORE_TAG = "scores";
	private static final String SCORE_DELETED_TAG = "deletedScores";

	private static final String GAMEMODE_PARAMS_TAG = "gameModeParams";
	private static final String GAMEMODE_PARAMS_DELETED_TAG = "deletedGameModeParams";

	public static final String CONTENT_GAMEMODE_TYPE = "vnd.android.cursor.item/" + DataTableConstants.MIME_CONTENT_URI + "/" + GAMEMODE_TAG;
	public static final String CONTENTS_GAMEMODE_TYPE = "vnd.android.cursor.dir/" + DataTableConstants.MIME_CONTENT_URI + "/" + GAMEMODE_TAG + "s";
	public static final Uri CONTENT_GAMEMODE_URI = Uri.parse("content://" + DataTableConstants.AUTHORITY + "/" + GAMEMODE_TAG);

	public static final String CONTENT_DELETED_GAMEMODE_TYPE = "vnd.android.cursor.item/" + DataTableConstants.MIME_CONTENT_URI + "/" + GAMEMODE_DELETED_TAG;
	public static final String CONTENTS_DELETED_GAMEMODE_TYPE = "vnd.android.cursor.dir/" + DataTableConstants.MIME_CONTENT_URI + "/" + GAMEMODE_DELETED_TAG + "s";
	public static final Uri CONTENT_DELETED_GAMEMODE_URI = Uri.parse("content://" + DataTableConstants.AUTHORITY + "/" + GAMEMODE_DELETED_TAG);


	public static final String CONTENT_SCORE_TYPE = "vnd.android.cursor.item/" + DataTableConstants.MIME_CONTENT_URI + "/" + SCORE_TAG;
	public static final String CONTENTS_SCORE_TYPE = "vnd.android.cursor.dir/" + DataTableConstants.MIME_CONTENT_URI + "/" + SCORE_TAG + "s";
	public static final Uri CONTENT_SCORE_URI = Uri.parse("content://" + DataTableConstants.AUTHORITY + "/" + SCORE_TAG);

	public static final String CONTENT_DELETED_SCORE_TYPE = "vnd.android.cursor.item/" + DataTableConstants.MIME_CONTENT_URI + "/" + SCORE_DELETED_TAG;
	public static final String CONTENTS_DELETED_SCORE_TYPE = "vnd.android.cursor.dir/" + DataTableConstants.MIME_CONTENT_URI + "/" + SCORE_DELETED_TAG + "s";
	public static final Uri CONTENT_DELETED_SCORE_URI = Uri.parse("content://" + DataTableConstants.AUTHORITY + "/" + SCORE_DELETED_TAG);

	public static final String CONTENT_GAMEMODE_PARAM_TYPE = "vnd.android.cursor.item/" + DataTableConstants.MIME_CONTENT_URI + "/" + GAMEMODE_PARAMS_TAG;
	public static final String CONTENTS_GAMEMODE_PARAM_TYPE = "vnd.android.cursor.dir/" + DataTableConstants.MIME_CONTENT_URI + "/" + GAMEMODE_PARAMS_TAG + "s";
	public static final Uri CONTENT_GAMEMODE_PARAM_URI = Uri.parse("content://" + DataTableConstants.AUTHORITY + "/" + GAMEMODE_PARAMS_TAG);

	public static final String CONTENT_DELETED_GAMEMODE_PARAM_TYPE = "vnd.android.cursor.item/" + DataTableConstants.MIME_CONTENT_URI + "/" + GAMEMODE_PARAMS_DELETED_TAG;
	public static final String CONTENTS_DELETED_GAMEMODE_PARAM_TYPE = "vnd.android.cursor.dir/" + DataTableConstants.MIME_CONTENT_URI + "/" + GAMEMODE_PARAMS_DELETED_TAG + "s";
	public static final Uri CONTENT_DELETED_GAMEMODE_PARAM_URI = Uri.parse("content://" + DataTableConstants.AUTHORITY + "/" + GAMEMODE_PARAMS_DELETED_TAG);


	private static final int GAMEMODE_LIST = IDataTablesMatch.GAMEMODE;
	private static final int GAMEMODE_ITEM = IDataTablesMatch.GAMEMODE + 1;
	private static final int GAMEMODE_DELETED_LIST = IDataTablesMatch.GAMEMODE + 2;
	private static final int GAMEMODE_DELETED_ITEM = IDataTablesMatch.GAMEMODE + 3;

	private static final int SCORE_LIST = IDataTablesMatch.GAMEMODE + 4;
	private static final int SCORE_ITEM = IDataTablesMatch.GAMEMODE + 5;
	private static final int SCORE_DELETED_LIST = IDataTablesMatch.GAMEMODE + 6;
	private static final int SCORE_DELETED_ITEM = IDataTablesMatch.GAMEMODE + 7;

	private static final int GAMEMODE_PARAM_LIST = IDataTablesMatch.GAMEMODE + 8;
	private static final int GAMEMODE_PARAM_ITEM = IDataTablesMatch.GAMEMODE + 9;
	private static final int GAMEMODE_PARAM_DELETED_LIST = IDataTablesMatch.GAMEMODE + 10;
	private static final int GAMEMODE_PARAM_DELETED_ITEM = IDataTablesMatch.GAMEMODE + 11;


	public RatingDataTableBuilder(UriMatcher uriMatcher, Context context) {
		super(uriMatcher, context);
	}

	@Override
	public int getTableVersion() {
		return 0;
	}

	@Override
	public boolean supportsMatch(int match) {
		return (match == GAMEMODE_LIST) || (match == GAMEMODE_ITEM) || 
				(match == GAMEMODE_DELETED_LIST) || (match == GAMEMODE_DELETED_ITEM) || 
				(match == SCORE_LIST) || (match == SCORE_ITEM) ||
				(match == SCORE_DELETED_LIST) || (match == SCORE_DELETED_ITEM);
	}

	@Override
	public String getType(int match) {
		switch (match) {
		case GAMEMODE_LIST:
			return CONTENTS_GAMEMODE_TYPE;

		case GAMEMODE_ITEM:
			return CONTENT_GAMEMODE_TYPE;

		case GAMEMODE_DELETED_LIST:
			return CONTENTS_DELETED_GAMEMODE_TYPE;

		case GAMEMODE_DELETED_ITEM:
			return CONTENT_DELETED_GAMEMODE_TYPE;

		case SCORE_LIST:
			return CONTENTS_SCORE_TYPE;

		case SCORE_ITEM:
			return CONTENT_SCORE_TYPE;

		case SCORE_DELETED_LIST:
			return CONTENTS_DELETED_SCORE_TYPE;

		case SCORE_DELETED_ITEM:
			return CONTENT_DELETED_SCORE_TYPE;

		case GAMEMODE_PARAM_LIST:
			return CONTENTS_GAMEMODE_PARAM_TYPE;

		case GAMEMODE_PARAM_ITEM:
			return CONTENT_GAMEMODE_PARAM_TYPE;

		case GAMEMODE_PARAM_DELETED_LIST:
			return CONTENT_DELETED_GAMEMODE_PARAM_TYPE;

		case GAMEMODE_PARAM_DELETED_ITEM:
			return CONTENT_DELETED_GAMEMODE_PARAM_TYPE;
		}
		return null;
	}

	@Override
	public int delete(SQLiteDatabase db, int match, Uri uri, String selection, String[] selectionArgs) {
		switch (match) {
		case GAMEMODE_ITEM:
			String whereid_Text = IGameModeColumns.ID + " = "	+ uri.getPathSegments().get(1) ;
			if(!TextUtils.isEmpty(selection)){
				whereid_Text += " and " + selection;
			}

			if(Log.isLoggable(GAMEMODE_TAG , Log.INFO)){
				Log.i(GAMEMODE_TAG," delete " + GAMEMODE_TAG + " - " + whereid_Text);
			}
			return db.delete(GAMEMODE_TAG, whereid_Text, selectionArgs);

		case GAMEMODE_DELETED_ITEM:
			String whereGameModeDeletedText = IGameModeDeletedColumns.ID + " = "	+ uri.getPathSegments().get(1) ;
			if(!TextUtils.isEmpty(selection)){
				whereGameModeDeletedText += " and " + selection;
			}


			if(Log.isLoggable(GAMEMODE_DELETED_TAG , Log.INFO)){
				Log.i(GAMEMODE_DELETED_TAG," delete " + GAMEMODE_DELETED_TAG + " - " + whereGameModeDeletedText);
			}

			return db.delete(GAMEMODE_DELETED_TAG, whereGameModeDeletedText, selectionArgs);

		case SCORE_ITEM:
			String scoreId_Text = IScoreColumns.ID + " = "	+ uri.getPathSegments().get(1) ;
			if(!TextUtils.isEmpty(selection)){
				scoreId_Text += " and " + selection;
			}

			if(Log.isLoggable(SCORE_TAG , Log.INFO)){
				Log.i(SCORE_TAG," delete " + SCORE_TAG + " - " + scoreId_Text);
			}
			return db.delete(SCORE_TAG, scoreId_Text, selectionArgs);

		case SCORE_DELETED_ITEM:
			String whereScoreDeletedText = IScoreDeletedColumns.ID + " = "	+ uri.getPathSegments().get(1) ;
			if(!TextUtils.isEmpty(selection)){
				whereScoreDeletedText += " and " + selection;
			}


			if(Log.isLoggable(SCORE_DELETED_TAG , Log.INFO)){
				Log.i(SCORE_DELETED_TAG," delete " + SCORE_DELETED_TAG + " - " + whereScoreDeletedText);
			}

			return db.delete(SCORE_DELETED_TAG, whereScoreDeletedText, selectionArgs);


		case GAMEMODE_PARAM_ITEM:
			String paramId_Text = IGameModeColumns.ID + " = "	+ uri.getPathSegments().get(1) ;
			if(!TextUtils.isEmpty(selection)){
				paramId_Text += " and " + selection;
			}

			if(Log.isLoggable(GAMEMODE_PARAMS_TAG , Log.INFO)){
				Log.i(GAMEMODE_PARAMS_TAG," delete " + GAMEMODE_PARAMS_TAG + " - " + paramId_Text);
			}
			return db.delete(GAMEMODE_PARAMS_TAG, paramId_Text, selectionArgs);

		case GAMEMODE_PARAM_DELETED_ITEM:
			String paramDeletedText = IGameModeParametersColumns.ID + " = "	+ uri.getPathSegments().get(1) ;
			if(!TextUtils.isEmpty(selection)){
				paramDeletedText += " and " + selection;
			}


			if(Log.isLoggable(GAMEMODE_PARAMS_DELETED_TAG , Log.INFO)){
				Log.i(GAMEMODE_PARAMS_DELETED_TAG," delete " + GAMEMODE_PARAMS_DELETED_TAG + " - " + paramDeletedText);
			}

			return db.delete(GAMEMODE_PARAMS_DELETED_TAG, paramDeletedText, selectionArgs);


		}
		return 0;

	}

	@Override
	public Uri insert(SQLiteDatabase db, int match, Uri uri, ContentValues values) {
		long recordId = 0;

		switch (match) {
		case GAMEMODE_ITEM:
			if(!values.containsKey(IGameModeColumns.ID)){
				values.put(IGameModeColumns.ID, uri.getPathSegments().get(1));
			}

			if(Log.isLoggable(GAMEMODE_TAG , Log.INFO)){
				Log.i(GAMEMODE_TAG,"insert " + GAMEMODE_TAG + " ITEMS");
			}

			recordId = db.replace(GAMEMODE_TAG, null, values);
			if (recordId > 0) {
				return ContentUris.withAppendedId(CONTENT_GAMEMODE_URI, recordId);
			}
			break;

		case GAMEMODE_DELETED_ITEM:
			if(!values.containsKey(IGameModeColumns.ID)){
				values.put(IGameModeDeletedColumns.ID, uri.getPathSegments().get(1));
			}

			if(Log.isLoggable(GAMEMODE_DELETED_TAG , Log.INFO)){
				Log.i(GAMEMODE_DELETED_TAG,"insert " + GAMEMODE_DELETED_TAG + " ITEMS");
			}

			recordId = db.replace(GAMEMODE_DELETED_TAG, null, values);
			if (recordId > 0) {
				return ContentUris.withAppendedId(CONTENT_DELETED_GAMEMODE_URI, recordId);
			}
			break;

		case SCORE_ITEM:
			if(!values.containsKey(IScoreColumns.ID)){
				values.put(IScoreColumns.ID, uri.getPathSegments().get(1));
			}

			if(Log.isLoggable(SCORE_TAG , Log.INFO)){
				Log.i(SCORE_TAG,"insert " + SCORE_TAG + " ITEMS");
			}

			recordId = db.replace(SCORE_TAG, null, values);
			if (recordId > 0) {
				return ContentUris.withAppendedId(CONTENT_SCORE_URI, recordId);
			}
			break;

		case SCORE_DELETED_ITEM:
			if(!values.containsKey(IScoreDeletedColumns.ID)){
				values.put(IScoreDeletedColumns.ID, uri.getPathSegments().get(1));
			}

			if(Log.isLoggable(SCORE_DELETED_TAG , Log.INFO)){
				Log.i(SCORE_DELETED_TAG,"insert " + SCORE_DELETED_TAG + " ITEMS");
			}

			recordId = db.replace(SCORE_DELETED_TAG, null, values);
			if (recordId > 0) {
				return ContentUris.withAppendedId(CONTENT_DELETED_SCORE_URI, recordId);
			}
			break;

		case GAMEMODE_PARAM_ITEM:
			if(!values.containsKey(IGameModeColumns.ID)){
				values.put(IGameModeColumns.ID, uri.getPathSegments().get(1));
			}

			if(Log.isLoggable(GAMEMODE_PARAMS_TAG , Log.INFO)){
				Log.i(GAMEMODE_PARAMS_TAG,"insert " + GAMEMODE_PARAMS_TAG + " ITEMS");
			}

			recordId = db.replace(GAMEMODE_PARAMS_TAG, null, values);
			if (recordId > 0) {
				return ContentUris.withAppendedId(CONTENT_GAMEMODE_PARAM_URI, recordId);
			}
			break;

		case GAMEMODE_PARAM_DELETED_ITEM:
			if(!values.containsKey(IGameModeParametersColumns.ID)){
				values.put(IGameModeParametersColumns.ID, uri.getPathSegments().get(1));
			}

			if(Log.isLoggable(GAMEMODE_PARAMS_DELETED_TAG , Log.INFO)){
				Log.i(GAMEMODE_PARAMS_DELETED_TAG,"insert " + GAMEMODE_PARAMS_DELETED_TAG + " ITEMS");
			}

			recordId = db.replace(GAMEMODE_PARAMS_DELETED_TAG, null, values);
			if (recordId > 0) {
				return ContentUris.withAppendedId(CONTENT_DELETED_GAMEMODE_PARAM_URI, recordId);
			}
			break;
		}

		return null;
	}

	@Override
	public Cursor query(SQLiteDatabase db, int match, Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Cursor resultCursor = null;

		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		switch (match) {
		case GAMEMODE_ITEM : 
			queryBuilder.setTables(GAMEMODE_TAG);
			String gameModeItem_Text = IGameModeColumns.ID + " = "	+ uri.getPathSegments().get(1);
			if(!TextUtils.isEmpty(selection)){
				gameModeItem_Text += " and " + selection;
			}

			if(Log.isLoggable(GAMEMODE_TAG , Log.INFO)){
				Log.i(GAMEMODE_TAG,"Query Application " + gameModeItem_Text);
			}
			resultCursor = queryBuilder.query(db, projection, gameModeItem_Text,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_GAMEMODE_URI);
			break;

		case GAMEMODE_LIST :
			queryBuilder.setTables(GAMEMODE_TAG);
			if(Log.isLoggable(GAMEMODE_TAG , Log.INFO)){
				Log.i(GAMEMODE_TAG,"Query Application " + selection);
			}
			resultCursor = queryBuilder.query(db, projection, selection,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_GAMEMODE_URI);
			break;

		case GAMEMODE_DELETED_ITEM : 
			queryBuilder.setTables(GAMEMODE_DELETED_TAG);
			String gameModeDeletedItem_Text = IGameModeDeletedColumns.ID + " = "	+ uri.getPathSegments().get(1);
			if(!TextUtils.isEmpty(selection)){
				gameModeDeletedItem_Text += " and " + selection;
			}

			if(Log.isLoggable(GAMEMODE_DELETED_TAG , Log.INFO)){
				Log.i(GAMEMODE_DELETED_TAG,"Query Application " + gameModeDeletedItem_Text);
			}
			resultCursor = queryBuilder.query(db, projection, gameModeDeletedItem_Text,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_DELETED_GAMEMODE_URI);
			break;

		case GAMEMODE_DELETED_LIST :
			queryBuilder.setTables(GAMEMODE_DELETED_TAG);
			if(Log.isLoggable(GAMEMODE_DELETED_TAG , Log.INFO)){
				Log.i(GAMEMODE_DELETED_TAG,"Query Application " + selection);
			}
			resultCursor = queryBuilder.query(db, projection, selection,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_DELETED_GAMEMODE_URI);
			break;

		case SCORE_ITEM : 
			queryBuilder.setTables(SCORE_TAG);
			String scoreItem_Text = IScoreColumns.ID + " = "	+ uri.getPathSegments().get(1);
			if(!TextUtils.isEmpty(selection)){
				scoreItem_Text += " and " + selection;
			}

			if(Log.isLoggable(SCORE_TAG , Log.INFO)){
				Log.i(SCORE_TAG,"Query Application " + scoreItem_Text);
			}
			resultCursor = queryBuilder.query(db, projection, scoreItem_Text,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_GAMEMODE_URI);
			break;

		case SCORE_LIST :
			queryBuilder.setTables(SCORE_TAG);
			if(Log.isLoggable(SCORE_TAG , Log.INFO)){
				Log.i(SCORE_TAG,"Query Application " + selection);
			}
			resultCursor = queryBuilder.query(db, projection, selection,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_SCORE_URI);
			break;

		case SCORE_DELETED_ITEM : 
			queryBuilder.setTables(SCORE_DELETED_TAG);
			String scoreDeletedItem_Text = IScoreDeletedColumns.ID + " = "	+ uri.getPathSegments().get(1);
			if(!TextUtils.isEmpty(selection)){
				scoreDeletedItem_Text += " and " + selection;
			}

			if(Log.isLoggable(GAMEMODE_DELETED_TAG , Log.INFO)){
				Log.i(GAMEMODE_DELETED_TAG,"Query Application " + scoreDeletedItem_Text);
			}
			resultCursor = queryBuilder.query(db, projection, scoreDeletedItem_Text,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_DELETED_SCORE_URI);
			break;

		case SCORE_DELETED_LIST :
			queryBuilder.setTables(SCORE_DELETED_TAG);
			if(Log.isLoggable(SCORE_DELETED_TAG , Log.INFO)){
				Log.i(SCORE_DELETED_TAG,"Query Application " + selection);
			}
			resultCursor = queryBuilder.query(db, projection, selection,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_DELETED_SCORE_URI);
			break;


		case GAMEMODE_PARAM_ITEM : 
			queryBuilder.setTables(GAMEMODE_PARAMS_TAG);
			String paramItem_Text = IGameModeParametersColumns.ID + " = "	+ uri.getPathSegments().get(1);
			if(!TextUtils.isEmpty(selection)){
				paramItem_Text += " and " + selection;
			}

			if(Log.isLoggable(GAMEMODE_PARAMS_TAG , Log.INFO)){
				Log.i(GAMEMODE_PARAMS_TAG,"Query Application " + paramItem_Text);
			}
			resultCursor = queryBuilder.query(db, projection, paramItem_Text,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_GAMEMODE_PARAM_URI);
			break;

		case GAMEMODE_PARAM_LIST :
			queryBuilder.setTables(GAMEMODE_PARAMS_TAG);
			if(Log.isLoggable(GAMEMODE_PARAMS_TAG , Log.INFO)){
				Log.i(GAMEMODE_PARAMS_TAG,"Query Application " + selection);
			}
			resultCursor = queryBuilder.query(db, projection, selection,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_GAMEMODE_PARAM_URI);
			break;

		case GAMEMODE_PARAM_DELETED_ITEM : 
			queryBuilder.setTables(GAMEMODE_PARAMS_DELETED_TAG);
			String paramDeletedItem_Text = IGameModeParametersDeletedColumns.ID + " = "	+ uri.getPathSegments().get(1);
			if(!TextUtils.isEmpty(selection)){
				paramDeletedItem_Text += " and " + selection;
			}

			if(Log.isLoggable(GAMEMODE_PARAMS_DELETED_TAG , Log.INFO)){
				Log.i(GAMEMODE_PARAMS_DELETED_TAG,"Query Application " + paramDeletedItem_Text);
			}
			resultCursor = queryBuilder.query(db, projection, paramDeletedItem_Text,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_DELETED_GAMEMODE_PARAM_URI);
			break;

		case GAMEMODE_PARAM_DELETED_LIST :
			queryBuilder.setTables(GAMEMODE_PARAMS_DELETED_TAG);
			if(Log.isLoggable(GAMEMODE_PARAMS_DELETED_TAG , Log.INFO)){
				Log.i(GAMEMODE_PARAMS_DELETED_TAG,"Query Application " + selection);
			}
			resultCursor = queryBuilder.query(db, projection, selection,	selectionArgs, null, null, sortOrder);
			resultCursor.setNotificationUri(context.getContentResolver(), CONTENT_DELETED_GAMEMODE_PARAM_URI);
			break;


		}

		return resultCursor;
	}

	@Override
	public int update(SQLiteDatabase db, int match, Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		switch (match) {
		case GAMEMODE_ITEM:
			String gameModeWhere_Text = IGameModeColumns.ID + " = "	+ uri.getPathSegments().get(1) ;
			if(!TextUtils.isEmpty(selection)){
				gameModeWhere_Text += " and " + selection;
			}

			if(Log.isLoggable(GAMEMODE_TAG , Log.INFO)){
				Log.i(GAMEMODE_TAG," update " + GAMEMODE_TAG + " - " + gameModeWhere_Text);
			}
			return db.update(GAMEMODE_TAG, values, gameModeWhere_Text, selectionArgs);

		case SCORE_ITEM:
			String scoreWhere_Text = IScoreColumns.ID + " = " + uri.getPathSegments().get(1) ;
			if(!TextUtils.isEmpty(selection)){
				scoreWhere_Text += " and " + selection;
			}

			if(Log.isLoggable(GAMEMODE_TAG , Log.INFO)){
				Log.i(SCORE_TAG," update " + SCORE_TAG + " - " + scoreWhere_Text);
			}
			return db.update(SCORE_TAG, values, scoreWhere_Text, selectionArgs);
			
		case GAMEMODE_PARAM_ITEM:
			String gameParam_Text = IGameModeParametersColumns.ID + " = " + uri.getPathSegments().get(1) ;
			if(!TextUtils.isEmpty(selection)){
				gameParam_Text += " and " + selection;
			}

			if(Log.isLoggable(GAMEMODE_PARAMS_TAG , Log.INFO)){
				Log.i(GAMEMODE_PARAMS_TAG," update " + GAMEMODE_PARAMS_TAG + " - " + gameParam_Text);
			}
			return db.update(GAMEMODE_PARAMS_TAG, values, gameParam_Text, selectionArgs);

		}


		return 0;
	}

	@Override
	public void createTiggers(SQLiteDatabase db) {

		StringBuilder sqlSB = new StringBuilder();


		sqlSB.append("CREATE TRIGGER IF NOT EXISTS deleteGameModeTrigger DELETE ON " + GAMEMODE_TAG);
		sqlSB.append(" FOR EACH ROW BEGIN ");

		sqlSB.append(" Insert INTO " + GAMEMODE_DELETED_TAG + " ( ");
		sqlSB.append(IGameModeDeletedColumns.ID);
		sqlSB.append("," + IGameModeDeletedColumns.ACCOUNT );
		sqlSB.append("," + IGameModeDeletedColumns.APPLICATION_ID );
		sqlSB.append("," + IGameModeDeletedColumns.USER_ID );
		sqlSB.append("," + IGameModeDeletedColumns.DELETED_AT );
		sqlSB.append(" ) values (");
		sqlSB.append("old." +IGameModeColumns.ID);
		sqlSB.append(", old." + IGameModeColumns.ACCOUNT );
		sqlSB.append(", old." + IGameModeColumns.APPLICATION_ID );
		sqlSB.append(", old." + IGameModeColumns.USER_ID );
		sqlSB.append(", DATETIME('NOW') ");
		sqlSB.append(");" );

		sqlSB.append(" delete from " + SCORE_TAG + " where " + IScoreColumns.GAME_MODE_ID + " = old." + IGameModeColumns.ID  + "; ");
		sqlSB.append(" END ");

		db.execSQL(sqlSB.toString());
		if(Log.isLoggable(GAMEMODE_TAG, Log.INFO)){
			Log.i(GAMEMODE_TAG,"createTrigger : deleteGameModeTrigger ");
		}

		if(Log.isLoggable(GAMEMODE_TAG, Log.VERBOSE)){
			Log.v(GAMEMODE_TAG,"createTrigger : deleteGameModeTrigger ");
		}


		sqlSB.setLength(0);
		sqlSB.append("CREATE TRIGGER IF NOT EXISTS deleteScoreTrigger DELETE ON " + SCORE_TAG);
		sqlSB.append(" FOR EACH ROW BEGIN ");
		sqlSB.append(" Insert INTO " + SCORE_DELETED_TAG + " ( ");
		sqlSB.append(IScoreDeletedColumns.ID);
		sqlSB.append("," + IScoreDeletedColumns.ACCOUNT );
		sqlSB.append("," + IScoreDeletedColumns.APPLICATION_ID );
		sqlSB.append("," + IScoreDeletedColumns.GAME_MODE_ID );
		sqlSB.append("," + IScoreDeletedColumns.DELETED_AT );
		sqlSB.append(" ) values (");
		sqlSB.append("old." +IScoreColumns.ID);
		sqlSB.append(", old." + IScoreColumns.ACCOUNT );
		sqlSB.append(", old." + IScoreColumns.APPLICATION_ID );
		sqlSB.append(", old." + IScoreColumns.GAME_MODE_ID );
		sqlSB.append(", DATETIME('NOW') " );
		sqlSB.append(");" );
		sqlSB.append(" END ");

		db.execSQL(sqlSB.toString());

		if(Log.isLoggable(GAMEMODE_TAG, Log.INFO)){
			Log.i(GAMEMODE_TAG,"createTrigger : deleteScoreTrigger ");
		}

		if(Log.isLoggable(GAMEMODE_TAG, Log.VERBOSE)){
			Log.v(GAMEMODE_TAG,"createTrigger : deleteScoreTrigger ");
		}

		sqlSB.setLength(0);
		sqlSB.append("CREATE TRIGGER IF NOT EXISTS deleteGameModeParamTrigger DELETE ON " + GAMEMODE_PARAMS_TAG);
		sqlSB.append(" FOR EACH ROW BEGIN ");
		sqlSB.append(" Insert INTO " + GAMEMODE_PARAMS_DELETED_TAG + " ( ");
		sqlSB.append(IGameModeParametersDeletedColumns.ID);
		sqlSB.append("," + IGameModeParametersDeletedColumns.ACCOUNT );
		sqlSB.append("," + IGameModeParametersDeletedColumns.APPLICATION_ID );
		sqlSB.append("," + IGameModeParametersDeletedColumns.GAME_MODE_ID );
		sqlSB.append("," + IGameModeParametersDeletedColumns.DELETED_AT );
		sqlSB.append(" ) values (");
		sqlSB.append("old." +IGameModeParametersColumns.ID);
		sqlSB.append(", old." + IGameModeParametersColumns.ACCOUNT );
		sqlSB.append(", old." + IGameModeParametersColumns.APPLICATION_ID );
		sqlSB.append(", old." + IGameModeParametersColumns.GAME_MODE_ID );
		sqlSB.append(", DATETIME('NOW') " );
		sqlSB.append(");" );
		sqlSB.append(" END ");

		db.execSQL(sqlSB.toString());

		if(Log.isLoggable(GAMEMODE_PARAMS_TAG, Log.INFO)){
			Log.i(GAMEMODE_PARAMS_TAG,"createTrigger : deleteGameModeParamTrigger ");
		}

		if(Log.isLoggable(GAMEMODE_PARAMS_TAG, Log.VERBOSE)){
			Log.v(GAMEMODE_PARAMS_TAG,"createTrigger : deleteGameModeParamTrigger ");
		}
	}

	@Override
	public boolean createTables(SQLiteDatabase db) {
		StringBuilder tableSB = new StringBuilder();
		tableSB.append("CREATE TABLE IF NOT EXISTS " + GAMEMODE_TAG);
		tableSB.append(" ( ");
		tableSB.append(IGameModeColumns.ID + "  INTEGER PRIMARY KEY");
		tableSB.append("," + IGameModeColumns.ACCOUNT + " TEXT ");
		tableSB.append("," + IGameModeColumns.APPLICATION_ID + " INTEGER ");
		tableSB.append("," + IGameModeColumns.USER_ID + " INTEGER ");
		tableSB.append("," + IGameModeColumns.TITLE + " TEXT ");
		tableSB.append("," + IGameModeColumns.DIRTY	+ " BOOLEAN ");
		tableSB.append("," + IGameModeColumns.SYNCED_AT	+ " INTEGER ");
		tableSB.append("," + IGameModeColumns.JSON_OBJECT	+ " TEXT ");
		tableSB.append(" )");

		db.execSQL(tableSB.toString());

		if(Log.isLoggable(GAMEMODE_TAG, Log.INFO)){
			Log.i(GAMEMODE_TAG,"createTables : " + GAMEMODE_TAG);
		}

		if(Log.isLoggable(GAMEMODE_TAG, Log.VERBOSE)){
			Log.v(GAMEMODE_TAG,"createTables : " + GAMEMODE_TAG);
		}


		tableSB.setLength(0);
		tableSB.append("CREATE TABLE IF NOT EXISTS " + GAMEMODE_DELETED_TAG);
		tableSB.append(" ( ");
		tableSB.append(IGameModeDeletedColumns.ID + "  INTEGER PRIMARY KEY");
		tableSB.append("," + IGameModeDeletedColumns.ACCOUNT + " TEXT ");
		tableSB.append("," + IGameModeDeletedColumns.APPLICATION_ID + " INTEGER ");
		tableSB.append("," + IGameModeDeletedColumns.USER_ID + " INTEGER ");
		tableSB.append("," + IGameModeDeletedColumns.DELETED_AT + " INTEGER ");
		tableSB.append(" )");

		db.execSQL(tableSB.toString());

		if(Log.isLoggable(GAMEMODE_DELETED_TAG, Log.INFO)){
			Log.i(GAMEMODE_DELETED_TAG,"createTables : " + GAMEMODE_DELETED_TAG);
		}

		if(Log.isLoggable(GAMEMODE_DELETED_TAG, Log.VERBOSE)){
			Log.v(GAMEMODE_DELETED_TAG,"createTables : " + GAMEMODE_DELETED_TAG);
		}		


		tableSB.setLength(0);
		tableSB.append("CREATE TABLE IF NOT EXISTS " + SCORE_TAG);
		tableSB.append(" ( ");
		tableSB.append(IScoreColumns.ID + "  INTEGER PRIMARY KEY");
		tableSB.append("," + IScoreColumns.ACCOUNT + " TEXT ");
		tableSB.append("," + IScoreColumns.APPLICATION_ID + " INTEGER ");
		tableSB.append("," + IScoreColumns.USER_ID + " INTEGER ");
		tableSB.append("," + IScoreColumns.VALUE + " INTEGER ");
		tableSB.append("," + IGameModeColumns.DIRTY	+ " BOOLEAN ");
		tableSB.append("," + IGameModeColumns.SYNCED_AT	+ " INTEGER ");
		tableSB.append("," + IGameModeColumns.JSON_OBJECT	+ " TEXT ");
		tableSB.append(" )");

		db.execSQL(tableSB.toString());

		if(Log.isLoggable(SCORE_TAG, Log.INFO)){
			Log.i(SCORE_TAG,"createTables : " + SCORE_TAG);
		}

		if(Log.isLoggable(SCORE_TAG, Log.VERBOSE)){
			Log.v(SCORE_TAG,"createTables : " + SCORE_TAG);
		}

		tableSB.setLength(0);
		tableSB.append("CREATE TABLE IF NOT EXISTS " + SCORE_DELETED_TAG);
		tableSB.append(" ( ");
		tableSB.append(IScoreDeletedColumns.ID + "  INTEGER PRIMARY KEY");
		tableSB.append("," + IScoreDeletedColumns.ACCOUNT + " TEXT ");
		tableSB.append("," + IScoreDeletedColumns.APPLICATION_ID + " INTEGER ");
		tableSB.append("," + IScoreDeletedColumns.GAME_MODE_ID + " INTEGER ");
		tableSB.append("," + IScoreDeletedColumns.DELETED_AT + " INTEGER ");
		tableSB.append(" )");

		db.execSQL(tableSB.toString());

		if(Log.isLoggable(SCORE_DELETED_TAG, Log.INFO)){
			Log.i(SCORE_DELETED_TAG,"createTables : " + SCORE_DELETED_TAG);
		}

		if(Log.isLoggable(SCORE_DELETED_TAG, Log.VERBOSE)){
			Log.v(SCORE_DELETED_TAG,"createTables : " + SCORE_DELETED_TAG);
		}		

		tableSB.setLength(0);
		tableSB.append("CREATE TABLE IF NOT EXISTS " + GAMEMODE_PARAMS_TAG);
		tableSB.append(" ( ");
		tableSB.append(IGameModeParametersColumns.ID + "  INTEGER PRIMARY KEY");
		tableSB.append("," + IGameModeParametersColumns.ACCOUNT + " TEXT ");
		tableSB.append("," + IGameModeParametersColumns.APPLICATION_ID + " INTEGER ");
		tableSB.append("," + IGameModeParametersColumns.NAME + " TEXT ");
		tableSB.append("," + IGameModeParametersColumns.GAME_MODE_ID + " INTEGER ");
		tableSB.append("," + IGameModeColumns.DIRTY	+ " BOOLEAN ");
		tableSB.append("," + IGameModeColumns.SYNCED_AT	+ " INTEGER ");
		tableSB.append("," + IGameModeColumns.JSON_OBJECT	+ " TEXT ");
		tableSB.append(" )");

		db.execSQL(tableSB.toString());

		if(Log.isLoggable(GAMEMODE_PARAMS_TAG, Log.INFO)){
			Log.i(GAMEMODE_PARAMS_TAG,"createTables : " + GAMEMODE_PARAMS_TAG);
		}

		if(Log.isLoggable(GAMEMODE_PARAMS_TAG, Log.VERBOSE)){
			Log.v(GAMEMODE_PARAMS_TAG,"createTables : " + GAMEMODE_PARAMS_TAG);
		}

		tableSB.setLength(0);
		tableSB.append("CREATE TABLE IF NOT EXISTS " + GAMEMODE_PARAMS_DELETED_TAG);
		tableSB.append(" ( ");
		tableSB.append(IGameModeParametersDeletedColumns.ID + "  INTEGER PRIMARY KEY");
		tableSB.append("," + IGameModeParametersDeletedColumns.ACCOUNT + " TEXT ");
		tableSB.append("," + IGameModeParametersDeletedColumns.APPLICATION_ID + " INTEGER ");
		tableSB.append("," + IGameModeParametersDeletedColumns.GAME_MODE_ID + " INTEGER ");
		tableSB.append("," + IGameModeParametersDeletedColumns.DELETED_AT + " INTEGER ");
		tableSB.append(" )");

		db.execSQL(tableSB.toString());

		if(Log.isLoggable(GAMEMODE_PARAMS_DELETED_TAG, Log.INFO)){
			Log.i(GAMEMODE_PARAMS_DELETED_TAG,"createTables : " + GAMEMODE_PARAMS_DELETED_TAG);
		}

		if(Log.isLoggable(GAMEMODE_PARAMS_DELETED_TAG, Log.VERBOSE)){
			Log.v(GAMEMODE_PARAMS_DELETED_TAG,"createTables : " + GAMEMODE_PARAMS_DELETED_TAG);
		}	
		
		return true;
	}

	@Override
	public String getTableName() {
		return GAMEMODE_TAG;
	}

	@Override
	public void addMatcherUri(UriMatcher uriMatcher) {
		uriMatcher.addURI(DataTableConstants.AUTHORITY, GAMEMODE_TAG + "/",	GAMEMODE_LIST);
		uriMatcher.addURI(DataTableConstants.AUTHORITY, GAMEMODE_TAG + "/#" , GAMEMODE_ITEM);
		uriMatcher.addURI(DataTableConstants.AUTHORITY, GAMEMODE_DELETED_TAG + "/",	GAMEMODE_DELETED_LIST);
		uriMatcher.addURI(DataTableConstants.AUTHORITY, GAMEMODE_DELETED_TAG + "/#" , GAMEMODE_DELETED_ITEM);


		uriMatcher.addURI(DataTableConstants.AUTHORITY, SCORE_TAG + "/",	SCORE_LIST);
		uriMatcher.addURI(DataTableConstants.AUTHORITY, SCORE_TAG + "/#" , SCORE_ITEM);
		uriMatcher.addURI(DataTableConstants.AUTHORITY, SCORE_DELETED_TAG + "/",	SCORE_DELETED_LIST);
		uriMatcher.addURI(DataTableConstants.AUTHORITY, SCORE_DELETED_TAG + "/#" , SCORE_DELETED_ITEM);

		uriMatcher.addURI(DataTableConstants.AUTHORITY, GAMEMODE_PARAMS_TAG + "/",	GAMEMODE_PARAM_LIST);
		uriMatcher.addURI(DataTableConstants.AUTHORITY, GAMEMODE_PARAMS_TAG + "/#" , GAMEMODE_PARAM_ITEM);
		uriMatcher.addURI(DataTableConstants.AUTHORITY, GAMEMODE_PARAMS_DELETED_TAG + "/",	GAMEMODE_PARAM_DELETED_LIST);
		uriMatcher.addURI(DataTableConstants.AUTHORITY, GAMEMODE_PARAMS_DELETED_TAG + "/#" , GAMEMODE_PARAM_DELETED_ITEM);

	}

}
