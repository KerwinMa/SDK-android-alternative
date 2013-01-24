package com.goodness.faker;

import com.goodness.faker.entity.NumberGen;

/**
 * User: Oleg Soroka
 * Date: 15.09.12
 * Time: 13:40
 */
public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 100000; i++) {

            double d = NumberGen.getDouble(-180, 180, 5);

            if (Math.round(d) == d) {
                System.out.print("*");
            }

            System.out.println(d);


        }

    }

}
