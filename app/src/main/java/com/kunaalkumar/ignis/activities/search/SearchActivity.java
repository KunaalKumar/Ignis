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

    private static EditText searchBox;

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

        init();
        appLinkCall();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // Save character fragment instance
        getSupportFragmentManager().putFragment(outState, "characterFragment", characterFragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore character fragment instance
        characterFragment = (SearchCharacterFragment) getSupportFragmentManager().
                getFragment(savedInstanceState, "characterFragment");
    }

    private void init() {

        searchBox = findViewById(R.id.search_searchBox);

        // Request focus on searchBox and pull up keyboard
        searchBox.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // Listener for on "enter" pressed
        searchBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != KeyEvent.ACTION_DOWN)
                    return true;
                switch (i) {
                    case KeyEvent.KEYCODE_ENTER:
                        characterFragment.searchCall(SearchActivity.this,
                                searchBox.getText().toString().trim(),
                                true);
                        keyboardState(false, null);
                        viewPager.requestFocus();
                        break;
                }
                return true;
            }
        });

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Called right before text is changed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(searchBox.getText())) {
                    clearButton.setVisibility(View.VISIBLE);
                } else {
                    clearButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Called right after text is changed
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        appLinkCall();
    }

    // Handle app link
    private void appLinkCall() {
        Intent appLinkIntent = getIntent();
        Uri appLinkData = appLinkIntent.getData();

        if (appLinkData != null) {
            searchBox.setText(appLinkData.getLastPathSegment().trim());
            characterFragment.searchCall(SearchActivity.this,
                    appLinkData.getLastPathSegment().trim(),
                    true);
        }
    }

    private void keyboardState(boolean state, View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (state) {
            view.requestFocus();
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        } else {
            imm.hideSoftInputFromWindow(findViewById(R.id.search_parent_layout).getWindowToken(),
                    0);
        }

    }

    @OnClick(R.id.search_back)
    public void onSearchBackPressed(View view) {
        keyboardState(false, null);
        onBackPressed();
    }

    @OnClick(R.id.search_clear)
    public void onClearClick(View view) {
        searchBox.setText(null);
        keyboardState(true, searchBox);
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
}
