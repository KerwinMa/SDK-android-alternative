package com.quickblox.android.framework.modules.ratings.net.results;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.quickblox.android.framework.base.net.results.ArrayResult;
import com.quickblox.android.framework.modules.ratings.models.QBGameMode;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeWrap;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 11:02
 */
public class QBGameModeArrayResult extends ArrayResult<QBGameMode> {

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

        Type type = new TypeToken<ArrayList<QBGameModeWrap>>() {}.getType();

        ArrayList<QBGameModeWrap> wraps = gson.fromJson(stringToParse, type);

        for (QBGameModeWrap wrap : wraps) {
            items.add(wrap.getGameMode());
        }
    }

    //

    public void setGameModes(ArrayList<QBGameMode> gameModes) {
        this.items = gameModes;
    }

    public ArrayList<QBGameMode> getGameModes() {
        return items;
    }
}