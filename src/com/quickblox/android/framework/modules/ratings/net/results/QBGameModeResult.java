package com.quickblox.android.framework.modules.ratings.net.results;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.ratings.models.QBGameMode;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeWrap;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 11:02
 */
public class QBGameModeResult extends Result {

    private Lo lo = new Lo(this);

    private QBGameMode gameMode;


    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {

            extractEntity();

            // Copy fields
            QBGameMode originalObject = (QBGameMode) getQuery().getOriginalObject();
            gameMode.copyFieldsTo(originalObject);

            lo.g(LOG_MSG_OBJ_PARSED + gameMode);
        }
    }

    public void extractEntity() {
        String stringToParse = response.getRawBody();

        Gson gson = new Gson();
        QBGameModeWrap gameModeWrap = gson.fromJson(stringToParse, QBGameModeWrap.class);
        gameMode = gameModeWrap.getGameMode();
    }

    //

    public QBGameMode getGameMode() {
        return gameMode;
    }
}