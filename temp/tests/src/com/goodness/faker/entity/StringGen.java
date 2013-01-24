package com.goodness.faker.entity;

import java.util.Random;

/**
 * User: Oleg Soroka
 * Date: 28.09.12
 * Time: 21:40
 */
public class StringGen {

    private static String az = "abcdefghijklmnopqrstuvwxyz";
    private static String n = "0123456789";

    private static int defMinLength = 8;

    public static String getPassword() {
        return getPassword(defMinLength);
    }

    public static String getPassword(int length) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            builder.append(getChar());
        }

        return builder.toString();
    }

    public static char getChar() {
        String allSymbols = az + n;
        int length = allSymbols.length();
        int n = NumberGen.getInt(length);
        return allSymbols.charAt(n);
    }

    public static String getRandomFromArray(String[] items) {
        int count = items.length;
        return items[(new Random()).nextInt(count)];
    }
}