package com.quickblox.android.framework.tests;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.modules.users.net.results.QBUserPagedResult;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;

/**
 * User: Oleg Soroka
 * Date: 15.10.12
 * Time: 22:16
 */
public class PrepareTests extends GenericTestCase {

    QBUserPagedResult pagedResult;

    // Creates main test user
    public void testPrepareTestUser() {

        // Check if we have any users (except owner) on the tests start moment, yte to delete them all.
        QBUsers.getUsers(new QBCallback() {
            @Override
            public void onComplete(Result result) {
                if (result.isSuccess()) {
                    pagedResult = (QBUserPagedResult) result;
                }
            }
        });

        // If have
        if (pagedResult != null && pagedResult.getUsers().size() > 1) {
            for (QBUser user : pagedResult.getUsers()) {
                // and current user is not account owner (it's impossible to remove account owner)
                if (!user.getLogin().equals(TestConfig.OWNER_LOGIN)) {
                    QBUsers.signIn(testUserAccount, null);
                    QBUsers.deleteUser(user, null);
                }
            }
        }


        QBUsers.signUp(testUserAccount, null);
    }
}