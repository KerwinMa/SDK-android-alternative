package com.quickblox.android.framework.modules.users.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.users.definitions.Consts;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.modules.users.net.results.QBUserResult;

import java.util.Map;

/**
 * User:Andriy Dmitrenko
 * Date: 10.08.12
 * Time: 9:17
 */
public class QueryGetUser extends Query {

    private QBUser user;
    private Integer filterType;

    public static final int BY_LOGIN = 0;
    public static final int BY_EMAIL = 1;
    public static final int BY_FACEBOOK_ID = 2;
    public static final int BY_TWITTER_ID = 3;
    public static final int BY_EXTERNAL_USER_ID = 4;

    private static String[] filters = {
            Consts.FILTER_LOGIN,
            Consts.FILTER_EMAIL,
            Consts.FILTER_FACEBOOK_ID,
            Consts.FILTER_TWITTER_ID,
            Consts.EXTERNAL_ID
    };

    public QueryGetUser(QBUser user) {
        this.user = user;
        setOriginalObject(user);
    }

    public QueryGetUser(QBUser user, int filterType) {
        this(user);
        this.filterType = filterType;
    }

    @Override
    protected void setParams(RestRequest request) {
        if (filterType != null && filterType != BY_EXTERNAL_USER_ID) {
            String value;

            switch (filterType) {
                case BY_LOGIN:
                    value = user.getLogin();
                    break;
                case BY_EMAIL:
                    value = user.getEmail();
                    break;
                case BY_FACEBOOK_ID:
                    value = user.getFacebookId().toString();
                    break;
                case BY_TWITTER_ID:
                    value = user.getTwitterId().toString();
                    break;
                default:
                    value = null;
                    break;
            }

            Map<String, String> parametersMap = request.getParameters();
            putValue(parametersMap, filters[filterType], value);
        }
    }

    @Override
    protected String getUrl() {
        if (filterType != null) {
            if (filterType != BY_EXTERNAL_USER_ID) {
                String filterString = Consts.FILTER_PREFIX + filters[filterType];
                return buildQueryUrl(Consts.USERS_ENDPOINT, filterString);
            } else {
                return buildQueryUrl(Consts.USERS_ENDPOINT, Consts.EXTERNAL_ID, user.getExternalId());
            }
        } else {
            return buildQueryUrl(Consts.USERS_ENDPOINT, user.getId());
        }
    }

    @Override
    public Class getResultClass() {
        return QBUserResult.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.GET);
    }
}