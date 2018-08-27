package com.kunaalkumar.ignis.utils;


import android.app.Activity;

import com.kunaalkumar.ignis.R;

import java.util.ArrayList;
import java.util.Collections;

public class SharedPrefs {

    public static final String KEY_SEARCH_HISTORY = "SearchHistory";
    public static final String KEY_FAVORITE_CHARACTERS = "FavoriteCharacters";
    public static final String KEY_DARK_THEME = "DarkTheme";
    public static final String KEY_SEARCH_HISTORY_SIZE = "SearchHistorySize";
    public static final String KEY_PEEK_HIGH_RES_IMAGE = "PeekHighResImage";
    public static final String KEY_DYNAMIC_CARD_COLOR = "DynamicCardColor";
    public static final String KEY_IMAGE_WIDTH = "ImageWidth";

    public static final Integer ABSOLUTE_MAX_SEARCH_HISTORY = 50;

    private static ArrayList<String> searchHistory;
    private static ArrayList<Integer> favoriteCharacters;
    private static TinyDB tinyDB;
    private static int maxArraySize;

    static {
        tinyDB = new TinyDB(Ignis.getAppContext());
        searchHistory = tinyDB.getListString(KEY_SEARCH_HISTORY);
        favoriteCharacters = tinyDB.getListInt(KEY_FAVORITE_CHARACTERS);

        maxArraySize = tinyDB.getInt(KEY_SEARCH_HISTORY_SIZE);

//        TODO: handle migrating from short list to long list, and vice-versa
        if (maxArraySize <= 0) {
            maxArraySize = 25;
            tinyDB.putInt(KEY_SEARCH_HISTORY_SIZE, maxArraySize);
        }
        if (searchHistory.size() > maxArraySize) {
            reduceList(maxArraySize);
        }
    }

    public static Integer getSearchHistorySize() {
        return maxArraySize;
    }

    public static void setSearchHistorySize(int size) {
        if (size < searchHistory.size()) {
            reduceList(size);
        }
        maxArraySize = size;
        tinyDB.putInt(KEY_SEARCH_HISTORY_SIZE, size);
    }

    private static void reduceList(int size) {
        searchHistory.subList(0, searchHistory.size() - size).clear();
    }

    public static void applyTheme(Activity activity) {

        if (SharedPrefs.getDarkThemeState()) {
            activity.setTheme(R.style.DarkTheme);
        } else activity.setTheme(R.style.LightTheme);

    }

    public static void addToFavoriteCharacters(Integer id) {
        favoriteCharacters.add(id);
        tinyDB.putListInt(KEY_FAVORITE_CHARACTERS, favoriteCharacters);
    }

    public static void addToSearchHistory(String search) {
        search = search.trim();
        if (search.equals("")) {
            return;
        }

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
        } else {
            for (int i = searchHistory.indexOf(search); i < searchHistory.size() - 1; i++) {
                Collections.swap(searchHistory, i, i + 1);
                tinyDB.putListString(KEY_SEARCH_HISTORY, searchHistory);
            }
        }
    }

    public static void replaceSearchHistory(ArrayList<String> newList) {
        searchHistory = new ArrayList<>(newList);
        tinyDB.putListString(KEY_SEARCH_HISTORY, searchHistory);
    }

    public static void removeFromSearchHistory(String search) {
        searchHistory.remove(search);
        tinyDB.putListString(KEY_SEARCH_HISTORY, searchHistory);
    }

    public static ArrayList<String> getSearchHistory() {

        ArrayList<String> toReturn = new ArrayList<>(searchHistory);

        Collections.reverse(toReturn);
        return toReturn;
    }

    public static void addFavoriteCharacter(Integer id) {
        favoriteCharacters.add(id);
        tinyDB.putListInt(KEY_FAVORITE_CHARACTERS, favoriteCharacters);
    }

    public static void removeFavoriteCharacter(Integer id) {
        favoriteCharacters.remove(id);
        tinyDB.putListInt(KEY_FAVORITE_CHARACTERS, favoriteCharacters);
    }

    public static ArrayList<Integer> getFavoriteCharacters() {
        return favoriteCharacters;
    }

    public static Boolean getDarkThemeState() {
        return tinyDB.getBoolean(KEY_DARK_THEME);
    }

    public static void setDarkThemeState(Boolean state) {
        tinyDB.putBoolean(KEY_DARK_THEME, state);
    }

    public static Boolean getPeekHighResImageState() {
        return tinyDB.getBoolean(KEY_PEEK_HIGH_RES_IMAGE);
    }

    public static void setPeekHighResImageState(Boolean state) {
        tinyDB.putBoolean(KEY_PEEK_HIGH_RES_IMAGE, state);
    }

    public static Boolean getDynamicCardColorState() {
        return tinyDB.getBoolean(KEY_DYNAMIC_CARD_COLOR);
    }

    public static void setDynamicCardColorState(Boolean state) {
        tinyDB.putBoolean(KEY_DYNAMIC_CARD_COLOR, state);
    }

    public static boolean getImageWidthState() {
        return tinyDB.getBoolean(KEY_IMAGE_WIDTH, true);
    }

    public static void setKeyImageWidth(boolean state) {
        tinyDB.putBoolean(KEY_IMAGE_WIDTH, state);
    }

}