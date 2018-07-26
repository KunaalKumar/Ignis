package com.kunaalkumar.ignis.utils;

import android.app.Application;
import android.content.Context;


/*
 * This class exists strictly to give context to SharedPrefs
 *
 * ... sad life, I know...
 * */
public class Ignis extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        Ignis.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Ignis.context;
    }
}
