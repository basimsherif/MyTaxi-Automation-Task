package com.mytaxi.android_demo.pages;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.utils.Utils;

import junit.framework.Assert;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
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
     * This function is used to search driver
     * @param driverName Name of the driver to be searched.
     **/
    public void searchDriver(String searchQuery, String driverName){
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
     * This function is used to verify text in auto complete text view
     * @param driverName Name of the driver to be verified in auto complete
     **/
    public void verifyTextInAutoCompleteList(String driverName){
        Log.d(TAG, "Verifying text "+driverName+" is present in list");
        onView(withText(driverName))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     * This function will tap back button
     **/
    public void tapBackButton(){
        Log.d(TAG, "Tapping navigation up button");
        if(Utils.onViewCheckSafe(onView(withContentDescription("Navigate up")), matches(isDisplayed()))){
            onView(withContentDescription("Navigate up")).perform(click());
        }else{
            Log.d(TAG, "If the button is not available, we will simply call pressBack function");
            Utils.pressBack();
        }
    }

    /**
     * This function will perform logout operation
     **/
    public void logOut(){
        Log.d(TAG, "Tapping hamburger button");
        onView(withContentDescription(R.string.navigation_drawer_open)).perform(click());
        Log.d(TAG, "Tapping logout button");
        onView(withText(R.string.text_item_title_logout)).perform(click());
    }


}
