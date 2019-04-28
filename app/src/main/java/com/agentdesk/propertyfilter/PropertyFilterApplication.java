package com.agentdesk.propertyfilter;

import android.app.Application;

import io.realm.Realm;

public class PropertyFilterApplication extends Application {

    private static PropertyFilterApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
        Realm.init(this);
    }

    private static void init(PropertyFilterApplication app) {
        sInstance = app;
    }
}