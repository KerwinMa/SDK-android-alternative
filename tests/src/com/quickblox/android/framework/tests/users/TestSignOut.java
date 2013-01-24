package com.quickblox.android.framework.tests.users;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import org.apache.http.HttpStatus;

/**
 * User: Oleg Soroka
 * Date: 28.09.12
 * Time: 21:53
 */
public class TestSignOut extends UsersTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        QBUsers.signUp(testUser, null);
        QBUsers.signIn(testUser, null);
    }

    public void testSignOut() {
        QBUsers.signOut(new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkIfSuccess(result);
                checkHttpStatus(HttpStatus.SC_OK, result);
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        QBUsers.signIn(testUser, null);
        QBUsers.deleteUser(testUser, null);
    }
}