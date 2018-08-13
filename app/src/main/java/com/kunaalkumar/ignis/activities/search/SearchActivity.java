package com.kunaalkumar.ignis.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.tabs.TabLayout;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchContract.MvpView {


    @BindView(R.id.search_toolbar)
    Toolbar toolbar;

    private EditText searchBox;

    @BindView(R.id.search_back)
    ImageView backButton;

    @BindView(R.id.search_clear)
    ImageView clearButton;

    @BindView(R.id.search_tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.search_view_pager)
    ViewPager viewPager;

    @BindView(R.id.search_parent_layout)
    RelativeLayout coordinatorLayout;

    @BindView(R.id.search_history_view)
    RecyclerView searchHistory;

    @BindView(R.id.search_results_view)
    RelativeLayout searchResults;

    private SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d("Ignis", "SearchActivity started");
        ButterKnife.bind(this);

        presenter = new SearchPresenter(this);

        presenter.setUpViewPageAdapter(getSupportFragmentManager());

        searchBox = findViewById(R.id.search_searchBox);
        presenter.initSearchBox();

        presenter.handleIntent(getIntent());

        searchBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                presenter.handleSearchBoxFocus(v, hasFocus);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save character fragment instance
        presenter.saveState(getSupportFragmentManager(), outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        presenter.restoreState(getSupportFragmentManager(), savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        presenter.handleIntent(intent);
    }

    @OnClick(R.id.search_back)
    public void onSearchBackPressed(View view) {
        UIUtil.hideKeyboard(this);
        onBackPressed();
    }

    @OnClick(R.id.search_clear)
    public void onClearClick(View view) {
        searchBox.setText(null);
        UIUtil.showKeyboard(view.getContext(), searchBox);
    }

    @Override
    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public TabLayout getTabLayout() {
        return tabLayout;
    }

    @Override
    public ImageView getClearButton() {
        return clearButton;
    }

    @Override
    public EditText getSearchBox() {
        return searchBox;
    }

    @Override
    public RecyclerView getSearchHistoryView() {
        return searchHistory;
    }

    @Override
    public RelativeLayout getSearchResultsView() {
        return searchResults;
    }
}
