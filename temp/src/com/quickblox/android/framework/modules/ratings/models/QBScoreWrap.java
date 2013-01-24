package com.quickblox.android.framework.modules.ratings.models;

import com.google.gson.annotations.SerializedName;

/**
 * User: Oleg Soroka
 * Date: 08.10.12
 * Time: 16:42
 */
public class QBScoreWrap {

    @SerializedName("score")
    private QBScore score;

    public QBScoreWrap() {
    }

    public QBScore getScore() {
        return score;
    }

    public void setScore(QBScore score) {
        this.score = score;
    }
}