package com.quickblox.android.framework.tests.custom;

import com.goodness.faker.entity.BooleanGen;
import com.goodness.faker.entity.NumberGen;
import com.goodness.faker.entity.StringGen;
import com.quickblox.android.framework.modules.custom.models.QBCustomObject;
import com.quickblox.android.framework.tests.GenericTestCase;

/**
 * User: Oleg Soroka
 * Date: 03.10.12
 * Time: 20:34
 */
public class CustomObjectTestCase extends GenericTestCase {

    // Use following schema
    // health - integer
    // type - string
    // god_mode - boolean
    // power - float

    String[] types = {"Paranormal", "Warrior", "Daredevil", "Protector",
            "Tycoon", "Nerd", "Rogue", "Brooding", "Alpha"};

    String className = "hero";

    String fieldHealth = "health";
    String fieldType = "type";
    String fieldGodMode = "god_mode";
    String fieldPower = "power";

    QBCustomObject getFakeObject() {
        QBCustomObject hero = new QBCustomObject(className);
        int health = NumberGen.getInt(60, 100);
        String type = StringGen.getRandomFromArray(types);
        boolean godMode = BooleanGen.getNext();
        float power = (float) NumberGen.getDouble(0, 100, 2);

        hero.put(fieldHealth, health);
        hero.put(fieldType, type);
        hero.put(fieldGodMode, godMode);
        hero.put(fieldPower, power);

        return hero;
    }

}