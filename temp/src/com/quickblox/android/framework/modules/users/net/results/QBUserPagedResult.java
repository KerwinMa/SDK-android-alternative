package com.quickblox.android.framework.modules.users.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickblox.android.framework.base.helpers.StringifyArrayList;
import com.quickblox.android.framework.base.net.results.PagedResult;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.modules.users.models.QBUserPaged;
import com.quickblox.android.framework.modules.users.models.QBUserWrap;
import com.quickblox.android.framework.modules.users.models.deserializers.QBStringifyArrayListDeserializer;

import java.util.ArrayList;

/**
 * @author: Daniel Goncharov
 * Date: 12.07.12
 * Time: 10:43
 */

/*
  The model for holding paged results of users.
 */
public class QBUserPagedResult extends PagedResult<QBUser> {

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();
        }
    }

    public void extractEntity() {
        String stringToParse = response.getRawBody();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(StringifyArrayList.class, new QBStringifyArrayListDeserializer());
        Gson gson = gsonBuilder.create();

        QBUserPaged userPaged = gson.fromJson(stringToParse, QBUserPaged.class);

        currentPage = userPaged.getCurrentPage();
        perPage = userPaged.getPerPage();
        totalEntries = userPaged.getTotalEntries();

        for (QBUserWrap item : userPaged.getItems()) {
            items.add(item.getUser());
        }
    }

    //

    public ArrayList<QBUser> getUsers() {
        return items;
    }

    public void setUsers(ArrayList<QBUser> users) {
        this.items = users;
    }
}