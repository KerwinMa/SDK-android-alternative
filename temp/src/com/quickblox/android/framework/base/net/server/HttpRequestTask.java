package com.quickblox.android.framework.base.net.server;

import com.quickblox.android.framework.base.models.QBSettings;
import com.quickblox.android.framework.base.definitions.interfaces.RestRequestCallback;
import com.quickblox.android.framework.base.net.rest.RestResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;
import java.util.UUID;

/**
 * User: Oleg Soroka
 * Date: 01.10.12
 * Time: 13:45
 */
public class HttpRequestTask {

    public HttpRequestTask(RestRequestCallback callback, HttpRequestBase httpRequestBase, UUID uuid) {
        if (!QBSettings.getInstance().isSynchronous()) {
            // do asynchronous using AsyncTask
            HttpRequestAsynkTask httpRequestAsynkTask = new HttpRequestAsynkTask(callback, uuid);
            httpRequestAsynkTask.execute(httpRequestBase);
        } else {
            // do synchronous
            HttpResponse response = null;
            IOException ioException = null;
            try {
                response = HttpExecutor.execute(httpRequestBase);
            } catch (IOException e) {
                e.printStackTrace();
                ioException = e;
            }
            RestResponse restResponse = new RestResponse(response, uuid, ioException);
            if (callback != null) {
                callback.completedWithResponse(restResponse);
            }
        }
    }
}