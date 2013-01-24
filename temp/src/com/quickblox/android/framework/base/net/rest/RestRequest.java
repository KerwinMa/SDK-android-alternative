package com.quickblox.android.framework.base.net.rest;

import com.quickblox.android.framework.base.definitions.ConstsGlobal;
import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.definitions.interfaces.RestRequestCallback;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.helpers.StringUtils;
import com.quickblox.android.framework.base.helpers.ToStringHelper;
import com.quickblox.android.framework.base.models.QBSettings;
import com.quickblox.android.framework.base.net.server.HttpRequestTask;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * User: Igor Khomenko
 */
public class RestRequest {

    private Lo lo = new Lo(this);

    private UUID uuid = UUID.randomUUID();

    private MultipartEntity multipartEntity;
    private RestMethod method;
    private URL url;
    private Map<String, String> headers;
    private Map<String, String> parameters;
    private HttpEntity body;
    private RestRequestCallback callback;
    QBSettings qbSettings;

    public RestRequest() {
        qbSettings = QBSettings.getInstance();
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Map<String, String> getParameters() {
        if (parameters == null) {
            parameters = new LinkedHashMap<String, String>();
        }
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getHeaders() {
        if (headers == null) {
            headers = new LinkedHashMap<String, String>();
        }
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public RestMethod getMethod() {
        return method;
    }

    public void setMethod(RestMethod method) {
        this.method = method;
    }

    public UUID getUuid() {
        return uuid;
    }

    //

    public void asyncRequestWithCallback(RestRequestCallback restRequestCallback) {
        lo.g(this);
        this.callback = restRequestCallback;
        HttpRequestBase httpRequestBase = getHttpRequest();
        HttpRequestTask requestTask = new HttpRequestTask(this.callback, httpRequestBase, uuid);
    }

    // create http request
    private HttpRequestBase getHttpRequest() {
        HttpRequestBase httpRequest = null;

        if (method == null) {
            method = RestMethod.GET;
        }

        // set method
        switch (method) {
            case GET:
                httpRequest = new HttpGet(getFinalURL().toString());
                break;
            case DELETE:
                httpRequest = new HttpDelete(getFinalURL().toString());
                break;
            case POST:
                HttpPost postRequest = new HttpPost(getFinalURL().toString());
                if (multipartEntity != null) {
                    postRequest.setEntity(multipartEntity);
                } else {
                    postRequest.setEntity(getBody());
                }
                httpRequest = postRequest;
                break;
            case PUT:
                HttpPut putRequest = new HttpPut(getFinalURL().toString());
                putRequest.setEntity(getBody());
                httpRequest = putRequest;
                break;
            default:
                break;
        }

        // set headers
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpRequest.addHeader(key, headers.get(key));
            }
        }

        return httpRequest;
    }

    public URL getFinalURL() {
        if (method == RestMethod.GET || method == RestMethod.DELETE) {

            String urlWithParams = getUrlWithParamsString();

            try {
                return new URL(urlWithParams);
            } catch (MalformedURLException e) {
                return null;
            }
        } else {
            return getUrl();
        }
    }

    public HttpEntity getBody() {
        HttpEntity entity = null;

        if (body == null) {
            if ((method == RestMethod.POST || method == RestMethod.PUT) && parameters.size() > 0) {
                List<NameValuePair> bodyParams = new ArrayList<NameValuePair>();
                for (String key : parameters.keySet()) {
                    bodyParams.add(new BasicNameValuePair(key, parameters.get(key)));
                }
                try {
                    entity = new UrlEncodedFormEntity(bodyParams, ConstsGlobal.DEFAULT_ENCODING);
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            entity = body;
        }

        return entity;
    }

    public String getParamsOnlyString() {
        return getEncodedParamsOnlyString(true);
    }

    public String getParamsOnlyStringNotEncoded() {
        return getEncodedParamsOnlyString(false);
    }

    // Returns params string only, e.g.
    // application_id=774&auth_key=aY7WwSRmu2-GbfA&nonce=1451135156
    private String getEncodedParamsOnlyString(boolean encoded) {
        StringBuilder paramsOnly = new StringBuilder();

        if (parameters.size() > 0) {
            for (String key : parameters.keySet()) {
                String value = parameters.get(key);
                if (value != null) {
                    String encodedValue = encoded ? URLEncoder.encode(value) : value;
                    paramsOnly.append(String.format("%s=%s&", key, encodedValue));
                }
            }

            // remove last '&'
            paramsOnly.deleteCharAt(paramsOnly.length() - 1);
        }
        return paramsOnly.toString();
    }

    // Returns full url, e.g.
    // https://api.quickblox.com/session.json?application_id=774&auth_key=aY7WwSRmu2-GbfA&nonce=1451135156
    public String getUrlWithParamsString() {
        StringBuilder urlWithParams = new StringBuilder(getUrl().toString());
        if (!StringUtils.isEmpty(getParamsOnlyString())) {
            urlWithParams.append("?");
        }

        return urlWithParams.append(getParamsOnlyString()).toString();
    }

    @Override
    public String toString() {
        String tab = "    ";

        return String.format("=========================================================\n" +
                "=== REQUEST ==== %s ===\nREQUEST\n    %s " +
                "%s\nHEADERS\n%s\nPARAMETERS\n%s\nINLINE\n    %s %s",
                String.valueOf(uuid),
                method,
                getUrl().toString(),
                ToStringHelper.toString(getHeaders(), tab),
                ToStringHelper.toString(getParameters(), tab),
                method,
                getUrlWithParamsString()
        );
    }

    //

    public void setMultipartEntity(MultipartEntity multipartEntity) {
        this.multipartEntity = multipartEntity;
    }
}