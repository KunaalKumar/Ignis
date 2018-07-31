package com.kunaalkumar.ignis.activities.main;

import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kunaalkumar.ignis.fragments.FavoritesFragment;
import com.kunaalkumar.ignis.fragments.NewsFragment;
import com.kunaalkumar.ignis.fragments.RandomFragment;

import androidx.fragment.app.Fragment;

/**
 * {@link MainActivity} Contract for MVP
 */
public interface MainContract {

    interface MvpView {

        void openNews();

        void openFavorite();

        void openRandom();

        void changeFragment(int id, Fragment fragment);

        NewsFragment getNewsFragment();

        FavoritesFragment getFavoriteFragment();

        RandomFragment getRandomFragment();
    }

    interface Presenter {

        void handleChangeTheme(int requestCode, int resultCode, Intent data);

        void initBottomNavigationBar(BottomNavigationView bottomNavigationView);

        void handleIntent(Intent intent);
    }
}
