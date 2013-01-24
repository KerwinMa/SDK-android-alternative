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
 * Time: 12:08
 */
public class TestCreateNewLocation extends GenericTestCase {

    QBLocation location = new QBLocation();

    @Override
    public void setUp() throws Exception {
        QBUsers.signIn(testUserAccount, null);
    }

    public void testCreateNewLocation() {
        double lat = LocationGen.getLatitude();
        double lng = LocationGen.getLongitude();

        location.setLatitude(lat);
        location.setLongitude(lng);
        location.setStatus(String.format("lat %s, lng %s", lat, lng));

        QBLocations.createLocation(location, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_CREATED, result);
                checkSchemaValidation("createLocation", result);

                QBLocationResult locationResult = (QBLocationResult) result;

                assertEquals(location.getLatitude(), locationResult.getLocation().getLatitude());
                assertEquals(location.getLongitude(), locationResult.getLocation().getLongitude());
                assertEquals(location.getStatus(), locationResult.getLocation().getStatus());
            }
        });
    }

    @Override
    protected void tearDown() throws Exception {
        QBLocations.deleteLocation(location, null);
    }
}