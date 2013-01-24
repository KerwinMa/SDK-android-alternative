package com.quickblox.android.framework.tests.users;

import com.goodness.faker.entity.FakeUser;
import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import com.quickblox.android.framework.tests.GenericTestCase;
import org.apache.http.HttpStatus;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 29.09.12
 * Time: 13:45
 */
public class TestGetUsers extends GenericTestCase {

    int usersCount = 2;

    ArrayList<QBUser> users = new ArrayList<QBUser>();

    @Override
    public void setUp() throws Exception {
        for (int i = 0; i < usersCount; i++) {
            FakeUser fakeUser = new FakeUser();
            QBUser user = new QBUser();
            user.setLogin(fakeUser.getLogin());
            user.setPassword((fakeUser.getPassword()));
            user.setEmail(fakeUser.getEmail());
            user.setFullName(fakeUser.getFullName());

            users.add(user);
            QBUsers.signUp(user, null);
        }
    }

    public void testGetUsers() {
        QBUsers.getUsers(new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkIfSuccess(result);
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkSchemaValidation("getUsers", result);
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        for (QBUser u : users) {
            QBUsers.signIn(u, null);
            QBUsers.deleteUser(u, null);
        }
    }
}