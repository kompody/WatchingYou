package com.yurentsy.watchingyou;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.yurentsy.watchingyou.mvp.di.AppComponent;
import com.yurentsy.watchingyou.mvp.di.DaggerAppComponent;

import io.paperdb.Paper;

public class App extends Application {
    private static App instance;
    private AppComponent component;

    public static App getInstance() {
        return instance;
    }

    public AppComponent getComponent() {
        if (component == null) {
            component = DaggerAppComponent.builder()
                    .build();
        }
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Paper.init(this);

        Stetho.initializeWithDefaults(this);
    }
}
