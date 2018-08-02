package com.kunaalkumar.ignis.activities.search;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

public interface SearchContract {

    interface MvpView {

        ViewPager getViewPager();

        TabLayout getTabLayout();
    }

    interface Presenter {
        void setUpViewPageAdapter(FragmentManager fragmentManager);
    }
}
