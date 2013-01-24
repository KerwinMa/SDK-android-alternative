package com.quickblox.android.framework.modules.ratings.net.queries.score;

import com.quickblox.android.framework.base.net.communication.Query;
import com.quickblox.android.framework.modules.ratings.models.QBScore;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 10:38
 */
public class QueryBaseScore extends Query {

    protected QBScore score;

    public QueryBaseScore(QBScore score) {
        this.score = score;
        setOriginalObject(score);
    }

    public QBScore getScore() {
        return score;
    }

    public void setScore(QBScore score) {
        this.score = score;
    }
}
