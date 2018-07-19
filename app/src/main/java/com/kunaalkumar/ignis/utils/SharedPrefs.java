package com.kunaalkumar.ignis.utils;

import android.app.Activity;
import android.content.Context;

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.activities.SettingsActivity;

public class SharedPrefs {

    TinyDB tinyDB;

    public SharedPrefs(Context context) {
        tinyDB = new TinyDB(context);
    }

    public Boolean getDarkThemeState() {
        return tinyDB.getBoolean("DarkTheme");
    }

    public void setDarkThemeState(Boolean state) {
        tinyDB.putBoolean("DarkTheme", state);
    }

    public static void applyTheme(Activity activity) {
        if (SettingsActivity.sharedPrefs.getDarkThemeState()) {
            activity.setTheme(R.style.DarkTheme);
        } else activity.setTheme(R.style.LightTheme);
    }
}