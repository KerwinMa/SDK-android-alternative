package com.goodness.faker.entity;

import com.goodness.faker.gen.NameGen;
import com.goodness.faker.gen.SurnameGen;

/**
 * User: Oleg Soroka
 * Date: 15.09.12
 * Time: 14:32
 */
public class UserGen {

    public static String getLogin() {
        return SurnameGen.getSurname().toLowerCase();
    }

    public static String getPassword() {
        return StringGen.getPassword();
    }

    public static String getName() {
        return NameGen.getName();
    }

    public static String getSurname() {
        return SurnameGen.getSurname();
    }

    public static String getFullName() {
        return getName() + " " + getSurname();
    }

    public static String getEmail() {
        return getEmail(getLogin());
    }

    public static String getEmail(String base) {
        return String.format("%s@gmail.com", base);
    }

    public static String getWebsite() {
        return getWebsite(getLogin());
    }

    public static String getWebsite(String base) {
        return String.format("%s.com", base);
    }
}