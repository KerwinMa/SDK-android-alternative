package com.quickblox.android.framework.base.net.server;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * User: Oleg Soroka
 * Date: 01.10.12
 * Time: 13:50
 */
public class HttpExecutor {

    public static HttpResponse execute(HttpRequestBase httpRequestBase) throws IOException {
        HttpClient httpClient = new DefaultHttpClient(); // TODO возможно, стоит использовать AndroidHttpClient??
        return httpClient.execute(httpRequestBase);
    }

}