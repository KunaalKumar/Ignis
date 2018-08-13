package com.kunaalkumar.ignis.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public interface SearchContract {

    interface MvpView {

        ViewPager getViewPager();

        TabLayout getTabLayout();

        EditText getSearchBox();

        ImageView getClearButton();

        RecyclerView getSearchHistoryView();

        RelativeLayout getSearchResultsView();
    }

    interface Presenter {
        void setUpViewPageAdapter(FragmentManager fragmentManager);

        void saveState(FragmentManager fragmentManager, Bundle bundle);

        void restoreState(FragmentManager fragmentManager, Bundle bundle);

        void initSearchBox();

        void handleIntent(Intent intent);

        void handleSearchBoxFocus(View v, boolean hasFocus);
    }
}
