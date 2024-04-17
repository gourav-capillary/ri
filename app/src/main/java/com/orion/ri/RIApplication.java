package com.orion.ri;


import android.app.Application;
import android.content.Context;

public class RIApplication extends Application {

    public static Context context;

    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }


}