package com.quickblox.android.framework.tests.auth;

import com.quickblox.android.framework.base.models.QBSettings;
import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.auth.net.results.QBSessionResult;
import com.quickblox.android.framework.modules.auth.net.server.QBAuth;
import com.quickblox.android.framework.tests.GenericTestCase;
import org.apache.http.HttpStatus;

/**
 * User: Oleg Soroka
 * Date: 16.08.12
 * Time: 11:41
 */
public class TestAuthModule extends GenericTestCase {

    public void testAuthorizeApp() {
        QBAuth.authorizeApp(new QBCallback() {
            @Override
            public void onComplete(Result result) {
                QBSessionResult sessionResult = (QBSessionResult) result;
                checkHttpStatus(HttpStatus.SC_CREATED, result);
                assertEquals(sessionResult.getSession().getAppId().toString(),
                        QBSettings.getInstance().getApplicationId());
            }
        });
        assertTrue(true);
    }

}