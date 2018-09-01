package com.kunaalkumar.ignis.activities.search;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
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

    @BindView(R.id.search_parent_layout)
    RelativeLayout coordinatorLayout;

    @BindView(R.id.search_history_view)
    RecyclerView searchHistory;

    @BindView(R.id.search_recycler_view)
    RecyclerView searchRecyclerView;

    @BindView(R.id.search_progress)
    ProgressBar progressBar;

    private SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d("Ignis", "SearchActivity started");
        ButterKnife.bind(this);

        presenter = new SearchPresenter(this);

        searchBox = findViewById(R.id.search_searchBox);
        presenter.initSearchBox();

        presenter.handleIntent(getIntent());

        if (!SharedPrefs.getDarkThemeState()) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            searchBox.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    presenter.showHistory(hasFocus);

                }
            });
        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.search_clear)
    public void onClearClick(View view) {
        searchBox.setText(null);
        UIUtil.showKeyboard(view.getContext(), searchBox);
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
    public RecyclerView getSearchRecyclerView() {
        return searchRecyclerView;
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
