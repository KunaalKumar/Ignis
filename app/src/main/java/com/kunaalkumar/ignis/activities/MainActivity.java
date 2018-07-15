package com.kunaalkumar.ignis.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kunaalkumar.ignis.fragments.FavoritesFragment;
import com.kunaalkumar.ignis.fragments.NewsFragment;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.utils.SharedPrefs;

public class MainActivity extends AppCompatActivity {

    public static final String FORMAT = "json";

    private NewsFragment newsFragment = new NewsFragment();
    private FavoritesFragment favoritesFragment = new FavoritesFragment();

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

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, newsFragment)
                .commit();

        initBottomNav();

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
                }
                return false;
            }
        });
    }
}
