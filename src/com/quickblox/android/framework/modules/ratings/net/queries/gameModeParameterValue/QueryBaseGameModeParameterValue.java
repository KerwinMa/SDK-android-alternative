package com.quickblox.android.framework.modules.ratings.net.queries.gameModeParameterValue;

import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameterValue;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;

import java.util.Map;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 10:08
 */
public class QueryBaseGameModeParameterValue extends Query {

    QBGameModeParameterValue gameModeParameterValue;

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        if(gameModeParameterValue.getGameModeParameterId() != 0 )
            putValue(parametersMap, Consts.GAME_MODE_PARAMETER_VALUE_GAME_MODE_PARAMETER_ID, gameModeParameterValue.getGameModeParameterId());
        if(gameModeParameterValue.getScoreId() !=0)
            putValue(parametersMap, Consts.GAME_MODE_PARAMETER_VALUE_SCORE_ID, gameModeParameterValue.getScoreId());
        putValue(parametersMap, Consts.GAME_MODE_PARAMETER_VALUE_VALUE, gameModeParameterValue.getValue());
    }


    public QueryBaseGameModeParameterValue(QBGameModeParameterValue gameModeParameterValue) {
        this.gameModeParameterValue = gameModeParameterValue;
        setOriginalObject(gameModeParameterValue);
    }

    public QBGameModeParameterValue getGameModeParameterValue() {
        return gameModeParameterValue;
    }

    public void setGameModeParameterValue(QBGameModeParameterValue gameModeParameterValue) {
        this.gameModeParameterValue = gameModeParameterValue;
    }
}
