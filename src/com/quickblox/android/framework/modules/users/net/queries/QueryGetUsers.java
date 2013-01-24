package com.quickblox.android.framework.modules.users.net.queries;

import android.text.TextUtils;
import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.requests.QBPagedRequestBuilder;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.users.definitions.Consts;
import com.quickblox.android.framework.modules.users.net.results.QBUserPagedResult;

import java.util.Collection;
import java.util.Map;

/**
 * User: Oleg Soroka
 * Date: 27.09.12
 * Time: 16:11
 */
public class QueryGetUsers extends Query {

    private QBPagedRequestBuilder requestBuilder;
    private String fullName;
    private Collection<String> tags;

    public QueryGetUsers(QBPagedRequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    public QueryGetUsers(Collection<String> tags, QBPagedRequestBuilder requestBuilder) {
        this.tags = tags;
        this.requestBuilder = requestBuilder;
    }

    public QueryGetUsers(String fullName, QBPagedRequestBuilder requestBuilder) {
        this.fullName = fullName;
        this.requestBuilder = requestBuilder;
    }

    @Override
    public String getUrl() {
        if (fullName != null) {
            return buildQueryUrl(Consts.USERS_ENDPOINT, Consts.BY_FULL_NAME);
        } else if (tags != null) {
            return buildQueryUrl(Consts.USERS_ENDPOINT, Consts.BY_TAGS);
        } else {
            return buildQueryUrl(Consts.USERS_ENDPOINT);
        }
    }

    @Override
    public Class getResultClass() {
        return QBUserPagedResult.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    @Override
    protected void setParams(RestRequest request) {
        super.setParams(request);
        Map<String, String> parametersMap = request.getParameters();

        if (requestBuilder != null) {
            requestBuilder.fillParametersMap(parametersMap);
        }

        String tagsString = null;

        if (tags != null && !tags.isEmpty()) {
            tagsString = TextUtils.join(",", tags);
        }

        putValue(parametersMap, Consts.TAGS, tagsString);
        putValue(parametersMap, Consts.FULL_NAME, fullName);
    }

    //

    public QBPagedRequestBuilder getRequestBuilder() {
        return requestBuilder;
    }

    public void setRequestBuilder(QBPagedRequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}