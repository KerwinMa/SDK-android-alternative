package com.goodness.faker.entity;

/**
 * User: Oleg Soroka
 * Date: 15.09.12
 * Time: 14:41
 */
public class LocationGen {

    public static double getLatitude() {
        return NumberGen.getDouble(-90, 90, 5); // [-90, 90]
    }

    public static double getLongitude() {
        return NumberGen.getDouble(-90, 90, 5); // [-180; 180]
    }

}
