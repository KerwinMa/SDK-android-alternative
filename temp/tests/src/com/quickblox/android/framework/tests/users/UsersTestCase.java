package com.quickblox.android.framework.tests.users;

import com.goodness.faker.entity.UserGen;
import com.quickblox.android.framework.modules.users.models.QBUser;
import com.quickblox.android.framework.tests.GenericTestCase;
import com.quickblox.android.framework.tests.TestConfig;

/**
 * User: Oleg Soroka
 * Date: 28.09.12
 * Time: 20:56
 */
public class UsersTestCase extends GenericTestCase {

    // Main test testUserAccount
    String testLogin = UserGen.getLogin();
    String testPassword = TestConfig.USER_PASSWORD;
    String testEmail = UserGen.getEmail();
    QBUser testUser = new QBUser(testLogin, testPassword);

    @Override
    public void setUp() throws Exception {
        testUser.setEmail(testEmail);
    }
}