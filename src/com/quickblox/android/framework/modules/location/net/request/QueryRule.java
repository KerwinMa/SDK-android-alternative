package com.quickblox.android.framework.modules.location.net.request;

import com.quickblox.android.framework.base.helpers.GenericQueryRule;

/**
 * User: Oleg Soroka
 * Date: 17.08.12
 * Time: 18:13
 */
public class QueryRule extends GenericQueryRule {

    // Pagination

    public static final String CURRENT_PAGE = "page";
    public static final String PER_PAGE = "per_page";

    // Filters
    public static final String CREATED_AT = "created_at";
    public static final String USER_ID = "user.id";
    public static final String USER_IDS = "user.ids";
    public static final String USER_NAME = "user.name";
    public static final String USER_EXTERNAL_IDS = "user.external_ids";

    // Diapasons
    public static final String MIN_CREATED_AT = "min_created_at";
    public static final String MAX_CREATED_AT = "max_created_at";
    public static final String GEO_RECT = "geo_rect";
    public static final String RADIUS = "radius";

    // Sort
    public static final String SORT_BY = "sort_by";
    public static final String SORT_BY_CREATED_AT = "created_at";
    public static final String SORT_BY_LATITUDE = "latitude";
    public static final String SORT_BY_LONGITUDE = "longitude";
    public static final String SORT_BY_DISTANCE = "distance";

    public static final String SORT_ASC = "sort_asc";
    public static final String CURRENT_POSITION = "current_position";

    public QueryRule(String paramName, Object value) {
        super(paramName, value);
    }
}