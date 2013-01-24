package com.quickblox.android.framework.base.net.communication;

import android.text.TextUtils;
import com.quickblox.android.framework.base.definitions.ConstsGlobal;
import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.definitions.exceptions.BaseServiceException;
import com.quickblox.android.framework.base.definitions.interfaces.Perform;
import com.quickblox.android.framework.base.definitions.interfaces.RestRequestCallback;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.models.QBSettings;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.rest.RestResponse;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.base.net.server.BaseService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * User: Igor Khomenko
 */
public class Query implements Perform, RestRequestCallback {

    // Этот класс перемещен в пакет communication вместе к классом Query,
    // чтобы позволить Query использовать protected методы RestResult (setQuery и setResponse),
    // но в тоже время иметь возможность скрыть эти методы от конечного пользователя,
    // который использует наследников RestResult.
    // В действительности же, этот класс должен находиться в пакете query.

    private Lo lo = new Lo(this);

    /*
     * Fields
     */
    private QBCallback callback;
    private Object context;
    private QBSettings qbSettings;
    private Object originalObject;

    /*
    * Properties
    */
    public Object getOriginalObject() {
        return originalObject;
    }

    public void setOriginalObject(Object originalObject) {
        this.originalObject = originalObject;
    }

    public QBCallback getCallback() {
        return callback;
    }

    public void setCallback(QBCallback callback) {
        this.callback = callback;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    /*
     * Methods (object)
     */
    public Query() {
        qbSettings = QBSettings.getInstance();
    }

    protected BaseService getService() {
        try {
            return BaseService.getBaseService();
        } catch (BaseServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Class getResultClass() {
        return Result.class;
    }

    protected void performInBgAsyncWithDelegate(QBCallback callback) {
        this.callback = callback;
        RestRequest restRequest = new RestRequest();
        setupRequest(restRequest);
        restRequest.asyncRequestWithCallback(this);
    }

    protected void setupRequest(RestRequest request) {
        setBody(request);
        setParams(request);
        setHeaders(request);
        setMethod(request);
        setUrl(request);
        setFiles(request);
        setShouldRedirect(request);

        setApiVersion(request);
        setFrameworkVersion(request);
        setAuthentication(request);
    }

    private void setApiVersion(RestRequest request) {
        String version = qbSettings.getApiVersion();
        if (version != null) {
            request.getHeaders().put(ConstsGlobal.HEADER_API_VERSION, version);
        }
    }

    private void setFrameworkVersion(RestRequest request) {
        String version = qbSettings.getVersionName();
        String versionValue = String.format("%s %s", ConstsGlobal.HEADER_FRAMEWORK_VERSION_VALUE_PREFIX, version);
        request.getHeaders().put(ConstsGlobal.HEADER_FRAMEWORK_VERSION, versionValue);
    }

    protected void setAuthentication(RestRequest request) {
        try {
            String token = BaseService.getBaseService().getToken();
            if (token != null) {
                request.getHeaders().put(ConstsGlobal.HEADER_TOKEN, token);
            }
        } catch (BaseServiceException e) {
            e.printStackTrace();
        }
    }

    protected String getUrl() {
        return BaseService.getServiceEndpointURL();
    }

    protected void setUrl(RestRequest request) {
        URL url = null;
        try {
            url = new URL(getUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        request.setUrl(url);
    }

    // Helps to build final query url, adding common url parts to specific part.
    protected String buildQueryUrl(Object... specificParts) {
        String specificPart = TextUtils.join("/", specificParts);
        StringBuilder urlBuilder = new StringBuilder(BaseService.getServiceEndpointURL() + "/");
        urlBuilder.append(specificPart).append(ConstsGlobal.REQUEST_FORMAT);
        return urlBuilder.toString();
    }

    protected void setBody(RestRequest request) {
        // Override in subclass
    }

    protected void setParams(RestRequest request) {
        // Override in subclass
    }

    protected void setHeaders(RestRequest request) {
        // Override in subclass
    }

    protected void setMethod(RestRequest request) {
        // Override in subclass
    }

    protected void setFiles(RestRequest request) {
        // Override in subclass
    }

    protected void setShouldRedirect(RestRequest request) {
        // Override in subclass
    }


    /*
     * Methods (interface)
     */
    @Override
    public void performAsyncWithCallback(QBCallback callback, Object context) {
        this.context = context;
        performAsyncWithCallback(callback);
    }

    @Override
    public void performAsyncWithCallback(QBCallback callback) {
        performInBgAsyncWithDelegate(callback);
    }

    @Override
    public void completedWithResponse(RestResponse response) {
        Result result = new Result();

        try {
            result = (Result) getResultClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        result.setQuery(this);
        result.setResponse(response);

        if (callback != null) {
            if (context != null) {
                callback.onComplete(result, context);
            } else {
                callback.onComplete(result);
            }
        }
    }

    protected void putValue(Map<String, String> parametersMap, String key, Object value) {
        if (value != null) {
            parametersMap.put(key, String.valueOf(value));
        }
    }
}