package com.mytaxi.android_demo.pages;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.models.Driver;
import com.mytaxi.android_demo.utils.EspressoMatchers;
import com.mytaxi.android_demo.utils.Utils;

import junit.framework.Assert;

import java.text.SimpleDateFormat;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

/**
 *  This class contains all the functions related to Driver details page
 **/
public class DriverDetailsPage extends BasePage {

    String TAG = "Driver details page";
    ActivityTestRule mActivityTestRule;


    /**
     * This function is used to verify driver details page
     * @param driver Driver details
     **/
    public void verifyDriverDetailsPage(Driver driver){
        Log.d(TAG, "Verifying driver name");
        onView(withId(R.id.textViewDriverName)).check(matches(withText(driver.getName())));

        Log.d(TAG, "Verifying driver location");
        onView(withId(R.id.textViewDriverLocation)).check(matches(withText(driver.getLocation())));

        Log.d(TAG, "Verifying driver date");
        onView(withId(R.id.textViewDriverDate)).check(matches(withText(new SimpleDateFormat("yyyy-MM-dd").format(driver.getRegisteredDate()))));

        Log.d(TAG, "Verifying location icon set properly");
        onView(withId(R.id.imageViewDriverLocation)).check(matches(EspressoMatchers.withDrawable(R.drawable.ic_location)));

        Log.d(TAG, "Verifying date icon set properly");
        onView(withId(R.id.imageViewDriverDate)).check(matches(EspressoMatchers.withDrawable(R.drawable.ic_date)));
    }

    /**
     * This function is used to tap login button
     **/
    public void tapCallButton(){
        Log.d(TAG, "Taping call button");
        onView(withId(R.id.fab)).perform(click());
    }


}
