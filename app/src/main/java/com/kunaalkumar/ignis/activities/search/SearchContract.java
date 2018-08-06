package com.kunaalkumar.ignis.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

public interface SearchContract {

    interface MvpView {

        ViewPager getViewPager();

        TabLayout getTabLayout();

        EditText getSearchBox();

        ImageView getClearButton();
    }

    interface Presenter {
        void setUpViewPageAdapter(FragmentManager fragmentManager);

        void saveState(FragmentManager fragmentManager, Bundle bundle);

        void restoreState(FragmentManager fragmentManager, Bundle bundle);

        void initSearchBox();

        void handleIntent(Intent intent);
    }
}
