package com.quickblox.android.framework.base.net.server;

import android.os.AsyncTask;
import com.quickblox.android.framework.base.definitions.interfaces.RestRequestCallback;
import com.quickblox.android.framework.base.net.rest.RestResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;
import java.util.UUID;

/**
 * User: Oleg Soroka
 * Date: 01.10.12
 * Time: 13:16
 */
public class HttpRequestAsynkTask extends AsyncTask<HttpRequestBase, Void, HttpResponse> {

    private RestRequestCallback restRequestCallback;
    private UUID uuid;
    private IOException ioException;

    HttpRequestAsynkTask(RestRequestCallback restRequestCallback, UUID uuid) {
        this.restRequestCallback = restRequestCallback;
        this.uuid = uuid;
    }

    @Override
    protected HttpResponse doInBackground(HttpRequestBase... httpRequestBases) {
        HttpRequestBase httpRequestBase = httpRequestBases[0];
        HttpResponse response = null;
        try {
            response = HttpExecutor.execute(httpRequestBase);
        } catch (IOException e) {
            e.printStackTrace(); // TODO Handle fucking exception
            ioException = e;
        }
        return response;
    }

    @Override
    protected void onPostExecute(HttpResponse response) {
        RestResponse restResponse = new RestResponse(response, uuid, ioException);
        if (this.restRequestCallback != null) {
            this.restRequestCallback.completedWithResponse(restResponse);
        }
    }
}