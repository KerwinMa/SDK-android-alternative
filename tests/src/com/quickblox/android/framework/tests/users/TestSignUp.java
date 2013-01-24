package com.quickblox.android.framework.tests.users;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import org.apache.http.HttpStatus;

/**
 * User: Oleg Soroka
 * Date: 28.09.12
 * Time: 20:14
 */
public class TestSignUp extends UsersTestCase {

    public void testSignUp() {
        QBUsers.signUp(testUser, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkIfSuccess(result);
                checkHttpStatus(HttpStatus.SC_CREATED, result);
                checkSchemaValidation("userSignUp", result);

                // TODO + проверка полей полученного объекта
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        QBUsers.signIn(testUser, null);
        QBUsers.deleteUser(testUser, null);
    }
}