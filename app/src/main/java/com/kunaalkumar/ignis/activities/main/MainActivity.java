package com.kunaalkumar.ignis.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.activities.search.SearchActivity;
import com.kunaalkumar.ignis.activities.settings.SettingsActivity;
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

        mainPresenter.initBottomNavigationBar(bottomNavigationView);

        if (!SharedPrefs.getDarkThemeState()) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

        mainPresenter.handleIntent(getIntent());
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

    @Override
    public void openNews() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, newsFragment)
                .commit();
    }

    @Override
    public void openFavorite() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, favoritesFragment)
                .commit();
    }

    @Override
    public void openRandom() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, randomFragment)
                .commit();
    }

    @Override
    public void changeFragment(int id, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, fragment)
                .commit();

        bottomNavigationView.setSelectedItemId(id);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    public FavoritesFragment getFavoriteFragment() {
        return favoritesFragment;
    }

    @Override
    public RandomFragment getRandomFragment() {
        return randomFragment;
    }

    @Override
    public NewsFragment getNewsFragment() {
        return newsFragment;
    }
}
