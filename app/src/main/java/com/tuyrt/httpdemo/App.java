package com.tuyrt.httpdemo;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by futao on 2017/10/27.
 */

public class App extends Application {

    private static App app;

    public static final String ad = "1231c";
    public static Context getContext() {
        return app.getApplicationContext();
    }

    public static String getMac() {
        return "sad";
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Stetho.initializeWithDefaults(this);
    }
}
