package com.kunaalkumar.ignis.activities.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kunaalkumar.ignis.BuildConfig;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.activities.SearchActivity;
import com.kunaalkumar.ignis.activities.SettingsActivity;
import com.kunaalkumar.ignis.fragments.FavoritesFragment;
import com.kunaalkumar.ignis.fragments.NewsFragment;
import com.kunaalkumar.ignis.fragments.RandomFragment;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kunaalkumar.ignis.activities.main.MainPresenter.EXIT;
import static com.kunaalkumar.ignis.activities.main.MainPresenter.mFirebaseAnalytics;

/**
 * Displays main screen with news, favorites and random fragments
 */
public class MainActivity extends AppCompatActivity implements MainContract.MvpView {

    public static final String SHORTCUT_FAV = "com.kunaalkumar.ignis.shortcut.favorite";
    public static final String SHORTCUT_RANDOM = "com.kunaalkumar.ignis.shortcut.random";

    private MainPresenter mainPresenter;

    private NewsFragment newsFragment = new NewsFragment();
    private FavoritesFragment favoritesFragment = new FavoritesFragment();
    private RandomFragment randomFragment = new RandomFragment();

    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.settings)
    ImageView settings;

    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, newsFragment)
                .commit();

        initBottomNav();

        if (SHORTCUT_FAV.equals(getIntent().getAction())) {
            changeBottom(R.id.bottom_navigation_favorites, favoritesFragment);
        }

        if (SHORTCUT_RANDOM.equals(getIntent().getAction())) {
            changeBottom(R.id.bottom_navigation_random, randomFragment);
        }
    }

    // handle bottom navigation events
    private void initBottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.bottom_navigation_news:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_fragmentholder, newsFragment)
                                .commit();
                        return true;

                    case R.id.bottom_navigation_favorites:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_fragmentholder, favoritesFragment)
                                .commit();
                        return true;

                    case R.id.bottom_navigation_random:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_fragmentholder, randomFragment)
                                .commit();
                        return true;

                    default:
                        Toast.makeText(MainActivity.this, "Something went terribly wrong", Toast.LENGTH_LONG).show();
                        return false;
                }
            }
        });
    }

    private void changeBottom(int id, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, fragment)
                .commit();

        bottomNavigationView.setSelectedItemId(id);
    }

    @OnClick(R.id.search)
    public void searchOnClick(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @OnClick(R.id.settings)
    public void settingsOnClick(View view) {

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "settings_click");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Open Settings");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "button");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        Intent intent = new Intent(this, SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, EXIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mainPresenter.handleChangeTheme(requestCode, resultCode, data);
    }
}
