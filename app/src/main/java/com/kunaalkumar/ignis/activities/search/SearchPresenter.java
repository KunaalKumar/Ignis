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

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.adapters.SearchHistoryAdapter;
import com.kunaalkumar.ignis.adapters.SearchResultAdapter;
import com.kunaalkumar.ignis.network.FetchSearchResults;
import com.kunaalkumar.ignis.utils.SharedPrefs;
import com.kunaalkumar.ignis.web_scraper.SearchResult;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchPresenter implements SearchContract.Presenter {

    public static String CHARACTER_STATE_KEY = "CHARACTER_STATE_KEY";
    ArrayList<SearchResult> results = new ArrayList<>();

    SearchContract.MvpView view;
    Activity activity;

    private SearchHistoryAdapter historyAdapter;
    private RecyclerView recyclerView;
    private SearchResultAdapter adapter;

    public static PeekAndPop peekAndPop;


    public SearchPresenter(SearchContract.MvpView view) {
        this.view = view;
        this.activity = (Activity) view;
        recyclerView = view.getSearchRecyclerView();

        historyAdapter = new SearchHistoryAdapter(SharedPrefs.getSearchHistory(), this);
        view.getSearchHistoryView().setAdapter(historyAdapter);
        view.getSearchHistoryView().setLayoutManager(new LinearLayoutManager(activity));

        initPeekAndPop();
        showHistory(true);
    }

    private void initPeekAndPop() {
        // Basic init for peekAndPop
        peekAndPop = new PeekAndPop.Builder(activity)
                .peekLayout(R.layout.peek_preview)
                .parentViewGroupToDisallowTouchEvents(view.getSearchRecyclerView())
                .build();
    }

    @Override
    public void saveState(FragmentManager fragmentManager, Bundle bundle) {
//        fragmentManager.putFragment(bundle, CHARACTER_STATE_KEY, characterFragment);
    }

    @Override
    public void restoreState(FragmentManager fragmentManager, Bundle bundle) {
        // Restore character fragment instance
//        characterFragment = (SearchCharacterFragment) fragmentManager.
//                getFragment(bundle, CHARACTER_STATE_KEY);
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
        UIUtil.hideKeyboard(activity);
        historyAdapter.searchHistory = SharedPrefs.getSearchHistory();
        historyAdapter.notifyDataSetChanged();
        showHistory(false);
        view.getSearchBox().setText(query.trim());

        // Search call
        new FetchSearchResults(this, query, view.getProgressBar()).execute();
    }

    public void populateSearchResults(ArrayList<SearchResult> list, String query) {
        results.addAll(list);
        view.getSearchRecyclerView().requestFocus();
        view.getSearchRecyclerView().setAdapter(adapter);
        adapter = new SearchResultAdapter((SearchActivity) activity, peekAndPop, query, results);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
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
            view.getSearchRecyclerView().setVisibility(View.INVISIBLE);
        } else {
            view.getSearchHistoryView().setVisibility(View.INVISIBLE);
            view.getSearchRecyclerView().setVisibility(View.VISIBLE);
        }
    }
}
