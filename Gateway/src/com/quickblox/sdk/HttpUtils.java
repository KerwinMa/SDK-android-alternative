package com.quickblox.sdk;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.quickblox.gateway.BuildConfig;



public class HttpUtils {

	public static final String TAG = "HttpUtils";

	public interface Method{
		public static final String OPTIONS = "OPTIONS";
		public static final String GET = "GET";
		public static final String HEAD = "HEAD";
		public static final String POST = "POST";
		public static final String PUT = "PUT";
		public static final String DELETE = "DELETE";
		public static final String TRACE = "TRACE";
		public static final String CONNECT = "CONNECT";
	}

	static String encodeUrl(Bundle parameters) {
		if (parameters == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String key : parameters.keySet()) {
			if (first)
				first = false;
			else
				sb.append("&");
			sb.append(URLEncoder.encode(key) + "=" + URLEncoder.encode(parameters.getString(key)));
		}
		return sb.toString();
	}
	static final int IO_BUFFER_SIZE = 4 * 1024;  

	static void copyStreams(InputStream in, OutputStream out) throws IOException {  
		byte[] b = new byte[IO_BUFFER_SIZE];  
		int read;  
		while ((read = in.read(b)) != -1) {  
			out.write(b, 0, read);  
		}  
	} 
	public static HttpURLConnection openUrl(String url, 
			String method, 
			Bundle params,
			Map<String,List<String>> headers,
			String messageBody) throws MalformedURLException, IOException {
		
		if (method.equals( Method.GET)) {
			url = url + "?" + encodeUrl(params);
		}

		if(BuildConfig.DEBUG) {
			Log.d(TAG, method + " URL: " + url);
		}

		HttpURLConnection connection = null; 
		OutputStream outputStream = null;

		//		try {
		if(url.startsWith("https://")) {
			connection = (HttpsURLConnection) new URL(url).openConnection();
		} else {
			connection = (HttpURLConnection) new URL(url).openConnection();
		}
		connection.setRequestMethod(method);
		connection.setUseCaches(false);


		if(headers != null){
			for(String headerKey : headers.keySet()){
				List<String> values = headers.get(headerKey);
				for(String headerValue : values){
					connection.addRequestProperty(headerKey, headerValue);
				}
			}
		}

		if (!method.equals( Method.GET)){
			if(!TextUtils.isEmpty(messageBody)){
				byte[] dataArray = messageBody.getBytes();
				connection.setFixedLengthStreamingMode(dataArray.length);
				connection.setDoOutput(true);
				outputStream = new BufferedOutputStream(connection.getOutputStream());
				outputStream.write(dataArray);
				outputStream.flush();
			}
		}

		connection.connect();

		if(BuildConfig.DEBUG){
			Log.d(TAG, "Response Code : " + connection.getResponseCode());
			Log.d(TAG, "Response Message : " + connection.getResponseMessage());
			//	Log.d(TAG, "Content : " + read(inputStream));

			if(connection.getHeaderFields() != null){
				for(String headerKey : connection.getHeaderFields().keySet()){
					List<String> values = connection.getHeaderFields().get(headerKey);
					for(String headerValue : values){
						if(BuildConfig.DEBUG){
							Log.d(TAG,String.format("Reponse Header : %s = %s" , headerKey, headerValue));
						}
					}
				}
			}


		}

		return connection;
	}

//	private static String read(InputStream in) throws IOException {
//		try{
//			StringBuilder sb = new StringBuilder();
//			BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
//			for (String line = r.readLine(); line != null; line = r.readLine()) {
//				sb.append(line);
//			}
//			return sb.toString();
//		} catch (IOException e){
//			return null;
//		}
//	}

}
