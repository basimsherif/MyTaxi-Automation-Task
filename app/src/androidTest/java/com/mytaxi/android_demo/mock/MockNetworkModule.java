package com.mytaxi.android_demo.mock;

import com.mytaxi.android_demo.dependencies.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MockNetworkModule extends NetworkModule {

    @Singleton
    @Provides
    MockHttpClient provideHttpClient() {
        return new MockHttpClient();
    }

}
