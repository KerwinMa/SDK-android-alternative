package com.quickblox.android.framework.modules.users.net.queries;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.modules.users.definitions.Consts;
import com.quickblox.android.framework.modules.users.net.results.QBUserResult;

import java.util.Map;

/**
 * QBUser: Daniel Goncharov
 * Date: 04.07.12
 * Time: 14:46
 */
public class QueryCreateUser extends Query {

    protected QBUser user;

    public QueryCreateUser(QBUser user) {
        this.user = user;
        setOriginalObject(user);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.USERS_ENDPOINT);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();

        // obligatory parameters
        putValue(parametersMap, Consts.USER_LOGIN, user.getLogin());
        putValue(parametersMap, Consts.USER_PASSWORD, user.getPassword());

        // optional parameters
        putValue(parametersMap, Consts.USER_BLOB_ID, user.getFileId());
        putValue(parametersMap, Consts.USER_MAIL, user.getEmail());
        putValue(parametersMap, Consts.USER_EXTERNAL_ID, user.getExternalId());
        putValue(parametersMap, Consts.USER_FACEBOOK_ID, user.getFacebookId());
        putValue(parametersMap, Consts.USER_TWITTER_ID, user.getTwitterId());
        putValue(parametersMap, Consts.USER_FULL_NAME, user.getFullName());
        putValue(parametersMap, Consts.USER_PHONE, user.getPhone());
        putValue(parametersMap, Consts.USER_WEBSITE, user.getWebsite());

        String tags = user.getTags().getItemsAsStringOrNull();
        putValue(parametersMap, Consts.USER_TAG_LIST, tags);
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.POST);
    }

    @Override
    public Class getResultClass() {
        return QBUserResult.class;
    }
}