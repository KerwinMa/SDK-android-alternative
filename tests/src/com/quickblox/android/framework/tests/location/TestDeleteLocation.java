package com.quickblox.android.framework.tests.location;

import com.goodness.faker.entity.LocationGen;
import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.net.server.QBLocations;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import com.quickblox.android.framework.tests.GenericTestCase;
import org.apache.http.HttpStatus;

/**
 * User: Oleg Soroka
 * Date: 14.09.12
 * Time: 12:08
 */
public class TestDeleteLocation extends GenericTestCase {

    QBLocation location = new QBLocation();

    @Override
    public void setUp() throws Exception {
        double lat = LocationGen.getLatitude();
        double lng = LocationGen.getLongitude();

        location.setLatitude(lat);
        location.setLongitude(lng);
        location.setStatus(String.format("lat %s, lng %s", lat, lng));

        QBUsers.signIn(testUserAccount, null);

        QBLocations.createLocation(location, null);
    }

    public void testDeleteLocation() {
        QBLocations.deleteLocation(location, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                checkEmptyResponseBody(result);
            }
        });
    }

    public void testDeleteLocationById() {
        int locationId = location.getId();

        QBLocations.deleteLocation(locationId, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                checkEmptyResponseBody(result);
            }
        });
    }

    int tmpId = 0;

    public void testDeleteObsoleteLocations() {
        int period = 10; // days
        tmpId = location.getId();

        QBLocations.deleteObsoleteLocations(period, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                checkEmptyResponseBody(result);
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        if (tmpId != 0) {
            QBLocations.deleteLocation(tmpId, null);
        }
    }
}