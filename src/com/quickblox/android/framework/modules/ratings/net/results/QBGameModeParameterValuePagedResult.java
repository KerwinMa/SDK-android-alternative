package com.quickblox.android.framework.modules.ratings.net.results;

import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameterValue;

import java.util.List;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 11:56
 */
public class QBGameModeParameterValuePagedResult extends Result {

    List<QBGameModeParameterValue> gameModeParameterValuesList;

    public void setGameModeParameterValuesList(List<QBGameModeParameterValue> gameModeParameterValuesList) {
        this.gameModeParameterValuesList = gameModeParameterValuesList;
    }

    public List<QBGameModeParameterValue> getGameModeParameterValuesList() {
        return gameModeParameterValuesList;
    }
}
