package com.quickblox.android.framework.modules.ratings.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.quickblox.android.framework.base.net.results.PagedResult;
import com.quickblox.android.framework.modules.ratings.models.QBScorePaged;
import com.quickblox.android.framework.modules.ratings.models.QBScoreWrap;
import com.quickblox.android.framework.modules.users.models.QBUser;

import java.util.ArrayList;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 11:20
 */
public class QBScorePagedResult extends PagedResult {

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
        Gson gson = gsonBuilder.create();

        QBScorePaged userPaged = gson.fromJson(stringToParse, QBScorePaged.class);

        currentPage = userPaged.getCurrentPage();
        perPage = userPaged.getPerPage();
        totalEntries = userPaged.getTotalEntries();

        for (QBScoreWrap item : userPaged.getItems()) {
            items.add(item.getScore());
        }
    }

    //

    public ArrayList<QBUser> getScores() {
        return items;
    }

    public void setScores(ArrayList<QBUser> users) {
        this.items = users;
    }
}