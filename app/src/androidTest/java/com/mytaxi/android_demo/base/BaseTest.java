package com.mytaxi.android_demo.base;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.pages.AuthenticationPage;
import com.mytaxi.android_demo.pages.DriverDetailsPage;
import com.mytaxi.android_demo.pages.HomePage;
import com.mytaxi.android_demo.rules.RetryActivityTestRule;
import com.mytaxi.android_demo.utils.Constants;

import org.junit.Rule;
import org.junit.rules.TestName;

/**
 * This is the base test class. Common functions for all tests can be added here
 **/
public class BaseTest {

    @Rule
    public RetryActivityTestRule<MainActivity> mActivityRule = new RetryActivityTestRule<MainActivity>(MainActivity.class, true, true, Constants.RETRY_COUNT);

    @Rule public TestName currentTestName = new TestName();

    public AuthenticationPage authenticationPage = new AuthenticationPage();
    public HomePage homePage = new HomePage(mActivityRule);
    public DriverDetailsPage driverDetailsPage = new DriverDetailsPage();

    public void restartApp(){
        mActivityRule.finishActivity();
        mActivityRule.launchActivity(null);
    }

}
