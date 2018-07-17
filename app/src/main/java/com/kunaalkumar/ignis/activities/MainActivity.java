package com.kunaalkumar.ignis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kunaalkumar.ignis.fragments.FavoritesFragment;
import com.kunaalkumar.ignis.fragments.NewsFragment;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.fragments.RandomFragment;
import com.kunaalkumar.ignis.utils.SharedPrefs;

public class MainActivity extends AppCompatActivity {

    public static final String FORMAT = "json";

    public static final String SHORTCUT_FAV = "com.kunaalkumar.ignis.shortcut.favorite";
    public static final String SHORTCUT_RANDOM = "com.kunaalkumar.ignis.shortcut.random";

    private NewsFragment newsFragment = new NewsFragment();
    private FavoritesFragment favoritesFragment = new FavoritesFragment();
    private RandomFragment randomFragment = new RandomFragment();


    // Api key for ComicVine
    public static final String API_KEY = "9aa1dc67801a2cdc8460790837f94b73057ce351";

    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.settings)
    ImageView settings;

    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (SettingsActivity.sharedPrefs == null) {
            SettingsActivity.sharedPrefs = new SharedPrefs(this);
        }

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
        startActivity(new Intent(this, SettingsActivity.class));
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
}
