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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kunaalkumar.ignis.BottomBarFragment;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String FORMAT = "json";

    private List<BottomBarFragment> fragments = new ArrayList<>(2);

    private static final String TAG_FRAGMENT_NEWS = "tag_frag_news";
    private static final String TAG_FRAGMENT_FAVORITES = "tag_favorites";

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

        initBottomNav();

        switchFragment(0, TAG_FRAGMENT_NEWS);

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
        buildFragmentsList();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_navigation_news:
                        switchFragment(0, TAG_FRAGMENT_NEWS);
                        return true;
                    case R.id.bottom_navigation_favorites:
                        switchFragment(1, TAG_FRAGMENT_FAVORITES);
                        return true;
                }
                return false;
            }
        });
    }

    // Switch fragment in frame layout
    private void switchFragment(int pos, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, fragments.get(pos), tag)
                .commit();
    }

    // Builds fragments to list
    private void buildFragmentsList() {
        BottomBarFragment callsFragment = buildFragment("News");
        BottomBarFragment recentsFragment = buildFragment("Favorites");

        fragments.add(callsFragment);
        fragments.add(recentsFragment);
    }

    // Returns BottomBarFragment whilst passing title as argument
    private BottomBarFragment buildFragment(String title) {
        BottomBarFragment fragment = new BottomBarFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BottomBarFragment.ARG_NAME, title);
        fragment.setArguments(bundle);
        return fragment;
    }
}
