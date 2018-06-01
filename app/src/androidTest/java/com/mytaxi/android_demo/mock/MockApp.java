package com.mytaxi.android_demo.mock;

import android.content.Context;

import com.mytaxi.android_demo.App;
import com.mytaxi.android_demo.activities.AuthenticatedActivity;
import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.dependencies.component.AppComponent;
import com.mytaxi.android_demo.dependencies.component.DaggerAppComponent;
import com.mytaxi.android_demo.dependencies.module.NetworkModule;
import com.mytaxi.android_demo.dependencies.module.PermissionModule;
import com.mytaxi.android_demo.dependencies.module.SharedPrefStorageModule;

import javax.inject.Singleton;

import dagger.Component;

public class MockApp extends App {

    private MockAppComponent mAppComponent;

    @Singleton
    @Component(modules = {MockNetworkModule.class, PermissionModule.class, SharedPrefStorageModule.class})
    public interface MockAppComponent extends AppComponent {

        void inject(AuthenticatedActivity activity);

        void inject(MainActivity activity);

        void inject(AuthenticationActivity activity);

    }

    public static MockApp getApplicationContext(Context context) {
        return (MockApp) context.getApplicationContext();
    }

    @Override
    public MockAppComponent getAppComponent() {
        return mAppComponent;
    }

}
