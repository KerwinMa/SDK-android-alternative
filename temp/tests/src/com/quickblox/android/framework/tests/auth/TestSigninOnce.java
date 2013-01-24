package com.quickblox.android.framework.tests.auth;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import com.quickblox.android.framework.tests.GenericTestCase;
import org.apache.http.HttpStatus;

/**
 * User: Oleg Soroka
 * Date: 14.09.12
 * Time: 17:12
 */
public class TestSignInOnce extends GenericTestCase {

    public void testSignInOnce() {
        QBUsers.signIn(testUserAccount, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkIfSuccess(result);
                checkHttpStatus(HttpStatus.SC_ACCEPTED, result);
            }
        });
    }

}
