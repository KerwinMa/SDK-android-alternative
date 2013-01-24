package com.quickblox.android.framework.tests;

import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;
import com.quickblox.android.framework.tests.auth.TestAuthModule;
import com.quickblox.android.framework.tests.auth.TestSignInOnce;
import com.quickblox.android.framework.tests.content.*;
import com.quickblox.android.framework.tests.custom.*;
import com.quickblox.android.framework.tests.location.*;
import com.quickblox.android.framework.tests.location.places.TestCreateNewPlace;
import com.quickblox.android.framework.tests.location.places.TestDeletePlace;
import com.quickblox.android.framework.tests.location.places.TestGetPlace;
import com.quickblox.android.framework.tests.location.places.TestUpdatePlace;
import com.quickblox.android.framework.tests.users.TestGetUsers;
import com.quickblox.android.framework.tests.users.TestSignIn;
import com.quickblox.android.framework.tests.users.TestSignOut;
import com.quickblox.android.framework.tests.users.TestSignUp;
import junit.framework.TestSuite;

/**
 * User: Oleg Soroka
 * Date: 13.09.12
 * Time: 21:22
 */

public class TestRunner extends InstrumentationTestRunner {

    // TODO !!! проверить фейлы тестов при выключенном интернете http://image.quickblox.com/3bf81a876ed888507d2eabbaebe9.injoit.png

    @Override
    public TestSuite getAllTests() {
        TestSuite suite = new InstrumentationTestSuite(this);

        /* Order is important */

        suite.addTestSuite(TestAuthModule.class);
        suite.addTestSuite(PrepareTests.class);
        suite.addTestSuite(TestSignInOnce.class);

        /* Location */

        suite.addTestSuite(TestCreateNewLocation.class);
        suite.addTestSuite(TestCreateNewLocationExtended.class);
        suite.addTestSuite(TestGetLocation.class);

        /* Be careful with fucking hack, where we using *_before and *_after test
         * to emulate JUnit4 features @BeforeClass and @AfterClass for JUnit3,
         * because there is no way to implement before and after code initializations
         * for whole test class in JUnit3.
         * There is one of reasons why I hate JUnit3. Enjoy it. */

        suite.addTestSuite(TestGetLocations_before.class);
        suite.addTestSuite(TestGetLocations.class);
        suite.addTestSuite(TestGetLocations_after.class);

        suite.addTestSuite(TestUpdateLocation.class);
        suite.addTestSuite(TestDeleteLocation.class);

        /* Places */

        suite.addTestSuite(TestCreateNewPlace.class);
        suite.addTestSuite(TestGetPlace.class);
        suite.addTestSuite(TestUpdatePlace.class);
        suite.addTestSuite(TestDeletePlace.class);

        // TODO + более детальные тесты для QBPlaces

        /* Users */

        suite.addTestSuite(TestSignUp.class);
        suite.addTestSuite(TestSignIn.class);
        suite.addTestSuite(TestSignOut.class);
        suite.addTestSuite(TestGetUsers.class);

        // TODO + подробные тесты для QBUsers


        /* Custom objects */

        suite.addTestSuite(TestCreateNewObject.class);
        suite.addTestSuite(TestUpdateObject.class);
        suite.addTestSuite(TestGetObject.class);
        suite.addTestSuite(TestGetObjects.class);
        suite.addTestSuite(TestDeleteObject.class);

        /* Content */

        suite.addTestSuite(TestUploadFileTask.class);
        suite.addTestSuite(TestDeleteFile.class);
        suite.addTestSuite(TestGetFile.class);
        suite.addTestSuite(TestGetFiles.class);
        suite.addTestSuite(TestDownloadFile.class);
        suite.addTestSuite(TestUploadFileTaskIncorrect.class);

        /* Tear down tests */

        suite.addTestSuite(TearDownTests.class);

        return suite;
    }

    @Override
    public ClassLoader getLoader() {
        return TestRunner.class.getClassLoader();
    }
}