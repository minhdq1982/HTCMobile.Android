package com.tvo.htc;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by ThinhNH on 12/14/2018.
 */
public class HTCApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (!BuildConfig.DEBUG) { // only enable bug tracking in release version
            Fabric.with(this, new Crashlytics());
        }
    }
}
