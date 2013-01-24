package com.quickblox.android.framework.modules.ratings.net.queries.gameMode;

import com.quickblox.android.framework.base.definitions.RestMethod;
import com.quickblox.android.framework.base.net.rest.RestRequest;
import com.quickblox.android.framework.modules.ratings.models.QBGameMode;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.net.results.QBGameModeResult;

import java.util.Map;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 9:52
 */
public class QueryUpdateGameMode extends QueryBaseGameMode {

    public QueryUpdateGameMode(QBGameMode gameMode) {
        super(gameMode);
    }

    @Override
    protected void setParams(RestRequest request) {
        Map<String, String> parametersMap = request.getParameters();
        putValue(parametersMap, Consts.TITLE, gameMode.getTitle());
    }

    @Override
    protected void setMethod(RestRequest request) {
        request.setMethod(RestMethod.PUT);
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.GAME_MODES_ENDPOINT, gameMode.getId());
    }

    @Override
    public Class getResultClass() {
        return QBGameModeResult.class;
    }
}