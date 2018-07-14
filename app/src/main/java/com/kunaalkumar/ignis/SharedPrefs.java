package com.kunaalkumar.ignis;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.kunaalkumar.ignis.activities.SettingsActivity;

public class SharedPrefs {

    SharedPreferences preferences;

    public SharedPrefs(Context context) {
        preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }

    public void setDarkThemeState(Boolean state) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("DarkTheme", state);
        editor.commit();
    }

    public Boolean getDarkThemeState() {
        return preferences.getBoolean("DarkTheme", false);
    }

    public static void applyTheme(Activity activity) {
        if (SettingsActivity.sharedPrefs.getDarkThemeState()) {
            activity.setTheme(R.style.DarkTheme);
        } else activity.setTheme(R.style.LightTheme);
    }
}