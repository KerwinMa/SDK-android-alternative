package com.quickblox.android.framework.modules.location.net.server;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.server.BaseService;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.models.QBPlace;
import com.quickblox.android.framework.modules.location.net.queries.*;
import com.quickblox.android.framework.modules.location.net.request.QBLocationRequestBuilder;

/**
 * @author: Oleg Soroka
 * Date: 17.07.12
 * Time: 15:25
 */
public class QBLocations extends BaseService {

    // Create

    public static void createLocation(QBLocation location, QBCallback callback) {
        createLocation(location, callback, null);
    }

    public static void createLocation(QBLocation location, QBCallback callback, Object context) {
        QueryCreateLocation query = new QueryCreateLocation(location);
        query.performAsyncWithCallback(callback, context);
    }

    // Retrieve

    public static void getLocations(QBLocationRequestBuilder requestBuilder, QBCallback callback, Object context) {
        QueryGetLocations query = new QueryGetLocations(requestBuilder);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getLocations(QBLocationRequestBuilder requestBuilder, QBCallback callback) {
        getLocations(requestBuilder, callback, null);
    }

    public static void getLocation(QBLocation location, QBCallback callback, Object context) {
        QueryGetLocation query = new QueryGetLocation(location);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getLocation(QBLocation location, QBCallback callback) {
        getLocation(location, callback, null);
    }

    public static void getLocation(int id, QBCallback callback, Object context) {
        QBLocation location = new QBLocation(id);
        QueryGetLocation query = new QueryGetLocation(location);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getLocation(int id, QBCallback callback) {
        getLocation(id, callback, null);
    }

    // Update

    public static void updateLocation(QBLocation location, QBCallback callback) {
        updateLocation(location, callback, null);
    }

    public static void updateLocation(QBLocation location, QBCallback callback, Object context) {
        QueryUpdateLocation query = new QueryUpdateLocation(location);
        query.performAsyncWithCallback(callback, context);
    }

    // Delete

    public static void deleteLocation(int id, QBCallback callback) {
        deleteLocation(id, callback, null);
    }

    public static void deleteLocation(int id, QBCallback callback, Object context) {
        QBLocation location = new QBLocation();
        location.setId(id);
        deleteLocation(location, callback, context);
    }

    public static void deleteLocation(QBLocation location, QBCallback callback) {
        deleteLocation(location, callback, null);
    }

    public static void deleteLocation(QBLocation location, QBCallback callback, Object context) {
        QueryDeleteLocation query = new QueryDeleteLocation(location);
        query.performAsyncWithCallback(callback, context);
    }

    // TODO method should be carefully tested
    public static void deleteObsoleteLocations(int period, QBCallback callback) {
        deleteObsoleteLocations(period, callback, null);
    }

    public static void deleteObsoleteLocations(int period, QBCallback callback, Object context) {
        QueryDeleteObsoleteLocation query = new QueryDeleteObsoleteLocation(period);
        query.performAsyncWithCallback(callback, context);
    }

    /* Places */

    // Create

    public static void createPlace(QBPlace place, QBCallback callback) {
        createPlace(place, callback, null);
    }

    public static void createPlace(QBPlace place, QBCallback callback, Object context) {
        QueryCreatePlace query = new QueryCreatePlace(place);
        query.performAsyncWithCallback(callback, context);
    }

    // Retrieve

    public static void getPlace(QBPlace place, QBCallback callback, Object context) {
        QueryGetPlace query = new QueryGetPlace(place);
        query.performAsyncWithCallback(callback, context);
    }

    public static void getPlace(int id, QBCallback callback, Object context) {
        getPlace(new QBPlace(id), callback, context);
    }

    public static void getPlace(int id, QBCallback callback) {
        getPlace(id, callback, null);
    }

    public static void getPlace(QBPlace place, QBCallback callback) {
        getPlace(place, callback, null);
    }

    // Update

    public static void updatePlace(QBPlace place, QBCallback callback) {
        updatePlace(place, callback, null);
    }

    public static void updatePlace(QBPlace place, QBCallback callback, Object context) {
        QueryUpdatePlace query = new QueryUpdatePlace(place);
        query.performAsyncWithCallback(callback, context);
    }

    // Delete

    public static void deletePlace(QBPlace place, QBCallback callback) {
        deletePlace(place, callback, null);
    }

    public static void deletePlace(QBPlace place, QBCallback callback, Object context) {
        QueryDeletePlace query = new QueryDeletePlace(place);
        query.performAsyncWithCallback(callback, context);
    }

    public static void deletePlace(int placeId, QBCallback callback) {
        deletePlace(placeId, callback, null);
    }

    public static void deletePlace(int placeId, QBCallback callback, Object context) {
        QBPlace place = new QBPlace();
        place.setId(placeId);
        deletePlace(place, callback, context);
    }
}