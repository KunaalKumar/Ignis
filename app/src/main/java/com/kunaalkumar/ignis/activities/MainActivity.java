package com.kunaalkumar.ignis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.fragments.FavoritesFragment;
import com.kunaalkumar.ignis.fragments.NewsFragment;
import com.kunaalkumar.ignis.fragments.RandomFragment;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    // Request code
    public static final Integer EXIT = 100;
    public static final String FORMAT = "json";

    public static final String SHORTCUT_FAV = "com.kunaalkumar.ignis.shortcut.favorite";
    public static final String SHORTCUT_RANDOM = "com.kunaalkumar.ignis.shortcut.random";

    // Api key for ComicVine
    public static final String API_KEY = "9aa1dc67801a2cdc8460790837f94b73057ce351";

    // Links
    public static final String GOOGLE_PLUS_URL = "https://plus.google.com/communities/117230352217222987710";
    public static final String REDDIT_URL = "https://www.reddit.com/r/ignisandroid";

    public static boolean CHANGED = false;

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
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, EXIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EXIT) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
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
}
