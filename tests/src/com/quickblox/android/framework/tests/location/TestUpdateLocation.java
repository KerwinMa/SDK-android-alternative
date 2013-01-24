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
 * Time: 19:01
 */
public class TestUpdateLocation extends GenericTestCase {

    QBLocation location = new QBLocation();

    String updatedStatus = "updated status";
    double updatedLatitude = 34;
    double updatedLongitude = 34;

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

    public void testUpdateLocation() {
        location.setStatus(updatedStatus);
        location.setLatitude(updatedLatitude);
        location.setLongitude(updatedLongitude);

        QBLocations.updateLocation(location, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkSchemaValidation("updateLocation", result);

                assertEquals(updatedStatus, location.getStatus());
                assertEquals(updatedLatitude, location.getLatitude());
                assertEquals(updatedLongitude, location.getLongitude());
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        QBLocations.deleteLocation(location, null);
    }
}