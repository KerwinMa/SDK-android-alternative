package com.quickblox.android.framework.tests.location;

import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import com.quickblox.android.framework.tests.GenericTestCase;
import com.quickblox.android.framework.tests.helpers.ComplexUser;

/**
 * User: Oleg Soroka
 * Date: 14.09.12
 * Time: 19:34
 */
public class TestGetLocations_after extends GenericTestCase {

    @Override
    public void tearDown() throws Exception {
        for (ComplexUser cu : TestGetLocations.users) {
            QBUser user = cu.getUser();
            int userId = user.getId();

            String login = user.getLogin();
            String password = login + TestGetLocations.PASSWORD_POSTFIX;

            QBUsers.signIn(login, password, null);

            QBUsers.deleteUser(userId, null);
        }
    }

    public void testEmpty() {
        assertTrue(true);
    }
}