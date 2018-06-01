package com.mytaxi.android_demo.utils;

import android.view.View;

import org.hamcrest.Matcher;

/**
 * Custom matchers for Espresso
 */
public class EspressoMatchers {

    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    public static Matcher<View> noDrawable() {
        return new DrawableMatcher(-1);
    }


}
