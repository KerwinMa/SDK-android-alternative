package com.quickblox.android.framework.modules.ratings.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.quickblox.android.framework.base.net.results.ArrayResult;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameter;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameterWrap;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 11:53
 */
public class QBGameModeParameterArrayResult extends ArrayResult<QBGameModeParameter> {

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {
            extractEntity();
        }
    }

    @Override
    protected void extractEntity() {
        String stringToParse = response.getRawBody();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Type type = new TypeToken<ArrayList<QBGameModeParameterWrap>>() {}.getType();

        ArrayList<QBGameModeParameterWrap> itemWraps = gson.fromJson(stringToParse, type);

        for (QBGameModeParameterWrap wrap : itemWraps) {
            items.add(wrap.getGameModeParameter());
        }
    }

    //

    public void setGameModeParameters(ArrayList<QBGameModeParameter> gameModeParametersList) {
        this.items = gameModeParametersList;
    }

    public ArrayList<QBGameModeParameter> getGameModeParameters() {
        return items;
    }
}