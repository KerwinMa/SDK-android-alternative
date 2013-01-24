package com.quickblox.android.framework.tests;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;

/**
 * User: Oleg Soroka
 * Date: 15.10.12
 * Time: 22:16
 */
public class TearDownTests extends GenericTestCase {

    public void testTearDownTests() {
        QBUsers.getUserByLogin(testUserAccount, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                if (result.isSuccess()) {
                    QBUsers.signIn(testUserAccount, null);
                    QBUsers.deleteUser(testUserAccount, null);
                }
            }
        });
    }
}