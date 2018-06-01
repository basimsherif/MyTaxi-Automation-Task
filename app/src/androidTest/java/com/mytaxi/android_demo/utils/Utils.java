package com.mytaxi.android_demo.utils;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.uiautomator.UiDevice;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mytaxi.android_demo.models.Driver;

import org.hamcrest.Matcher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static com.mytaxi.android_demo.utils.CustomViewActions.waitForElement;

/**
 ** Utility functions class
 * */
public class Utils
{
    /**
     * This function is used to safely check if a view is visible or not.
     * @param viewInteraction view interaction need to check
     * @param viewAssert view assert need to check
     * @return will return true if its found, else return false

     **/
    public static boolean onViewCheckSafe(ViewInteraction viewInteraction, ViewAssertion viewAssert) {
        final boolean[] checkResult = new boolean[1];
        checkResult[0] = true;

        viewInteraction.withFailureHandler(new FailureHandler() {
            @Override
            public void handle(Throwable throwable, Matcher<View> matcher) {
                checkResult[0] = false;
            }
        }).check(viewAssert);
        return checkResult[0];
    }

    /**
     * This function is used to read and return String from json file
     * @param obj context
     * @param fileName file name
     * @return will return String

     **/
    public static String getStringFromFile(Object obj, String fileName) throws Exception {
        final InputStream stream = obj.getClass().getClassLoader().getResourceAsStream(fileName);
        String ret = convertStreamToString(stream);
        stream.close();
        return ret;
    }

    /**
     * Helper function for getStringFromFile
     * @param is input stream
     * @return will return String

     **/
    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static ArrayList<Driver> getDrivers(String jsonResponse) {
        JsonParser mJsonParser = new JsonParser();
        JsonObject jsonObject = mJsonParser.parse(jsonResponse).getAsJsonObject();
        JsonArray results = jsonObject.getAsJsonArray("results");
        ArrayList<Driver> drivers = new ArrayList<>();
        for (JsonElement jsonElement :results) {
            JsonObject jsonUser = jsonElement.getAsJsonObject();
            JsonObject name = jsonUser.getAsJsonObject("name");
            String firstName = name.get("first").getAsString();
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            String lastName = name.get("last").getAsString();
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            String fullName = firstName + " " + lastName;
            String phone = jsonUser.get("cell").getAsString();
            JsonObject picture = jsonUser.getAsJsonObject("picture");
            String avatar = picture.get("large").getAsString();
            JsonObject location = jsonUser.getAsJsonObject("location");
            String street = location.get("street").getAsString();
            String registered = jsonUser.get("registered").getAsString().split("\\s+")[0];
            Date registeredDate;
            try {
                registeredDate = new SimpleDateFormat("yyyy-MM-dd").parse(registered);
            } catch (ParseException e) {
                e.printStackTrace();
                registeredDate = new Date(0);
            }
            drivers.add(new Driver(fullName, phone, avatar, street, registeredDate));
        }
        return drivers;
    }

    /**
     * This function is used to perform back operation
     **/
    public static void pressBack(){
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressBack();
    }

    /**
     * This function is used to put delay between test steps
     * @param seconds Number seconds script needs to wait
     **/
    public static void wait(int seconds){
        onView(isRoot()).perform(waitForElement(seconds * 1000));
    }

}
