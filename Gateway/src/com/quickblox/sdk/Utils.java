package com.quickblox.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.util.JsonReader;

class Utils {
	public static JsonReader loadAssetSchemaJson(Context context, String className) throws IOException{
		InputStream inputStream = context.getAssets().open("schema/" + className + ".schema");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, HTTP.UTF_8);
		return new JsonReader(inputStreamReader);
	}
}
