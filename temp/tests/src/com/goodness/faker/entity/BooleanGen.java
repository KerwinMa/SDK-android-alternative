package com.goodness.faker.entity;

import java.util.Random;

/**
 * User: Oleg Soroka
 * Date: 03.10.12
 * Time: 00:08
 */
public class BooleanGen {

    public static boolean getNext() {
        return (new Random()).nextBoolean();
    }

}
