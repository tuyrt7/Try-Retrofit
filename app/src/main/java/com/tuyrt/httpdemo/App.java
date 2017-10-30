package com.tuyrt.httpdemo;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by futao on 2017/10/27.
 */

public class App extends Application {

    private static App app;

    public static Context getContext() {
        return app.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Stetho.initializeWithDefaults(this);
    }
}
