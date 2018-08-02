package com.kunaalkumar.ignis.activities.search;

import android.app.Activity;

import com.kunaalkumar.ignis.adapters.ViewPageAdapter;
import com.kunaalkumar.ignis.fragments.search.SearchCharacterFragment;
import com.kunaalkumar.ignis.fragments.search.SearchIssueFragment;
import com.kunaalkumar.ignis.fragments.search.SearchObjectFragment;

import androidx.fragment.app.FragmentManager;

public class SearchPresenter implements SearchContract.Presenter {

    SearchContract.MvpView view;
    Activity activity;

    // Fragments for ViewPager
    private SearchCharacterFragment characterFragment;
    private SearchIssueFragment issueFragment;
    private SearchObjectFragment objectFragment;

    ViewPageAdapter viewPageAdapter;

    public SearchPresenter(SearchContract.MvpView view) {
        this.view = view;
        this.activity = (Activity) view;
    }

    public void setUpViewPageAdapter(FragmentManager fragmentManager) {

        viewPageAdapter = new ViewPageAdapter(fragmentManager);

        characterFragment = new SearchCharacterFragment();
        issueFragment = new SearchIssueFragment();
        objectFragment = new SearchObjectFragment();

        viewPageAdapter.addFragment(characterFragment, SearchCharacterFragment.TITLE);
        viewPageAdapter.addFragment(issueFragment, SearchIssueFragment.TITLE);
        viewPageAdapter.addFragment(objectFragment, SearchObjectFragment.TITLE);

        // Keeps all fragments in memory
        view.getViewPager().setOffscreenPageLimit(viewPageAdapter.getCount());

        view.getViewPager().setAdapter(viewPageAdapter);

        view.getTabLayout().setupWithViewPager(view.getViewPager());
    }
}
