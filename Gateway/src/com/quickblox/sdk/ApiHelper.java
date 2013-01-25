package com.quickblox.sdk;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.madmouse.core.utils.Util;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.quickblox.gateway.BuildConfig;
import com.quickblox.sdk.HttpUtils.Method;
import com.quickblox.sdk.data.ApplicationTableBuilder;
import com.quickblox.sdk.data.QuickBloxContentProvider;
import com.quickblox.sdk.data.UserDataTableBuilder;
import com.quickblox.sdk.data.interfaces.IApplicationTableBuilder;
import com.quickblox.sdk.interfaces.IDataSchema;
import com.quickblox.sdk.interfaces.IDataSchemaCallback;
import com.quickblox.sdk.interfaces.ISession;
import com.quickblox.sdk.interfaces.ISessionCallBack;
import com.quickblox.sdk.interfaces.IUser;
import com.quickblox.sdk.interfaces.IUserCallBack;



public class ApiHelper {
	private static final String TAG = "util";

	private static final String URL_BASE = "https://api.quickblox.com";
	private static final String URL_SESSION = URL_BASE +  "/session.json";
	private static final String URL_LOGIN = URL_BASE +  "/login.json";
	private static final String URL_USERS = URL_BASE +  "/users.json";
	private static final String URL_USER = URL_BASE +  "/users/%d.json";
	private static final String URL_DATA = URL_BASE +  "/data";


	private static final String HEADER_CONTENTTYPE_PARAM = "Content-Type";
	private static final String HEADER_QB_TOKEN_PARAM = "QB-Token";
	private static final String HEADER_QB_API_VERSION_PARAM = "REST-API-Version";

	private static final String HEADER_CONTENTTYPE_VALUE = "application/json";
	private static final String HEADER_QB_API_VERSION_VALUE = "0.1.0";

	public static final SimpleDateFormat mQBDateFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	public interface HTTPColumns{
		public static final String HTTP_APPLICATION_ID = "application_id";
		public static final String HTTP_AUTH_KEY = "auth_key";
		public static final String HTTP_NONCE = "nonce";
		public static final String HTTP_TIMESTAMP = "timestamp";
		public static final String HTTP_SIGNATURE = "signature";

		public static final String HTTP_LOGIN = "login";
		public static final String HTTP_PASSWORD = "password";

		public static final String HTTP_PAGE = "page";
		public static final String HTTP_PER_PAGE = "per_page";


	}

	static Map<String, List<String>> getDefaultHeaders(){
		Map<String, List<String>> headers = new HashMap<String, List<String>>();

		headers.put(HEADER_CONTENTTYPE_PARAM, Arrays.asList(new String[] { HEADER_CONTENTTYPE_VALUE }));
		headers.put(HEADER_QB_API_VERSION_PARAM, Arrays.asList(new String[] { HEADER_QB_API_VERSION_VALUE }));

		return headers;
	}

	private static void addAppSpecificHeaderData(Context context, Integer applicationId, Map<String, List<String>> headers){
		QuickBloxContentProvider quickBloxProvider = QuickBloxContentProvider.getInstance(context);

		Cursor cursor = quickBloxProvider.query(ContentUris.withAppendedId(ApplicationTableBuilder.CONTENT_URI  , applicationId), null, null, null,null);
		if(cursor != null){
			while(cursor.moveToNext()){
				String token = cursor.getString(cursor.getColumnIndex(IApplicationTableBuilder.IColumns.TOKEN));
				if(!TextUtils.isEmpty(token)){
					headers.put(HEADER_QB_TOKEN_PARAM, Arrays.asList(new String[] { token }));
				}
			}
		}
	}

	public static ISession createApplicationSession(Context context, String account, Integer applicationId, String nonce,String authKey, String authSecret) throws InvalidKeyException, NoSuchAlgorithmException, JSONException, MalformedURLException, IOException{
		return createApplicationSession(context, account, applicationId, nonce, authKey, authSecret, null);
	}

	public static ISession createApplicationSession(Context context, String account, Integer applicationId, String nonce,String authKey, String authSecret, ISessionCallBack sessionCallBack) throws InvalidKeyException, NoSuchAlgorithmException, JSONException, MalformedURLException, IOException{
		Long timeStamp = System.currentTimeMillis() / 1000;
		String signature = Util.hmac_sha1(String.format(Locale.US, "%s=%d&%s=%s&%s=%s&%s=%d", 	HTTPColumns.HTTP_APPLICATION_ID, applicationId,
				HTTPColumns.HTTP_AUTH_KEY, authKey,
				HTTPColumns.HTTP_NONCE, nonce,
				HTTPColumns.HTTP_TIMESTAMP, timeStamp), authSecret);
		if(BuildConfig.DEBUG){
			Log.d(TAG,"Signature is " + signature + " : " + timeStamp);
		}

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(HTTPColumns.HTTP_APPLICATION_ID, applicationId);
		jsonObject.put(HTTPColumns.HTTP_AUTH_KEY, authKey);
		jsonObject.put(HTTPColumns.HTTP_NONCE, nonce);
		jsonObject.put(HTTPColumns.HTTP_TIMESTAMP, timeStamp);
		jsonObject.put(HTTPColumns.HTTP_SIGNATURE, signature);

		HttpURLConnection httpResponse = HttpUtils.openUrl(URL_SESSION, Method.POST, null, getDefaultHeaders(), jsonObject.toString());

		ISession resultSession = null;
		if(httpResponse != null){
			try {
				if(httpResponse != null && httpResponse.getResponseCode() == HttpsURLConnection.HTTP_CREATED){
					InputStreamReader inputStreamReader = new InputStreamReader(httpResponse.getInputStream(), HTTP.UTF_8);
					resultSession = new Session(account, new JsonReader( inputStreamReader));

					if(sessionCallBack != null){
						sessionCallBack.onSessionCreated(account, applicationId, resultSession);
					}

					ContentValues values = resultSession.getContentValues();
					values.put(IApplicationTableBuilder.IColumns.AUTH_SECRET, authSecret);
					values.put(IApplicationTableBuilder.IColumns.AUTH_KEY, authKey);

					QuickBloxContentProvider quickBloxProvider = QuickBloxContentProvider.getInstance(context);
					Uri uri = quickBloxProvider.insert(ContentUris.withAppendedId(ApplicationTableBuilder.CONTENT_URI  , resultSession.getApplicationId()), values);

					if(BuildConfig.DEBUG){
						Log.i(TAG," Inserted Application uri" + uri);
					}

				}
			}
			finally{
				httpResponse.disconnect();
			}
		}
		return resultSession;
	}

	public static Integer deleteApplicationSession(Context context, String account, Integer applicationId) throws MalformedURLException, IOException{
		return  deleteApplicationSession(context, account, applicationId, null);
	}

	public static Integer deleteApplicationSession(Context context,String account, Integer applicationId, ISessionCallBack sessionCallBack) throws MalformedURLException, IOException{

		Map<String,List<String>> headers = getDefaultHeaders();
		addAppSpecificHeaderData(context, applicationId, headers);

		HttpURLConnection httpResponse = HttpUtils.openUrl(URL_SESSION, Method.DELETE, null,headers , null);

		if(httpResponse != null){

			if(sessionCallBack != null && httpResponse.getResponseCode() == HttpURLConnection.HTTP_OK){
				sessionCallBack.onSessionDeleted(account, applicationId);
			}

			httpResponse.disconnect();
			return httpResponse.getResponseCode();
		}

		return -1;
	}

	public static IUser loginUser(Context context, Integer applicationId, String account, String userName, String password) throws MalformedURLException, IOException, JSONException{
		return loginUser(context, applicationId, account, userName, password, null);
	}
	public static IUser loginUser(Context context, Integer applicationId, String account, String userName, String password, IUserCallBack userCallBack) throws MalformedURLException, IOException, JSONException{

		Long timeStamp = System.currentTimeMillis() / 1000;

		Map<String,List<String>> headers = getDefaultHeaders();
		addAppSpecificHeaderData(context, applicationId, headers);


		JSONObject jsonObject = new JSONObject();

		jsonObject.put(HTTPColumns.HTTP_APPLICATION_ID, applicationId);
		jsonObject.put(HTTPColumns.HTTP_LOGIN, userName);
		jsonObject.put(HTTPColumns.HTTP_PASSWORD, password);
		jsonObject.put(HTTPColumns.HTTP_TIMESTAMP, timeStamp);

		HttpURLConnection httpResponse = HttpUtils.openUrl(URL_LOGIN, Method.POST, null, headers, jsonObject.toString());
		IUser resultUser = null;
		if(httpResponse != null){
			try {
				if(httpResponse != null && httpResponse.getResponseCode() == HttpsURLConnection.HTTP_ACCEPTED){
					InputStreamReader inputStreamReader = new InputStreamReader(httpResponse.getInputStream(), HTTP.UTF_8);
					resultUser = new User(applicationId, account, new JsonReader( inputStreamReader));

					if(userCallBack != null){
						userCallBack.onUserCreated(account, applicationId, resultUser);
					}

					ContentValues values = resultUser.getContentValues();

					QuickBloxContentProvider quickBloxProvider = QuickBloxContentProvider.getInstance(context);
					Uri uri = quickBloxProvider.insert(ContentUris.withAppendedId(UserDataTableBuilder.CONTENT_URI  , resultUser.getId()), values);
					if(BuildConfig.DEBUG){
						Log.i(TAG," Inserted User uri" + uri);
					}

				}
			}
			finally{
				httpResponse.disconnect();
			}
		}
		return resultUser;
	}

	public static Integer logoutUser(Context context, Integer applicationId, String account) throws MalformedURLException, IOException{
		return logoutUser(context, applicationId, null);
	}
	public static Integer logoutUser(Context context, Integer applicationId, String account, IUserCallBack userCallback) throws MalformedURLException, IOException{

		Map<String,List<String>> headers = getDefaultHeaders();
		addAppSpecificHeaderData(context, applicationId, headers);

		HttpURLConnection httpResponse = HttpUtils.openUrl(URL_LOGIN, Method.DELETE, null,headers , null);

		if(httpResponse != null){
			if(userCallback != null && httpResponse.getResponseCode() == HttpURLConnection.HTTP_OK){
				userCallback.onUserDeleted(account, applicationId);
			}

			httpResponse.disconnect();
			return httpResponse.getResponseCode();
		}

		return -1;
	}

	private static void processUserData(Context context, Integer applicationId, String account, JsonReader jsonReader, List<IUser> userList, IUserCallBack userCallBack ){

		IUser resultUser = new User(applicationId,account,jsonReader);
		if(userCallBack != null){
			userCallBack.onUserCreated(account, applicationId, resultUser);
		}

		userList.add(resultUser);

		ContentValues values = resultUser.getContentValues();

		QuickBloxContentProvider quickBloxProvider = QuickBloxContentProvider.getInstance(context);
		Uri uri = quickBloxProvider.insert(ContentUris.withAppendedId(UserDataTableBuilder.CONTENT_URI  , resultUser.getId()), values);
		if(BuildConfig.DEBUG){
			Log.i(TAG," Inserted User uri" + uri);
		}

	}

	private enum IUsersTags{
		current_page,
		per_page,
		total_entries,
		items;
	}

	private static void createUserLists(Context context, Integer applicationId, String account,Integer page, List<IUser> userList, IUserCallBack userCallBack) throws MalformedURLException, IOException{
		Map<String,List<String>> headers = getDefaultHeaders();
		addAppSpecificHeaderData(context, applicationId, headers);

		Bundle parameters = new Bundle();
		parameters.putString(HTTPColumns.HTTP_PAGE, Integer.toString(page));
		parameters.putString(HTTPColumns.HTTP_PER_PAGE, "1");

		HttpURLConnection httpResponse = HttpUtils.openUrl(URL_USERS, Method.GET, parameters, headers, null);
		if(httpResponse != null){
			try {
				if(httpResponse != null && httpResponse.getResponseCode() == HttpsURLConnection.HTTP_OK){
					InputStreamReader inputStreamReader = new InputStreamReader(httpResponse.getInputStream(), HTTP.UTF_8);
					JsonReader jsonReader = new JsonReader( inputStreamReader);

					jsonReader.setLenient(true);
					jsonReader.beginObject();
					Integer currentPage = 1;
					Boolean hasItems = false;
					while( jsonReader.hasNext()){
						final String name = jsonReader.nextName();

						if(BuildConfig.DEBUG){
							Log.d(TAG,"User Parser :" + name);
						}

						final boolean isNull = jsonReader.peek() == JsonToken.NULL;
						if( name.equals( IUsersTags.current_page.name()) &&  !isNull ) {
							currentPage = jsonReader.nextInt();
						} else {
							if( name.equals( IUsersTags.items.name()) &&  !isNull ) {
								jsonReader.beginArray();
								while( jsonReader.hasNext()){
									hasItems = true;
									processUserData(context, applicationId, account, jsonReader, userList, userCallBack);
								}
								jsonReader.endArray();
							} else {
								jsonReader.skipValue();
							}
						}
					}

					jsonReader.endObject();
					jsonReader.close();
					if(hasItems){
						createUserLists(context,applicationId,account,currentPage + 1,userList,userCallBack);
					}
				}
			}
			finally{
				httpResponse.disconnect();
			}
		}
	}

	public static List<IUser> listUsers(Context context, Integer applicationId, String account) throws MalformedURLException, IOException{
		return listUsers(context, applicationId, account, null);
	}

	public static List<IUser> listUsers(Context context, Integer applicationId, String account, IUserCallBack userCallBack) throws MalformedURLException, IOException{
		List<IUser> userList = new ArrayList<IUser>();
		createUserLists(context, applicationId, account, 1, userList, userCallBack);  
		return userList;	
	}

	public static IUser updateUser(Context context, Integer applicationId, String account, IUser userObject) throws MalformedURLException, IOException, JSONException{
		return updateUser(context, applicationId, account, userObject, null);
	}

	public static IUser updateUser(Context context, Integer applicationId, String account, IUser userObject, IUserCallBack userCallBack) throws MalformedURLException, IOException, JSONException{

		Map<String,List<String>> headers = getDefaultHeaders();
		addAppSpecificHeaderData(context, applicationId, headers);

		HttpURLConnection httpResponse = HttpUtils.openUrl(String.format(URL_USER,userObject.getId()), Method.PUT, null, headers, userObject.getJSONObject().toString());
		IUser resultUser = null;
		if(httpResponse != null){
			try {
				if(httpResponse != null && httpResponse.getResponseCode() == HttpsURLConnection.HTTP_OK){
					InputStreamReader inputStreamReader = new InputStreamReader(httpResponse.getInputStream(), HTTP.UTF_8);
					resultUser = new User(applicationId, account, new JsonReader( inputStreamReader));

					if(userCallBack != null){
						userCallBack.onUserUpdated(account, applicationId, resultUser);
					}

					ContentValues values = resultUser.getContentValues();

					QuickBloxContentProvider quickBloxProvider = QuickBloxContentProvider.getInstance(context);
					Uri uri = quickBloxProvider.insert(ContentUris.withAppendedId(UserDataTableBuilder.CONTENT_URI  , resultUser.getId()), values);
					if(BuildConfig.DEBUG){
						Log.i(TAG," Updated User uri" + uri);
					}
				}
			}
			finally{
				httpResponse.disconnect();
			}
		}
		return resultUser;	
	}

	public static IDataSchema createDataSchema(Context context, Integer applicationId, String account, String className,  IDataSchemaCallback dataSchemaCallback) throws IOException{
		JsonReader jsonReader = Utils.loadAssetSchemaJson(context, className);

		IDataSchema dataSchema = null;
		if(jsonReader != null){
			dataSchema = new DataSchema(jsonReader);
			if(dataSchemaCallback != null){
				dataSchemaCallback.onDataSchemaCreated(account, applicationId, dataSchema);
			}
		}
		return dataSchema;
	}

}
