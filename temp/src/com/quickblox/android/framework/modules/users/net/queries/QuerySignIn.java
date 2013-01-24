package com.quickblox.android.framework.modules.users.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.users.definitions.Consts;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.modules.users.net.results.QBUserResult;

import java.util.Map;

/**
 * User: Andriy Dmitrenko
 * Date: 10.08.12
 * Time: 11:14
 */
public class QuerySignIn extends Query {

    private QBUser user;

    public QuerySignIn(QBUser user) {
        this.user = user;
        setOriginalObject(user);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.SIGNIN_ENDPOINT);
    }

    @Override
    public void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();

        putValue(parametersMap, Consts.LOGIN, user.getLogin());
        putValue(parametersMap, Consts.EMAIL, user.getEmail());
        putValue(parametersMap, Consts.PASSWORD, user.getPassword());
        putValue(parametersMap, Consts.PASSWORD, user.getPassword());
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    public Class getResultClass() {
        return QBUserResult.class;
    }

    //

    public QBUser getUser() {
        return user;
    }

    public void setUser(QBUser user) {
        this.user = user;
    }
}
