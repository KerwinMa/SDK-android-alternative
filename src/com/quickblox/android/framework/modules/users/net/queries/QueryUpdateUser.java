package com.quickblox.android.framework.modules.users.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.modules.users.definitions.Consts;
import com.quickblox.android.framework.modules.users.net.results.QBUserResult;

import java.util.Map;

/**
 * @author: Daniel Goncharov
 * Date: 13.07.12
 * Time: 12:37
 */
public class QueryUpdateUser extends Query {

    QBUser user;

    public QueryUpdateUser(QBUser user) {
        this.user = user;
        setOriginalObject(user);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.USERS_ENDPOINT, user.getId());
    }

    @Override
    public Class getResultClass() {
        return QBUserResult.class;
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.PUT);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();

        putValue(parametersMap, Consts.USER_LOGIN, user.getLogin()); // required
        putValue(parametersMap, Consts.USER_BLOB_ID, user.getFileId());
        putValue(parametersMap, Consts.USER_MAIL, user.getEmail());
        putValue(parametersMap, Consts.USER_EXTERNAL_ID, user.getExternalId());
        putValue(parametersMap, Consts.USER_FACEBOOK_ID, user.getFacebookId());
        putValue(parametersMap, Consts.USER_TWITTER_ID, user.getTwitterId());
        putValue(parametersMap, Consts.USER_FULL_NAME, user.getFullName());
        putValue(parametersMap, Consts.USER_PHONE, user.getPhone());
        putValue(parametersMap, Consts.USER_WEBSITE, user.getWebsite());
        putValue(parametersMap, Consts.USER_TAG_LIST, user.getTags());
    }
}