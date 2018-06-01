package com.mytaxi.android_demo.mock;

import com.google.gson.JsonParser;
import com.mytaxi.android_demo.utils.network.HttpClient;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static com.mytaxi.android_demo.misc.Constants.SOCKET_TIMEOUT;

public class MockHttpClient extends HttpClient {

    public static String RANDOM_USER_URL = "https://randomuser.me/api/";
    private final OkHttpClient mClient;
    private final JsonParser mJsonParser;

    public MockHttpClient() {
        mClient = new OkHttpClient.Builder().readTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS).build();
        mJsonParser = new JsonParser();
    }

}
