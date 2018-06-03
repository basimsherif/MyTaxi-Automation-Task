package com.mytaxi.android_demo.base;

import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.pages.AuthenticationPage;
import com.mytaxi.android_demo.pages.BasePage;
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

    /**
     * This will handle runtime permission dialogs
     **/
    public void allowPermissionsIfNeeded()  {
        if (Build.VERSION.SDK_INT >= 23) {
            UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            UiObject allowPermissions = mDevice.findObject(new UiSelector().text("Allow"));
            if (allowPermissions.exists()) {
                try {
                    allowPermissions.click();
                } catch (UiObjectNotFoundException e) {
                }
            }
        }
    }

}
