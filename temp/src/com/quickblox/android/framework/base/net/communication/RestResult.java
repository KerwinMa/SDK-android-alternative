/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickblox.android.framework.base.net.communication;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.definitions.ConstsGlobal;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.models.QBErrorPlain;
import com.quickblox.android.framework.base.models.QBErrorWithArray;
import com.quickblox.android.framework.base.models.QBErrorWithBase;
import com.quickblox.android.framework.base.models.QBErrorWithCode;
import com.quickblox.android.framework.base.net.rest.RestResponse;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Igor Khomenko
 */
public class RestResult {

    // Этот класс перемещен в пакет communication вместе к классом Query,
    // чтобы позволить Query использовать protected методы RestResult (setQuery и setResponse),
    // но в тоже время иметь возможность скрыть эти методы от конечного пользователя,
    // который использует наследников RestResult.
    // В действительности же, этот класс должен находиться в пакете results.

    Lo lo = new Lo(this);

    protected RestResponse response;
    private List<String> errors = new ArrayList<String>();
    private Query query;

    public RestResult(RestResponse response) {
        this.response = response;
    }

    public RestResult() {
        super();
        errors = new ArrayList<String>();
    }

    protected RestResponse getResponse() {
        return response;
    }

    protected void setResponse(RestResponse response) {
        this.response = response;

        if (response.getIOException() != null) {
            errors.add(ConstsGlobal.EXCEPTION_CONNECTION_FAILED);
        }

        processResponse();
    }

    protected Query getQuery() {
        return query;
    }

    protected void setQuery(Query query) {
        this.query = query;
    }

    protected void processResponse() {
        String responseBody = getRawBody();
        try {
            // If JSON root is not empty and root is not array
            if (!isEmpty() && !isArray()) {
                JSONObject jResponse = new JSONObject(responseBody);
                if (jResponse.has("errors")) {
                    Object jRawErrors = jResponse.get("errors");

                    // Parses string like this
                    // "{'errors':{'status':['is too long (maximum is 1000 characters)']}}"
                    if (jRawErrors instanceof JSONObject) {
                        JSONObject jErrors = (JSONObject) jRawErrors;
                        if (jErrors.length() > 0) {
                            JSONArray fields = jErrors.names();
                            for (int i = 0; i < fields.length(); i++) {
                                String fieldName = fields.get(i).toString();
                                JSONArray values = jErrors.getJSONArray(fieldName);
                                for (int j = 0; j < values.length(); j++) {
                                    String value = values.getString(j);
                                    errors.add(0, fieldName + " " + value);
                                }
                            }
                        }
                    }

                    if (jRawErrors instanceof JSONArray) {
                        // Parses string like this
                        // {'errors':['This geo datum was not found or it is bound to a place']}
                        Gson gson = new Gson();
                        QBErrorWithArray errorWithArray = gson.fromJson(responseBody, QBErrorWithArray.class);
                        for (String e : errorWithArray.getErrors()) {
                            errors.add(0, e);
                        }
                    }
                } else if (jResponse.has("code")) {
                    // Parses strings like
                    // '{"code":null,"message":"invalid byte sequence in UTF-8"}'
                    Gson gson = new Gson();
                    QBErrorWithCode errorWithCode = gson.fromJson(responseBody, QBErrorWithCode.class);
                    errors.add(0, errorWithCode.getMessage());
                } else if (jResponse.has("error")) {
                    // Parses strings like
                    // '{"error":"No one can receive the message"}'
                    Gson gson = new Gson();
                    QBErrorPlain errorPlain = gson.fromJson(responseBody, QBErrorPlain.class);
                    errors.add(0, errorPlain.getMessage());
                } else if (jResponse.has("base")) {
                    // Parses strings like
                    // '{"base":["No one can receive the message for GCM (Android Push)"]}'
                    Gson gson = new Gson();
                    QBErrorWithBase errorBase = gson.fromJson(responseBody, QBErrorWithBase.class);
                    for (String e : errorBase.getBase()) {
                        errors.add(0, e);
                    }
                } else {
                    lo.g("No errors. All right.");
                }
            } else {
                if (isArray()) {
                    lo.g("Response is array.");
                } else {
                    lo.g("Response is empty.");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (!errors.isEmpty()) {
            lo.g("Request has been completed with errors: " + errors);
        }
    }

    protected boolean isEmpty() {
        if (getRawBody() != null) {
            return getRawBody().trim().length() == 0;
        } else {
            return true;
        }
    }

    boolean isArray() {
        if (!isEmpty()) {
            return getRawBody().charAt(0) == '[';
        } else {
            return false;
        }
    }

    boolean isObject() {
        if (!isEmpty()) {
            return getRawBody().charAt(0) == '{';
        } else {
            return false;
        }
    }

    protected boolean notFoundError() {
        if (response != null) {
            return getStatusCode() == HttpStatus.SC_NOT_FOUND;
        }
        return false;
    }

    public int getStatusCode() {
        if (response != null) {
            return response.getStatusCode();
        } else {
            return 0;
        }
    }

    public List<String> getErrors() {
        return errors;
    }

    private void addError(String error) {
        errors.add(error);
    }

    protected void extractEntity() {
        // Override in child
    }

    //

    public String getRawBody() {
        if (response != null) {
            return response.getRawBody();
        } else {
            return ConstsGlobal.EMPTY_STRING;
        }
    }


    // TODO написать стрингер для
//    @Override
//    public String toString() {
//        return "RestResult{" +
//                "isEmpty=" + isEmpty() +
//                ", notFoundError=" + notFoundError() +
//                ", statusCode=" + getStatusCode() +
//                ", errors=" + getStatusCode() +
//                '}';
//    }

}