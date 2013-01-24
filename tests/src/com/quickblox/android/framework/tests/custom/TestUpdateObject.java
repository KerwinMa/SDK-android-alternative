package com.quickblox.android.framework.tests.custom;

import com.quickblox.android.framework.base.definitions.QBCallback;
import com.quickblox.android.framework.base.net.results.Result;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;
import com.quickblox.android.framework.modules.custom.net.results.QBCustomObjectResult;
import com.quickblox.android.framework.modules.custom.net.server.QBCustomObjects;
import com.quickblox.android.framework.modules.users.net.server.QBUsers;
import org.apache.http.HttpStatus;

/**
 * User: Oleg Soroka
 * Date: 03.10.12
 * Time: 23:11
 */
public class TestUpdateObject extends CustomObjectTestCase {

    QBCustomObject hero = getFakeObject();

    @Override
    public void setUp() throws Exception {
        QBUsers.signIn(testUserAccount, null);
        QBCustomObjects.createObject(hero, null);
    }

    public void testUpdateObject() {
        final String newHeroType = "monster";
        hero.put(fieldType, newHeroType);

        QBCustomObjects.updateObject(hero, new QBCallback() {
            @Override
            public void onComplete(Result result) {
                checkHttpStatus(HttpStatus.SC_OK, result);
                checkIfSuccess(result);
                QBCustomObject newHero = ((QBCustomObjectResult) result).getCustomObject();

                assertEquals(newHeroType, newHero.getFields().get(fieldType));

                assertEquals(hero.getClassName(), newHero.getClassName());
                assertEquals(hero.getFields().get(fieldHealth), newHero.getFields().get(fieldHealth));
                assertEquals(hero.getFields().get(fieldPower), newHero.getFields().get(fieldPower));
                assertEquals(hero.getFields().get(fieldGodMode), newHero.getFields().get(fieldGodMode));
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        QBCustomObjects.deleteObject(hero, null);
    }

}