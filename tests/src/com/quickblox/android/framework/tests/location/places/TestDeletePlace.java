package com.quickblox.android.framework.tests.location.places;

import com.goodness.faker.entity.LocationGen;
import com.goodness.faker.entity.NumberGen;
import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.models.QBPlace;
import com.quickblox.android.framework.modules.location.net.server.QBLocations;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import com.quickblox.android.framework.tests.GenericTestCase;
import org.apache.http.HttpStatus;

/**
 * User: Oleg Soroka
 * Date: 14.09.12
 * Time: 12:08
 */
public class TestDeletePlace extends GenericTestCase {

    QBLocation location = new QBLocation();
    QBPlace place = new QBPlace();

    @Override
    public void setUp() throws Exception {
        double lat = LocationGen.getLatitude();
        double lng = LocationGen.getLongitude();

        location.setLatitude(lat);
        location.setLongitude(lng);
        location.setStatus(String.format("lat %s, lng %s", lat, lng));

        QBUsers.signIn(testUserAccount, null);

        QBLocations.createLocation(location, null);

        final Integer locationId = location.getId();
        final String title = "some title " + NumberGen.getInt();
        final String description = "some description " + NumberGen.getInt();
        final String address = "some address " + NumberGen.getInt();

        place.setLocationId(locationId);
        place.setTitle(title);
        place.setDescription(description);
        place.setAddress(address);

        QBLocations.createPlace(place, null);
    }

    public void testDeletePlace() {
        QBLocations.deletePlace(place, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                checkEmptyResponseBody(result);
            }
        });
    }
}