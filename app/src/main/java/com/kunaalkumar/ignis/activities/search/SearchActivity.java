package com.kunaalkumar.ignis.activities.search;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.fragments.search.SearchCharacterFragment;
import com.kunaalkumar.ignis.fragments.search.SearchIssueFragment;
import com.kunaalkumar.ignis.fragments.search.SearchObjectFragment;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
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
    CoordinatorLayout coordinatorLayout;

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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
            Toast.makeText(this, "keyboard hidden", Toast.LENGTH_SHORT).show();
        }
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
}
