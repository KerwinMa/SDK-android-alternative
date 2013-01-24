package com.quickblox.android.framework.modules.users.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.users.definitions.Consts;
import com.quickblox.android.framework.modules.users.models.QBUser;

import java.util.Map;

/**
 * User: Andriy Dmitrenko
 * Date: 09.08.12
 * Time: 15:11
 */
public class QueryResetPassword extends Query {

    private QBUser user;

    public QueryResetPassword(QBUser user) {
        this.user = user;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }

    public String getUrl() {
        return buildQueryUrl(Consts.USERS_ENDPOINT, Consts.PASSWORD_RESET);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.EMAIL, user.getEmail());
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }
}