package com.quickblox.android.framework.modules.ratings.net.results;

import com.google.gson.Gson;
import com.quickblox.android.framework.base.helpers.Lo;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.ratings.models.QBScore;
import com.quickblox.android.framework.modules.ratings.models.QBScoreWrap;

/**
 * User: Andriy Dmitrenko
 * Date: 23.08.12
 * Time: 11:20
 */
public class QBScoreResult extends Result {

    private Lo lo = new Lo(this);

    private QBScore score;

    @Override
    protected void processResponse() {
        super.processResponse();

        if (isDeserializable()) {

            extractEntity();

            // Copy fields
            QBScore originalObject = (QBScore) getQuery().getOriginalObject();
            score.copyFieldsTo(originalObject);

            lo.g(LOG_MSG_OBJ_PARSED + score);
        }
    }

    public void extractEntity() {
        String stringToParse = response.getRawBody();

        Gson gson = new Gson();
        QBScoreWrap scoreWrap = gson.fromJson(stringToParse, QBScoreWrap.class);

        score = scoreWrap.getScore();
    }

    //

    public QBScore getScore() {
        return score;
    }
}