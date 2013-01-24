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
public class TestSignIn extends UsersTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        QBUsers.signUp(testUser, null);
    }

    public void testSignIn() {
        QBUsers.signIn(testUser, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkIfSuccess(result);
                checkHttpStatus(HttpStatus.SC_ACCEPTED, result);
                checkSchemaValidation("userSignIn", result);
            }
        });
    }

    public void testSignInByLoginAndPassword() {
        QBUsers.signIn(testLogin, testPassword, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkIfSuccess(result);
                checkHttpStatus(HttpStatus.SC_ACCEPTED, result);
                checkSchemaValidation("userSignIn", result);
            }
        });
    }

    public void testSignInByEmail() {
        QBUsers.signInByEmail(testUser, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkIfSuccess(result);
                checkHttpStatus(HttpStatus.SC_ACCEPTED, result);
                checkSchemaValidation("userSignIn", result);
            }
        });
    }

    public void testSignInByEmailAndPassword() {
        QBUsers.signInByEmail(testEmail, testPassword, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkIfSuccess(result);
                checkHttpStatus(HttpStatus.SC_ACCEPTED, result);
                checkSchemaValidation("userSignIn", result);
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        QBUsers.deleteUser(testUser, null);
    }
}