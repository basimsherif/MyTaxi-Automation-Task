package com.mytaxi.android_demo;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.base.BaseTest;
import com.mytaxi.android_demo.model.User;
import com.mytaxi.android_demo.rules.RetryActivityTestRule;
import com.mytaxi.android_demo.utils.Constants;
import com.mytaxi.android_demo.utils.Utils;
import com.mytaxi.android_demo.utils.network.HttpClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * This test class contains all the test cases related to login functionality
 * Author:Basim Sherif
 **/
@RunWith(Parameterized.class)
public class LoginTests extends BaseTest {

    User user;
    String TAG = "Login tests";

    @Before
    public void setUp() throws Exception{
        if(homePage.ifHomePageOpened())
            homePage.logOut();
    }

    /**
     * This is how we pass test data to the test. We can add multiple users to the list.
     * Can be useful for stress testing with n number of users.
     * **/
    @Parameterized.Parameters
    public static Iterable<? extends Object> data() {
        User user1 = new User("whiteelephant261","video1");
        return Arrays.asList(user1);
    }

    public LoginTests(User mUser){
        user = mUser;
    }

    /**
     * This testcase will verify login
     * Test ID:02
     * Author:Basim Sherif
     * **/
    @Test
    public void verifyLogin() throws Exception {
        authenticationPage.login(user.getUsername(), user.getPassword());
        Utils.wait(2);
        homePage.assertHomePageOpened();
    }

    /**
     * This testcase will verify logout
     * Test ID:03
     * Author:Basim Sherif
     * **/
    @Test
    public void verifyLogout() throws Exception {
        authenticationPage.login(user.getUsername(), user.getPassword());
        Utils.wait(2);
        homePage.assertHomePageOpened();
        homePage.logOut();
        Utils.wait(2);
        authenticationPage.assertLoginPageOpened();
    }

    /**
     * This function will execute after each test cases. Can be used to clear memory/resources.
     * **/
    @After
    public void tearDown() throws Exception{
        //will check current running test name, so that we can run tear down for specific tests
        if(currentTestName.getMethodName().contains("verifyLogin")){
            //Will restart main activity
            Log.d(TAG, "Restarting Main Activity");
            mActivityRule.launchActivity(new Intent());
            if(homePage.ifHomePageOpened())
                homePage.logOut();
        }
    }


}
