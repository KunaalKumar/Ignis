package com.kunaalkumar.ignis.activities.search;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.kunaalkumar.ignis.adapters.SearchHistoryAdapter;
import com.kunaalkumar.ignis.adapters.ViewPageAdapter;
import com.kunaalkumar.ignis.fragments.search.SearchCharacterFragment;
import com.kunaalkumar.ignis.fragments.search.SearchIssueFragment;
import com.kunaalkumar.ignis.fragments.search.SearchObjectFragment;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

public class SearchPresenter implements SearchContract.Presenter {

    public static String CHARACTER_STATE_KEY = "CHARACTER_STATE_KEY";

    SearchContract.MvpView view;
    Activity activity;

    // Fragments for ViewPager
    private SearchCharacterFragment characterFragment;
    private SearchIssueFragment issueFragment;
    private SearchObjectFragment objectFragment;

    private SearchHistoryAdapter historyAdapter;

    ViewPageAdapter viewPageAdapter;

    public SearchPresenter(SearchContract.MvpView view) {
        this.view = view;
        this.activity = (Activity) view;

        characterFragment = new SearchCharacterFragment();
        issueFragment = new SearchIssueFragment();
        objectFragment = new SearchObjectFragment();

        showHistory(true);
    }

    public void setUpViewPageAdapter(FragmentManager fragmentManager) {

        viewPageAdapter = new ViewPageAdapter(fragmentManager);

        viewPageAdapter.addFragment(characterFragment, SearchCharacterFragment.TITLE);
        viewPageAdapter.addFragment(issueFragment, SearchIssueFragment.TITLE);
        viewPageAdapter.addFragment(objectFragment, SearchObjectFragment.TITLE);

        // Keeps all fragments in memory
        view.getViewPager().setOffscreenPageLimit(viewPageAdapter.getCount());

        view.getViewPager().setAdapter(viewPageAdapter);

        view.getTabLayout().setupWithViewPager(view.getViewPager());

        historyAdapter = new SearchHistoryAdapter(SharedPrefs.getSearchHistory(), this);
        view.getSearchHistoryView().setAdapter(historyAdapter);
        view.getSearchHistoryView().setLayoutManager(new LinearLayoutManager(activity));
    }

    @Override
    public void saveState(FragmentManager fragmentManager, Bundle bundle) {
        fragmentManager.putFragment(bundle, CHARACTER_STATE_KEY, characterFragment);
    }

    @Override
    public void restoreState(FragmentManager fragmentManager, Bundle bundle) {
        // Restore character fragment instance
        characterFragment = (SearchCharacterFragment) fragmentManager.
                getFragment(bundle, CHARACTER_STATE_KEY);
    }

    @Override
    public void initSearchBox() {
        // Request focus on searchBox and pull up keyboard
        view.getSearchBox().requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // Listener for on "enter" pressed
        view.getSearchBox().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View _view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                    switch (i) {
                        case KeyEvent.KEYCODE_ENTER:
                            searchCall(view.getSearchBox().getText().toString().trim());
                            break;
                        case KeyEvent.KEYCODE_BACK:
                            activity.onBackPressed();
                    }
                return false;
            }
        });

        view.getSearchBox().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Called right before text is changed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(view.getSearchBox().getText())) {
                    view.getClearButton().setVisibility(View.VISIBLE);
                } else {
                    view.getClearButton().setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Called right after text is changed
            }
        });
    }

    public void searchCall(String query) {

        SharedPrefs.addToSearchHistory(query);
        historyAdapter.searchHistory = SharedPrefs.getSearchHistory();
        historyAdapter.notifyDataSetChanged();

        showHistory(false);
        view.getSearchBox().setText(query.trim());
        UIUtil.hideKeyboard(activity, view.getSearchBox());
        view.getSearchBox().clearFocus();
        view.getViewPager().requestFocus();
        characterFragment.searchCall(query,
                true);
    }

    // Handle app link
    @Override
    public void handleIntent(Intent intent) {
        Uri appLinkData = intent.getData();

        if (appLinkData != null) {
            searchCall(
                    appLinkData.getLastPathSegment().trim());
        }
    }

    @Override
    public void showHistory(boolean show) {
        if (show) {
            view.getSearchHistoryView().setVisibility(View.VISIBLE);
            view.getSearchResultsView().setVisibility(View.INVISIBLE);
        } else {
            view.getSearchHistoryView().setVisibility(View.INVISIBLE);
            view.getSearchResultsView().setVisibility(View.VISIBLE);
        }
    }
}
