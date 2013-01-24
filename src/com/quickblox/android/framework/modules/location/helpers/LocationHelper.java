package com.quickblox.android.framework.modules.location.helpers;

/**
 * User: Oleg Soroka
 * Date: 18.09.12
 * Time: 12:38
 */
public class LocationHelper {

    // Returns distance between coordinates in meters.
    public static double getTwoCoordinatesDistance(double lat1, double lng1, double lat2, double lng2) {
        float pk = (float) (180 / 3.14169);

        double a1 = lat1 / pk;
        double a2 = lng1 / pk;
        double b1 = lat2 / pk;
        double b2 = lng2 / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 6366000 * tt;
    }

}
