package com.quickblox.android.framework.tests;

import junit.framework.TestSuite;

/**
 * User: Oleg Soroka
 * Date: 15.10.12
 * Time: 21:55
 */
public class StageTestRunner extends TestRunner {

    @Override
    public TestSuite getAllTests() {

        TestConfig.loadStageConfig();

        return super.getAllTests();
    }
}