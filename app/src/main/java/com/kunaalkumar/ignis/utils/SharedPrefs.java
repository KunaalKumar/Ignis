package com.kunaalkumar.ignis.utils;


import android.app.Activity;
import android.content.Context;

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.activities.SettingsActivity;

import java.util.ArrayList;
import java.util.Collections;

public class SharedPrefs {

    public static final String KEY_SEARCH_HISTORY = "SearchHistory";
    public static final String KEY_DARK_THEME = "DarkTheme";
    public static final String KEY_SEARCH_HISTORY_SIZE = "SearchHistorySize";
    public static ArrayList<String> searchHistory;
    private static TinyDB tinyDB;
    private static int maxArraySize;

    public SharedPrefs(Context context) {
        tinyDB = new TinyDB(context);
        searchHistory = tinyDB.getListString(KEY_SEARCH_HISTORY);
        maxArraySize = tinyDB.getInt(KEY_SEARCH_HISTORY_SIZE);

//        TODO: handle migrating from short list to long list, and vice-versa
        if (maxArraySize <= 0) {
            maxArraySize = 10;
            tinyDB.putInt(KEY_SEARCH_HISTORY_SIZE, maxArraySize);
        }
    }

    public static int getSearchHistorySize() {
        return maxArraySize;
    }

    public static void setSearchHistorySize(int size) {
        maxArraySize = size;
        tinyDB.putInt(KEY_SEARCH_HISTORY_SIZE, size);
    }

    public static void applyTheme(Activity activity) {
        if (SettingsActivity.sharedPrefs.getDarkThemeState()) {
            activity.setTheme(R.style.DarkTheme);
        } else activity.setTheme(R.style.LightTheme);
    }

    public static void addToSearchHistory(String search) {

        if (!searchHistory.contains(search)) {
            if (searchHistory.size() >= maxArraySize) {
                // Remove first element; shift elements up; add new element to the end
                for (int i = 0; i < searchHistory.size() - 1; i++) {
                    searchHistory.set(i, searchHistory.get(i + 1));
                }
                searchHistory.set(searchHistory.size() - 1, search);

                tinyDB.putListString(KEY_SEARCH_HISTORY, searchHistory);
            } else {
                searchHistory.add(search);
                tinyDB.putListString(KEY_SEARCH_HISTORY, searchHistory);
            }
        }
    }

    public static ArrayList<String> getSearchHistory() {

        ArrayList<String> toReturn = new ArrayList<>(searchHistory);

        Collections.reverse(toReturn);
        return toReturn;
    }

    public Boolean getDarkThemeState() {
        return tinyDB.getBoolean(KEY_DARK_THEME);
    }

    public void setDarkThemeState(Boolean state) {
        tinyDB.putBoolean(KEY_DARK_THEME, state);
    }
}