package com.kunaalkumar.ignis;

import android.content.Context;
import android.content.SharedPreferences;

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
}