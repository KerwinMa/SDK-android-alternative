package com.quickblox.android.framework.tests.custom;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;
import com.quickblox.android.framework.modules.custom.net.requests.QBCustomObjectRequestBuilder;
import com.quickblox.android.framework.modules.custom.net.results.QBCustomObjectCountResult;
import com.quickblox.android.framework.modules.custom.net.results.QBCustomObjectLimitedResult;
import com.quickblox.android.framework.modules.custom.net.server.QBCustomObjects;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import org.apache.http.HttpStatus;

import java.util.ArrayList;

/**
 * User: Oleg Soroka
 * Date: 03.10.12
 * Time: 20:33
 */
public class TestGetObjects extends CustomObjectTestCase {

    // TODO написать тесты для всех фильтров!!

    int objectsCount = 2;

    ArrayList<QBCustomObject> objects = new ArrayList<QBCustomObject>();

    @Override
    public void setUp() throws Exception {
        QBUsers.signIn(testUserAccount, null);
        for (int i = 0; i < objectsCount; i++) {
            QBCustomObject hero = getFakeObject();
            QBCustomObjects.createObject(hero, null);
            objects.add(hero);
        }
    }

    public void testGetObjects() {
        QBCustomObjects.getObjects(className, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                QBCustomObjectLimitedResult customObjectResult = (QBCustomObjectLimitedResult) result;
                ArrayList<QBCustomObject> customObjectsList = customObjectResult.getCustomObjects();
                assertEquals(objects.size(), customObjectsList.size());
                for (QBCustomObject co : customObjectsList) {
                    assertTrue(containsById(objects, co.getCustomObjectId()));
                }
            }
        });
    }

    public void testCountObjects() {
        QBCustomObjects.countObjects(className, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                QBCustomObjectCountResult countResult = (QBCustomObjectCountResult) result;
                int count = countResult.getCount();
                assertEquals(objects.size(), count);
            }
        });
    }

    public void testLimitedQuery() {
        final Integer limit = 1;
        final Integer skip = 1;

        QBCustomObjectRequestBuilder requestBuilder = new QBCustomObjectRequestBuilder();
        requestBuilder.setPagesLimit(limit);
        requestBuilder.setPagesSkip(skip);
        QBCustomObjects.getObjects(className, requestBuilder, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                QBCustomObjectLimitedResult customObjectLimitedResult = (QBCustomObjectLimitedResult) result;
                ArrayList<QBCustomObject> customObjectsList = customObjectLimitedResult.getCustomObjects();
                assertEquals(limit, new Integer(customObjectsList.size()));
                assertEquals(limit, customObjectLimitedResult.getPagesLimit());
                assertEquals(skip, customObjectLimitedResult.getPagesSkip());
            }
        });
    }

    private boolean containsById(ArrayList<QBCustomObject> list, String id) {
        boolean contains = false;
        for (QBCustomObject co : list) {
            if (co.getCustomObjectId().equals(id)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public void tearDown() throws Exception {
        for (QBCustomObject co : objects) {
            QBCustomObjects.deleteObject(co, null);
        }
    }
}