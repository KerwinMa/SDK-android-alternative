package com.quickblox.android.framework.tests.location;

import com.goodness.faker.entity.LocationGen;
import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.net.server.QBLocations;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import com.quickblox.android.framework.tests.GenericTestCase;

/**
 * User: Oleg Soroka
 * Date: 14.09.12
 * Time: 12:08
 */
public class TestCreateNewLocationExtended extends GenericTestCase {

    QBLocation location = new QBLocation();

    @Override
    protected void setUp() throws Exception {
        double lat = LocationGen.getLatitude();
        double lng = LocationGen.getLongitude();

        location.setLatitude(lat);
        location.setLongitude(lng);
        location.setStatus(String.format("some correct status; lat = %s, lng = %s", lat, lng));

        QBUsers.signIn(testUserAccount, null);
    }

    public void testShortStatus() {
        location.setStatus("");

        QBLocations.createLocation(location, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                if (!result.isSuccess()) {
                    String statusErrorExpected = "status is too short (minimum is 1 characters)";
                    String statusErrorActual = result.getErrors().get(0);
                    assertEquals(statusErrorExpected, statusErrorActual);
                }
            }
        });
    }

    public void testLongStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        int k = 1001; // 1000 is max status string length
        for (int i = 0; i <= k; i++) {
            stringBuilder.append("a");
        }
        String status = stringBuilder.toString();

        location.setStatus(status);

        QBLocations.createLocation(location, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                if (!result.isSuccess()) {
                    String statusErrorExpected = "status is too long (maximum is 1000 characters)";
                    String statusErrorActual = result.getErrors().get(0);
                    assertEquals(statusErrorExpected, statusErrorActual);
                }
            }
        });
    }

    public void testIncorrectLatitude() {
        double incorrectLatitude = 91;
        location.setLatitude(incorrectLatitude);

        QBLocations.createLocation(location, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                if (!result.isSuccess()) {
                    String latitudeErrorExpected = "latitude must be less than or equal to 90";
                    String latitudeErrorActual = result.getErrors().get(0);
                    assertEquals(latitudeErrorExpected, latitudeErrorActual);
                }
            }
        });
    }

    public void testIncorrectLongitude() {
        double incorrectLongitude = 181;
        location.setLongitude(incorrectLongitude);

        QBLocations.createLocation(location, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                if (!result.isSuccess()) {
                    String longitudeErrorExpected = "longitude must be less than or equal to 180";
                    String longitudeErrorActual = result.getErrors().get(0);
                    assertEquals(longitudeErrorExpected, longitudeErrorActual);
                }
            }
        });
    }

    public void testIncorrectLatitudeLongitude() {
        double incorrectLatitude = 91;
        double incorrectLongitude = 181;

        location.setLatitude(incorrectLatitude);
        location.setLongitude(incorrectLongitude);

        QBLocations.createLocation(location, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                if (!result.isSuccess()) {
                    String latitudeErrorExpected = "latitude must be less than or equal to 90";
                    String longitudeErrorExpected = "longitude must be less than or equal to 180";

                    String latitudeErrorActual = result.getErrors().get(0);
                    String longitudeErrorActual = result.getErrors().get(1);

                    assertEquals(2, result.getErrors().size());
                    assertEquals(longitudeErrorExpected, longitudeErrorActual);
                    assertEquals(latitudeErrorExpected, latitudeErrorActual);
                }
            }
        });
    }

    public void testIncorrectNegativeLatitudeLongitude() {
        double incorrectLatitude = -91;
        double incorrectLongitude = -181;

        location.setLatitude(incorrectLatitude);
        location.setLongitude(incorrectLongitude);

        QBLocations.createLocation(location, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                if (!result.isSuccess()) {
                    String latitudeErrorExpected = "latitude must be greater than or equal to -90";
                    String longitudeErrorExpected = "longitude must be greater than or equal to -180";

                    String latitudeErrorActual = result.getErrors().get(0);
                    String longitudeErrorActual = result.getErrors().get(1);

                    assertEquals(2, result.getErrors().size());
                    assertEquals(latitudeErrorExpected, latitudeErrorActual);
                    assertEquals(longitudeErrorExpected, longitudeErrorActual);
                }
            }
        });
    }

    public void testWithShortStatusIncorrectLatitudeLongitude() {
        double incorrectLatitude = -91;
        double incorrectLongitude = -181;

        location.setLatitude(incorrectLatitude);
        location.setLongitude(incorrectLongitude);
        location.setStatus("");

        QBLocations.createLocation(location, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                if (!result.isSuccess()) {
                    String latitudeErrorExpected = "latitude must be greater than or equal to -90";
                    String longitudeErrorExpected = "longitude must be greater than or equal to -180";
                    String statusErrorExpected = "status is too short (minimum is 1 characters)";

                    String statusErrorActualLatitude = result.getErrors().get(0);
                    String statusErrorActualLongitude = result.getErrors().get(1);
                    String statusErrorActual = result.getErrors().get(2);

                    assertEquals(3, result.getErrors().size());
                    assertEquals(statusErrorExpected, statusErrorActual);
                    assertEquals(longitudeErrorExpected, statusErrorActualLongitude);
                    assertEquals(latitudeErrorExpected, statusErrorActualLatitude);
                }
            }
        });
    }
}