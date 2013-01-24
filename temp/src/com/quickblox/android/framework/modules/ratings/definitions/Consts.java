package com.quickblox.android.framework.modules.ratings.definitions;

/**
 * User: Andriy Dmitrenko
 * Date: 20.08.12
 * Time: 17:44
 */
public class Consts {

    public static final String GAME_MODE_ = "game_mode";
    public static final String SCORE_ = "score";
    public static final String GAME_MODE_ID_ = "game_mode_id";
    public static final String GAME_MODE_PARAMETER_ = "game_mode_parameter";
    public static final String GAME_MODE_PARAMETER_VALUE_ = "game_mode_parameter_value";

    //Game Mode
    public static final String GAME_MODES_ENDPOINT = "gamemodes";
    public static final String TITLE = "gamemode[title]";
    public static final String APPLICATION = "application";
    public static final String VALUE = "value";

    //Scores
    public static final String SCORES_ENDPOINT = "scores";
    public static final String SCORE_GAME_MODE_ID = "score[game_mode_id]";
    public static final String SCORE_VALUE = "score[value]";
    public static final String TOP = "/top.";
    public static final String PAGE = "page";
    public static final String PER_PAGE = "per_page";
    public static final String SORT_BY = "sort_by";
    public static final String SORT_ASC = "sort_asc";
    public static final String USERS = "/users/";
    public static final String AVERAGE_ENDPOINT = "average";
    public static final String AVERAGES_ENDPOINT = "averages";

    //Game mode parameter
    public static final String GAME_MODE_PARAMETERS_ENDPOINT = "gamemodeparameters";
    public static final String GAME_MODE_PARAMETER_GAME_MODE_ID = "gamemodeparameter[game_mode_id]";
    public static final String GAME_MODE_PARAMETER_NAME = "gamemodeparameter[name]";

    //Game mode parameter value
    public static final String GAME_MODE_PARAMETER_VALUES_ENDPOINT = "gamemodeparametervalues";
    public static final String GAME_MODE_PARAMETER_VALUE_GAME_MODE_PARAMETER_ID = "gamemodeparametervalue[game_mode_parameter_id]";
    public static final String GAME_MODE_PARAMETER_VALUE_SCORE_ID = "gamemodeparametervalue[score_id]";
    public static final String GAME_MODE_PARAMETER_VALUE_VALUE = "gamemodeparametervalue[value]";
}
