package com.quickblox.android.framework.tests.location;

import com.goodness.faker.entity.LocationGen;
import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.net.result.QBLocationResult;
import com.quickblox.android.framework.modules.location.net.server.QBLocations;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import com.quickblox.android.framework.tests.GenericTestCase;
import org.apache.http.HttpStatus;

/**
 * User: Oleg Soroka
 * Date: 14.09.12
 * Time: 19:34
 */
public class TestGetLocation extends GenericTestCase {

    QBLocation location = new QBLocation();

    double lat = LocationGen.getLatitude();
    double lng = LocationGen.getLongitude();
    String status = String.format("lat %s, lng %s", lat, lng);

    @Override
    public void setUp() throws Exception {
        location.setLatitude(lat);
        location.setLongitude(lng);
        location.setStatus(status);

        QBUsers.signIn(testUserAccount, null);

        QBLocations.createLocation(location, null);

    }

    public void testGetLocation() {
        QBLocations.getLocation(location, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkSchemaValidation("getLocation", result);

                QBLocation resLocation = ((QBLocationResult) result).getLocation();
                assertEquals(lat, resLocation.getLatitude());
                assertEquals(lng, resLocation.getLongitude());
                assertEquals(status, resLocation.getStatus());
            }
        });
    }

    public void testGetLocationById() {
        int locationId = location.getId();

        QBLocations.getLocation(locationId, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                QBLocation resLocation = ((QBLocationResult) result).getLocation();
                assertEquals(lat, resLocation.getLatitude());
                assertEquals(lng, resLocation.getLongitude());
                assertEquals(status, resLocation.getStatus());
            }
        });
    }

    public void testGetLocationByIdWithNonexistentId() {
        int locationId = 0; // Nonexistent id.

        QBLocations.getLocation(locationId, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                if (!result.isSuccess()) {
                    String expectedError = "This geo datum was not found or it is bound to a place";
                    String actualError = result.getErrors().get(0);

                    checkHttpStatus(HttpStatus.SC_UNPROCESSABLE_ENTITY, result);
                    assertEquals(expectedError, actualError);
                }
            }
        });
    }


    @Override
    public void tearDown() throws Exception {
        QBLocations.deleteLocation(location, null);
    }
}