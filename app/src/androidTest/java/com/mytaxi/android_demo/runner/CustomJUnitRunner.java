package com.mytaxi.android_demo.runner;

import android.app.Activity;
import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.runner.lifecycle.ActivityLifecycleCallback;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import static android.view.WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
import static android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
import static android.view.WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;


/**
 *  A custom JUnit Runner class which will make sure that the screen will be turned ON throughout the test execution
 **/
public class CustomJUnitRunner extends AndroidJUnitRunner {

    @Override public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

        ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(new ActivityLifecycleCallback() {
            @Override public void onActivityLifecycleChanged(Activity activity, Stage stage) {
                if (stage == Stage.PRE_ON_CREATE) {
                    activity.getWindow().addFlags(FLAG_DISMISS_KEYGUARD | FLAG_TURN_SCREEN_ON | FLAG_KEEP_SCREEN_ON);
                }
            }
        });
    }
}
