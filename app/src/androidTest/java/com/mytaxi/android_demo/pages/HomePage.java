package com.mytaxi.android_demo.pages;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.utils.Utils;

import junit.framework.Assert;

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
 *  This class contains all the functions related to Home page
 **/
public class HomePage extends BasePage {

    String TAG = "Home page";
    ActivityTestRule mActivityTestRule;

    public HomePage(ActivityTestRule activityTestRule){
        mActivityTestRule = activityTestRule;
    }

    /**
     * This function is used to verify if home page is opened
     **/
    public boolean ifHomePageOpened(){
        return Utils.onViewCheckSafe(onView(withId(R.id.textSearch)), matches(isDisplayed()));
    }

    /**
     * This function is used to assert home page is opened
     **/
    public void assertHomePageOpened(){
        Log.d(TAG, "Verify if home page is opened");
        Assert.assertTrue("Home page not opened",ifHomePageOpened());
    }

    /**
     * This function is used to assert home page not opened
     **/
    public void assertHomePageNotOpened(){
        Log.d(TAG, "Verify if home page not opened");
        Assert.assertFalse("Home page opened",ifHomePageOpened());
    }

    /**
     * This function is used to search driver
     * @param driverName Name of the driver to be searched.
     **/
    public void searchAndTapDriver(String searchQuery, String driverName){
        assertHomePageOpened();
        Log.d(TAG, "Type driver name query in edit text");
        setTextInAutoComplete(searchQuery);
        Log.d(TAG, "Tap driver name in populated list");
        onView(withText(driverName))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .perform(click());
    }

    /**
     * This function is used to set text in auto complete text view
     * @param searchQuery search query to be set in auto complete
     **/
    public void setTextInAutoComplete(String searchQuery){
        Log.d(TAG, "Setting text "+searchQuery+" in auto complete text view");
        onView(withId(R.id.textSearch)).perform(click());
        onView(withId(R.id.textSearch)).perform(typeText(searchQuery));
    }

    /**
     * This function is used to tap location button
     **/
    public void tapLocationButton(){
        Log.d(TAG, "Taping location button");
        onView(withId(R.id.fab)).perform(click());
    }

    /**
     * This function will perform logout operation
     **/
    public void logOut(){
        Log.d(TAG, "Tapping hamburger button");
        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        Utils.pressBack();
        Utils.wait(1);
        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        Log.d(TAG, "Tapping logout button");
        onView(withText(R.string.text_item_title_logout)).perform(click());
    }


}
