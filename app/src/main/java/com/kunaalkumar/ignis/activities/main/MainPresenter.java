package com.kunaalkumar.ignis.activities.main;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kunaalkumar.ignis.BuildConfig;
import com.kunaalkumar.ignis.R;

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


    // App shortcut
    public static final String SHORTCUT_FAV = "com.kunaalkumar.ignis.shortcut.favorite";
    public static final String SHORTCUT_RANDOM = "com.kunaalkumar.ignis.shortcut.random";


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

    @Override
    public void initBottomNavigationBar(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.
                OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.bottom_navigation_news:
                        view.openNews();
                        return true;

                    case R.id.bottom_navigation_favorites:
                        view.openFavorite();
                        return true;

                    case R.id.bottom_navigation_random:
                        view.openRandom();
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void handleIntent(Intent intent) {
        if (SHORTCUT_FAV.equals(intent.getAction())) {
            view.changeFragment(R.id.bottom_navigation_favorites, view.getFavoriteFragment());
        }

        if (SHORTCUT_RANDOM.equals(intent.getAction())) {
            view.changeFragment(R.id.bottom_navigation_random, view.getRandomFragment());
        }
    }
}
