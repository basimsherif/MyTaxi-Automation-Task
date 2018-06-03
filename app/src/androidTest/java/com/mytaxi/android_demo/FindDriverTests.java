package com.mytaxi.android_demo;

import android.util.Log;

import com.mytaxi.android_demo.base.BaseTest;
import com.mytaxi.android_demo.model.User;
import com.mytaxi.android_demo.models.Driver;
import com.mytaxi.android_demo.utils.Utils;
import com.mytaxi.android_demo.utils.network.HttpClient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * This test class contains all the test cases related to find driver functionality
 **/
@RunWith(Parameterized.class)
public class FindDriverTests extends BaseTest {

    User user;
    String TAG = "Find driver tests";
    private MockWebServer server;
    String RANDOM_USER_URL;

    @Before
    public void setUp() throws Exception{
        RANDOM_USER_URL =  HttpClient.RANDOM_USER_URL;
        server = new MockWebServer();
        server.start();
        allowPermissionsIfNeeded();
        //Not logged in to the app. So, we will perform login.
        if(authenticationPage.ifAuthenticationPageOpened()){
            authenticationPage.login(user.getUsername(), user.getPassword());
        }

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

    public FindDriverTests(User mUser){
        user = mUser;
    }

    /**
     * This testcase will verify driver details screen
     * Test ID:01
     * Author:Basim Sherif
     * **/
    @Test
    public void verifyDriverDetailsScreen() throws Exception {
        HttpClient.RANDOM_USER_URL = server.url("/").toString();
        String fileName = "sample.json";
        //We use mocked API service to verify the data is displayed
        // properly in driver details screen by passing a sample json file (located in resource folder) as response.
        String jsonBody = Utils.getStringFromFile(this, fileName);
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(jsonBody));
        homePage.assertHomePageOpened();
        homePage.tapLocationButton();
        homePage.searchAndTapDriver("sa","Sarah Friedrich");
        ArrayList<Driver> driverList = Utils.getDrivers(jsonBody);
        driverDetailsPage.verifyDriverDetailsPage(driverList.get(65));
        driverDetailsPage.verifyCallIntent(driverList.get(65).getPhone());

    }

    /**
     * This function will execute after each test cases. Can be used to clear memory/resources.
     * **/
    @After
    public void tearDown() throws Exception{
        //will check current running test name, so that we can run tear down for specific tests
        if(currentTestName.getMethodName().contains("verifyDriverDetailsScreen")){
            //Will restart main activity
            Log.d(TAG, "Restarting Main Activity");
            server.shutdown();
            HttpClient.RANDOM_USER_URL =  RANDOM_USER_URL;

        }
    }


}
