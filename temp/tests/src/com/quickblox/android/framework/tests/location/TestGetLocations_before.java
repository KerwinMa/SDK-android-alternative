package com.quickblox.android.framework.tests.location;

import com.goodness.faker.entity.NumberGen;
import com.goodness.faker.entity.UserGen;
import com.quickblox.android.framework.modules.location.models.QBLocation;
import com.quickblox.android.framework.modules.location.net.server.QBLocations;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import com.quickblox.android.framework.tests.GenericTestCase;
import com.quickblox.android.framework.tests.helpers.ComplexUser;

/**
 * User: Oleg Soroka
 * Date: 14.09.12
 * Time: 19:34
 */
public class TestGetLocations_before extends GenericTestCase {

    @Override
    public void setUp() throws Exception {
        for (int j = 0; j < TestGetLocations.usersCount; j++) {
            String login = "fake_login_" + j;
            String password = login + TestGetLocations.PASSWORD_POSTFIX;
            String externalId = "1000000" + j;

            QBUser user = new QBUser();
            user.setLogin(login);
            user.setPassword(password);
            user.setFullName(UserGen.getFullName());
            user.setExternalId(externalId);

            QBUsers.signUp(user, null);

            QBUsers.signIn(user, null);

            ComplexUser cUser = new ComplexUser(user);
            TestGetLocations.users.add(cUser);

            for (int i = 0; i < TestGetLocations.locationsPerUser; i++) {
                QBLocation location = new QBLocation();
                location.setStatus("some status " + NumberGen.getIntWithRespectToZero(99));
                if (j == 0) {
                    if (i == 0) {
                        location.setLatitude(TestGetLocations.lat1);
                        location.setLongitude(TestGetLocations.lng1);
                    }
                    if (i == 1) {
                        location.setLatitude(TestGetLocations.lat2);
                        location.setLongitude(TestGetLocations.lng2);
                    }
                } else {
                    location.setLongitude((double) NumberGen.getInt(-180, 180));
                    location.setLatitude((double) NumberGen.getInt(-90, 90));

                    boolean inCircle = pointInsideCircle(TestGetLocations.lat1, TestGetLocations.lng1,
                            location.getLatitude(), location.getLongitude(),
                            TestGetLocations.distanceInMeters);

                    System.out.println(">>> IMPORTANT inCircle=" + inCircle);
                }

                QBLocations.createLocation(location, null);

                assertTrue(true);

                cUser.addLocation(location);
                TestGetLocations.locations.add(location);
            }
        }
    }

    private boolean pointInsideCircle(double xCenter, double yCenter, double xPoint, double yPoint, double radius) {
        boolean flag = false;
        if (Math.sqrt(Math.pow((xCenter - xPoint), 2) + Math.pow((yCenter - yPoint), 2)) <= radius) {
            flag = true;
        }
        return flag;
    }

    public void testEmpty() {
        assertTrue(true);
    }
}