package com.quickblox.android.framework.tests.custom;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;
import com.quickblox.android.framework.modules.custom.net.server.QBCustomObjects;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import org.apache.http.HttpStatus;

/**
 * User: Oleg Soroka
 * Date: 03.10.12
 * Time: 22:51
 */
public class TestDeleteObject extends CustomObjectTestCase {

    QBCustomObject hero = getFakeObject();

    @Override
    public void setUp() throws Exception {
        QBUsers.signIn(testUserAccount, null);
        QBCustomObjects.createObject(hero, null);
    }

    public void testDeleteObject() {
        QBCustomObjects.deleteObject(hero, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                checkEmptyResponseBody(result);
            }
        });
    }

    public void testDeleteObjectById() {
        String id = hero.getCustomObjectId();
        QBCustomObjects.deleteObject(className, id, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                checkEmptyResponseBody(result);
            }
        });
    }

}