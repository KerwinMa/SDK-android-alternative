package com.quickblox.android.framework.modules.location.net.request;

import com.quickblox.android.framework.base.net.requests.QBPagedRequestBuilder;
import com.quickblox.android.framework.modules.location.helpers.LocationHelper;

/**
 * User: Oleg Soroka
 * Date: 17.08.12
 * Time: 18:04
 */
public class QBLocationRequestBuilder extends QBPagedRequestBuilder {

    // Filters http://quickblox.com/developers/Location#Filters

    public QBLocationRequestBuilder setCreatedAt(int timestamp) {
        addRule(QueryRule.CREATED_AT, timestamp);
        return this;
    }

    public QBLocationRequestBuilder setUserId(long userId) {
        addRule(QueryRule.USER_ID, userId);
        return this;
    }

    public QBLocationRequestBuilder setUserIds(Integer... userIds) {
        addRule(QueryRule.USER_IDS, arrayToString(userIds));
        return this;
    }

    public QBLocationRequestBuilder setUserName(String userName) {
        addRule(QueryRule.USER_NAME, userName);
        return this;
    }

    public QBLocationRequestBuilder setUserExternalIds(String... userIds) {
        addRule(QueryRule.USER_EXTERNAL_IDS, arrayToString(userIds));
        return this;
    }

    // Diapasons http://quickblox.com/developers/Location#Diapasons

    public QBLocationRequestBuilder setMinCreatedAt(int timestamp) {
        addRule(QueryRule.MIN_CREATED_AT, timestamp);
        return this;
    }

    public QBLocationRequestBuilder setMaxCreatedAt(int timestamp) {
        addRule(QueryRule.MAX_CREATED_AT, timestamp);
        return this;
    }

    public QBLocationRequestBuilder setGeoRect(double latitude1, double longitude1, double latitude2, double longitude2) {
        addRule(QueryRule.GEO_RECT, String.format("%s;%s;%s;%s", latitude1, longitude1, latitude2, longitude2));
        return this;
    }

    public QBLocationRequestBuilder setCurrentPosition(double latitude1, double longitude1) {
        addRule(QueryRule.CURRENT_POSITION, String.format("%s;%s", latitude1, longitude1));
        return this;
    }

    public QBLocationRequestBuilder setRadius(double latitude1, double longitude1, int radius) {
        addRule(QueryRule.CURRENT_POSITION, String.format("%s;%s", latitude1, longitude1));
        addRule(QueryRule.RADIUS, radius);
        return this;
    }

    public QBLocationRequestBuilder setRadius(double latitude1, double longitude1, double latitude2,
                                              double longitude2) {
        double r = LocationHelper.getTwoCoordinatesDistance(latitude1, longitude1, latitude2, longitude2);
        int radius = (int) Math.round(r);
        setRadius(latitude1, latitude2, radius);
        return this;
    }

    // Sort http://quickblox.com/developers/Location#Sort

    public QBLocationRequestBuilder setSort(SortOrder sortOrder) {
        if (sortOrder.equals(SortOrder.ASCENDING)) {
            addRule(QueryRule.SORT_ASC, "1");
        }
        return this;
    }

    public QBLocationRequestBuilder setSort(SortField sortField) {
        addRule(QueryRule.SORT_BY, sortField.toString());
        return this;
    }

    public QBLocationRequestBuilder setSort(SortField sortField,
                                            SortOrder sortOrder) {
        setSort(sortField);
        setSort(sortOrder);
        return this;
    }

    public QBLocationRequestBuilder setSort(double latitude1, double longitude1,
                                            SortField sortField,
                                            SortOrder sortOrder) {
        addRule(QueryRule.CURRENT_POSITION, String.format("%s;%s", latitude1, longitude1));
        setSort(sortField);
        setSort(sortOrder);
        return this;
    }

}