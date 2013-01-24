package com.quickblox.android.framework.modules.ratings.net.queries.score;

import com.quickblox.android.framework.base.net.queries.PagedQuery;
import com.quickblox.android.framework.base.net.requests.QBPagedRequestBuilder;
import com.quickblox.android.framework.modules.ratings.definitions.Consts;
import com.quickblox.android.framework.modules.ratings.models.QBGameMode;
import com.quickblox.android.framework.modules.ratings.net.results.QBScorePagedResult;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 11:15
 */
public class QueryGetTopScores extends PagedQuery {

    private QBGameMode gameMode;
    private int topScoresCount;
    private QBPagedRequestBuilder requestBuilder;

    public QueryGetTopScores(QBGameMode gameMode, int topScoresCount, QBPagedRequestBuilder requestBuilder) {
        this.gameMode = gameMode;
        this.topScoresCount = topScoresCount;
        this.requestBuilder = requestBuilder;
    }

    @Override
    public String getUrl() {
        return buildQueryUrl(Consts.GAME_MODES_ENDPOINT, gameMode.getId(), Consts.TOP + topScoresCount);
    }

    @Override
    public Class getResultClass() {
        return QBScorePagedResult.class;
    }
}