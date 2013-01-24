package com.quickblox.android.framework.modules.users.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.users.definitions.Consts;
import com.quickblox.android.framework.modules.users.models.QBUser;

/**
 * QBUser: Daniel Goncharov
 * Date: 05.07.12
 * Time: 9:38
 */
public class QueryDeleteUser extends Query {

    public static final int BY_EXTERNAL_USER_ID = 0;

    private QBUser user;
    private Integer filterType;

    public QueryDeleteUser(QBUser user) {
        this.user = user;
    }

    public QueryDeleteUser(QBUser user, Integer filterType) {
        this.user = user;
        this.filterType = filterType;
    }

    @Override
    public String getUrl() {
        if (filterType != null && filterType == BY_EXTERNAL_USER_ID) {
            return buildQueryUrl(Consts.USERS_ENDPOINT, Consts.EXTERNAL_ID, user.getExternalId());
        } else {
            return buildQueryUrl(Consts.USERS_ENDPOINT, user.getId());
        }
    }

    @Override
    public Class getResultClass() {
        return Result.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.DELETE);
    }
}