package com.kunaalkumar.ignis.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kunaalkumar.ignis.fragments.FavoritesFragment;
import com.kunaalkumar.ignis.fragments.NewsFragment;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String FORMAT = "json";

    public static final String SHORTCUT_FAV = "shortcut_fav";

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            initShortcuts();
        }

        if (getIntent() != null) {
            Intent intent = getIntent();
            if (intent.getBooleanExtra(SHORTCUT_FAV, false)) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_fragmentholder, favoritesFragment)
                        .commit();

                bottomNavigationView.setSelectedItemId(R.id.bottom_navigation_favorites);
            }
        }
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

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    private void initShortcuts() {
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        Intent intentFav = new Intent(getBaseContext(), LaunchActivity.class);
        intentFav.putExtra(SHORTCUT_FAV, true);
        intentFav.setAction(Intent.ACTION_VIEW);

        ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(this, "Favorites")
                .setShortLabel("Favorites")
                .setLongLabel("Favorites")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_favorite_24dp))
                .setIntent(intentFav)
                .build();

        shortcutManager.addDynamicShortcuts(Arrays.asList(shortcutInfo));
    }
}
