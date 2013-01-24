package com.quickblox.android.framework.modules.ratings.net.server;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.requests.QBPagedRequestBuilder;
import com.quickblox.android.framework.base.net.server.BaseService;
import com.quickblox.android.framework.modules.ratings.models.QBGameMode;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameter;
import com.quickblox.android.framework.modules.ratings.models.QBGameModeParameterValue;
import com.quickblox.android.framework.modules.ratings.models.QBScore;
import com.quickblox.android.framework.modules.ratings.net.queries.gameMode.*;
import com.quickblox.android.framework.modules.ratings.net.queries.gameModeParameter.*;
import com.quickblox.android.framework.modules.ratings.net.queries.gameModeParameterValue.*;
import com.quickblox.android.framework.modules.ratings.net.queries.score.*;
import com.quickblox.android.framework.modules.users.models.QBUser;

/**
 * User: Andriy Dmitrenko
 * Date: 21.08.12
 * Time: 10:35
 */
public class QBRatings extends BaseService {

    /* Game mode */

    // Create

    public static void createGameMode(QBGameMode gameMode, QBCallback callback) {
        createGameMode(gameMode, callback, null);
    }

    public static void createGameMode(QBGameMode gameMode, QBCallback callback, Object context) {
        QueryCreateGameMode query = new QueryCreateGameMode(gameMode);
        query.performAsyncWithCallback(callback, context);
    }

    // Update

    public static void updateGameMode(QBGameMode gameMode, QBCallback callback) {
        updateGameMode(gameMode, callback, null);
    }

    public static void updateGameMode(QBGameMode gameMode, QBCallback callback, Object context) {
        QueryUpdateGameMode query = new QueryUpdateGameMode(gameMode);
        query.performAsyncWithCallback(callback, context);
    }

    // Get

    public static void getGameMode(int gameModeId, QBCallback callback) {
        getGameMode(new QBGameMode(gameModeId), callback, null);
    }

    public static void getGameMode(int gameModeId, QBCallback callback, Object context) {
        getGameMode(new QBGameMode(gameModeId), callback, context);
    }

    public static void getGameMode(QBGameMode gameMode, QBCallback callback) {
        getGameMode(gameMode, callback, null);
    }

    public static void getGameMode(QBGameMode gameMode, QBCallback callback, Object context) {
        QueryGetGameMode query = new QueryGetGameMode(gameMode);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getGameModes(QBCallback callback) {
        getGameModes(callback, null);
    }

    public static void getGameModes(QBCallback callback, Object context) {
        QueryGetGameModes query = new QueryGetGameModes();
        query.performAsyncWithCallback(callback, context);
    }

    // Delete

    public static void deleteGameMode(int gameModeId, QBCallback callback) {
        deleteGameMode(new QBGameMode(gameModeId), callback, null);
    }

    public static void deleteGameMode(int gameModeId, QBCallback callback, Object context) {
        deleteGameMode(new QBGameMode(gameModeId), callback, context);
    }

    public static void deleteGameMode(QBGameMode gameMode, QBCallback callback) {
        deleteGameMode(gameMode, callback, null);
    }

    public static void deleteGameMode(QBGameMode gameMode, QBCallback callback, Object context) {
        QueryDeleteGameMode query = new QueryDeleteGameMode(gameMode);
        query.performAsyncWithCallback(callback, context);
    }

    /* Scores */

    // Create

    public static void createScore(QBScore score, QBCallback callback) {
        createScore(score, callback, null);
    }

    public static void createScore(QBScore score, QBCallback callback, Object context) {
        QueryCreateScore query = new QueryCreateScore(score);
        query.performAsyncWithCallback(callback, context);
    }

    // Update

    public static void updateScore(QBScore score, QBCallback callback) {
        updateScore(score, callback, null);
    }

    public static void updateScore(QBScore score, QBCallback callback, Object context) {
        QueryUpdateScore query = new QueryUpdateScore(score);
        query.performAsyncWithCallback(callback, context);
    }

    // Get

    public static void getScore(int id, QBCallback callback) {
        getScore(new QBScore(id), callback, null);
    }

    public static void getScore(int id, QBCallback callback, Object context) {
        getScore(new QBScore(id), callback, context);
    }

    public static void getScore(QBScore score, QBCallback callback) {
        getScore(score, callback, null);
    }

    public static void getScore(QBScore score, QBCallback callback, Object context) {
        QueryGetScore query = new QueryGetScore(score);
        query.performAsyncWithCallback(callback, context);
    }

    // TODO важно! добавить недостающие параметры запроса из таблички
    // TODO http://quickblox.com/developers/Ratings#Retrieve_Scores_for_user
    public static void getScoresByUser(QBUser user,
                                       QBPagedRequestBuilder requestBuilder,
                                       QBCallback callback) {
        getScoresByUser(user, requestBuilder, callback, null);
    }

    public static void getScoresByUser(QBUser user,
                                       QBPagedRequestBuilder requestBuilder,
                                       QBCallback callback,
                                       Object context) {
        QueryGetScores query = new QueryGetScores(user, requestBuilder);
        query.performAsyncWithCallback(callback, context);
    }


    // TODO важно! добавить недостающие параметры запроса из таблички
    public static void getTopScores(QBGameMode gameMode, int topScoresCount,
                                    QBPagedRequestBuilder requestBuilder,
                                    QBCallback callback) {
        getTopScores(gameMode, topScoresCount, requestBuilder, callback, null);
    }

    public static void getTopScores(QBGameMode gameMode, int topScoresCount,
                                    QBPagedRequestBuilder requestBuilder,
                                    QBCallback callback, Object context) {
        QueryGetTopScores query = new QueryGetTopScores(gameMode, topScoresCount, requestBuilder);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getAverageByGameMode(QBGameMode gameMode, QBCallback callback) {
        getAverageByGameMode(gameMode, callback, null);
    }

    public static void getAverageByGameMode(QBGameMode gameMode, QBCallback callback, Object context) {
        QueryGetAvarageByGameMode query = new QueryGetAvarageByGameMode(gameMode);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getAveragesByApp(QBCallback callback) {
        getAveragesByApp(callback, null);
    }

    public static void getAveragesByApp(QBCallback callback, Object context) {
        QueryGetAveragesByApp query = new QueryGetAveragesByApp();
        query.performAsyncWithCallback(callback, context);
    }

    // Delete

    public static void deleteScore(int scoreId, QBCallback callback) {
        deleteScore(new QBScore(scoreId), callback, null);
    }

    public static void deleteScore(int scoreId, QBCallback callback, Object context) {
        deleteScore(new QBScore(scoreId), callback, context);
    }

    public static void deleteScore(QBScore score, QBCallback callback) {
        deleteScore(score, callback, null);
    }

    public static void deleteScore(QBScore score, QBCallback callback, Object context) {
        QueryDeleteScore query = new QueryDeleteScore(score);
        query.performAsyncWithCallback(callback, context);
    }

    /* Game mode parameter */

    // Create

    public static void createParameter(QBGameModeParameter gameModeParameter, QBCallback callback) {
        createParameter(gameModeParameter, callback, null);
    }

    public static void createParameter(QBGameModeParameter gameModeParameter, QBCallback callback, Object context) {
        QueryCreateGameModeParameter query = new QueryCreateGameModeParameter(gameModeParameter);
        query.performAsyncWithCallback(callback, context);
    }

    // Update

    public static void updateParameter(QBGameModeParameter gameModeParameter, QBCallback callback) {
        updateParameter(gameModeParameter, callback, null);
    }

    public static void updateParameter(QBGameModeParameter gameModeParameter, QBCallback callback, Object context) {
        QueryUpdateGameModeParameter query = new QueryUpdateGameModeParameter(gameModeParameter);
        query.performAsyncWithCallback(callback, context);
    }

    // Get

    public static void getParameter(int id, QBCallback callback) {
        QBRatings.getParameter(id, callback, null);
    }

    public static void getParameter(int id, QBCallback callback, Object context) {
        getParameter(new QBGameModeParameter(id), callback, context);
    }

    public static void getParameter(QBGameModeParameter gameModeParameter, QBCallback callback) {
        getParameter(gameModeParameter, callback, null);
    }

    public static void getParameter(QBGameModeParameter gameModeParameter, QBCallback callback, Object context) {
        QueryGetGameModeParameter query = new QueryGetGameModeParameter(gameModeParameter);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getParametersByGameMode(QBGameMode gameMode, QBCallback callback) {
        getParametersByGameMode(gameMode, callback, null);
    }

    public static void getParametersByGameMode(int gameModeId, QBCallback callback) {
        getParametersByGameMode(new QBGameMode(gameModeId), callback, null);
    }

    public static void getParametersByGameMode(int gameModeId, QBCallback callback, Object context) {
        getParametersByGameMode(new QBGameMode(gameModeId), callback, context);
    }

    public static void getParametersByGameMode(QBGameMode gameMode, QBCallback callback, Object context) {
        QueryGetGameModeParameters query = new QueryGetGameModeParameters(gameMode);
        query.performAsyncWithCallback(callback, context);
    }

    // Delete

    public static void deleteParameter(int id, QBCallback callback) {
        deleteParameter(new QBGameModeParameter(id), callback, null);
    }

    public static void deleteParameter(QBGameModeParameter gameModeParameter, QBCallback callback) {
        deleteParameter(gameModeParameter, callback, null);
    }

    public static void deleteParameter(int id, QBCallback callback, Object context) {
        deleteParameter(new QBGameModeParameter(id), callback, context);
    }

    public static void deleteParameter(QBGameModeParameter gameModeParameter, QBCallback callback, Object context) {
        QueryDeleteGameModeParameter query = new QueryDeleteGameModeParameter(gameModeParameter);
        query.performAsyncWithCallback(callback, context);
    }

    // Game mode parameter value

    // Create

    public static void createValue(QBGameModeParameterValue gameModeParameterValue, QBCallback callback) {
        createValue(gameModeParameterValue, callback, null);
    }

    public static void createValue(QBGameModeParameterValue gameModeParameterValue, QBCallback callback, Object context) {
        QueryCreateGameModeParameterValue query = new QueryCreateGameModeParameterValue(gameModeParameterValue);
        query.performAsyncWithCallback(callback, context);
    }

    // Update

    public static void updateValue(QBGameModeParameterValue gameModeParameterValue, QBCallback callback) {
        updateValue(gameModeParameterValue, callback, null);
    }

    public static void updateValue(QBGameModeParameterValue gameModeParameterValue, QBCallback callback, Object context) {
        QueryUpdateGameModeParameterValue query = new QueryUpdateGameModeParameterValue(gameModeParameterValue);
        query.performAsyncWithCallback(callback, context);
    }

    // Get

    public static void getValue(int id, QBCallback callback) {
        getValue(new QBGameModeParameterValue(id), callback, null);
    }

    public static void getValue(QBGameModeParameterValue gameModeParameterValue, QBCallback callback) {
        getValue(gameModeParameterValue, callback, null);
    }

    public static void getValue(int id, QBCallback callback, Object context) {
        getValue(new QBGameModeParameterValue(id), callback, context);
    }

    public static void getValue(QBGameModeParameterValue gameModeParameterValue, QBCallback callback, Object context) {
        QueryGetGameModeParameterValue query = new QueryGetGameModeParameterValue(gameModeParameterValue);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getValuesByScore(int scoreId, QBCallback callback) {
        getValuesByScore(new QBScore(scoreId), callback, null);
    }

    public static void getValuesByScore(int scoreId, QBCallback callback, Object context) {
        getValuesByScore(new QBScore(scoreId), callback, context);
    }

    public static void getValuesByScore(QBScore score, QBCallback callback) {
        getValuesByScore(score, callback, null);
    }

    public static void getValuesByScore(QBScore score, QBCallback callback, Object context) {
        QueryGetGameModeParameterValues query = new QueryGetGameModeParameterValues(score);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getValuesByScoreAndParameter(int scoreId,
                                                    int gameModeParameterId,
                                                    QBCallback callback) {
        getValuesByScoreAndParameter(new QBScore(scoreId),
                new QBGameModeParameter(gameModeParameterId),
                callback, null);
    }

    public static void getValuesByScoreAndParameter(int scoreId,
                                                    int gameModeParameterId,
                                                    QBCallback callback,
                                                    Object context) {
        getValuesByScoreAndParameter(new QBScore(scoreId),
                new QBGameModeParameter(gameModeParameterId),
                callback, context);
    }

    public static void getValuesByScoreAndParameter(QBScore score,
                                                    QBGameModeParameter gameModeParameter,
                                                    QBCallback callback) {
        getValuesByScoreAndParameter(score, gameModeParameter, callback, null);
    }

    public static void getValuesByScoreAndParameter(QBScore score,
                                                    QBGameModeParameter gameModeParameter,
                                                    QBCallback callback,
                                                    Object context) {
        QueryGetGameModeParameterValueApi query = new QueryGetGameModeParameterValueApi(score, gameModeParameter);
        query.performAsyncWithCallback(callback, context);
    }

    // Delete

    public static void deleteValue(int id, QBCallback callback) {
        deleteValue(new QBGameModeParameterValue(id), callback, null);
    }

    public static void deleteValue(int id, QBCallback callback, Object context) {
        deleteValue(new QBGameModeParameterValue(id), callback, context);
    }

    public static void deleteValue(QBGameModeParameterValue gameModeParameterValue, QBCallback callback) {
        deleteValue(gameModeParameterValue, callback, null);
    }

    public static void deleteValue(QBGameModeParameterValue gameModeParameterValue, QBCallback callback, Object context) {
        QueryDeleteGameModeParameterValue query = new QueryDeleteGameModeParameterValue(gameModeParameterValue);
        query.performAsyncWithCallback(callback, context);
    }
}