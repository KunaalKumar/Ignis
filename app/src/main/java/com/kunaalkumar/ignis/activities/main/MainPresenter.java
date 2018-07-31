package com.kunaalkumar.ignis.activities.main;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kunaalkumar.ignis.BuildConfig;

import static android.app.Activity.RESULT_OK;

/**
 * Responsible for handling actions from the view and updating the UI as required
 */
public class MainPresenter implements MainContract.Presenter {

    public static FirebaseAnalytics mFirebaseAnalytics;

    // Request code
    public static final Integer EXIT = 100;
    public static final String FORMAT = "json";

    // Api key for ComicVine
    public static final String API_KEY = BuildConfig.ComicVineApiKey;

    // Links
    public static final String GOOGLE_PLUS_URL = "https://plus.google.com/communities/117230352217222987710";
    public static final String REDDIT_URL = "https://www.reddit.com/r/ignisandroid";
    public static final String PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.kunaalkumar.ignis";

    // Tells the Settings Activity whether or not to change thme
    public static boolean CHANGED = false;

    private MainContract.MvpView view;

    public MainPresenter(MainContract.MvpView view) {
        this.view = view;

        mFirebaseAnalytics = FirebaseAnalytics.getInstance((Activity) view);
    }

    @Override
    public void handleChangeTheme(int requestCode, int resultCode, Intent data) {
        if (requestCode == EXIT) {
            if (resultCode == RESULT_OK) {
                ((Activity) view).finish();
            }
        }
    }
}
