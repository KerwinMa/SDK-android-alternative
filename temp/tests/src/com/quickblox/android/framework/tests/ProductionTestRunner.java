package com.quickblox.android.framework.tests;

import junit.framework.TestSuite;

/**
 * User: Oleg Soroka
 * Date: 15.10.12
 * Time: 21:55
 */
public class ProductionTestRunner extends TestRunner {

    @Override
    public TestSuite getAllTests() {

        TestConfig.loadProductionConfig();

        return super.getAllTests();
    }
}