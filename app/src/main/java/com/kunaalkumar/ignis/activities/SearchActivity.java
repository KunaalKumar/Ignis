package com.kunaalkumar.ignis.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.kunaalkumar.ignis.R;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_back)
    ImageView backButton;

    @BindView(R.id.search_searchBox)
    TextInputEditText searchBox;

    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d("Ignis", "SearchActivity started");
        ButterKnife.bind(this);

        // Request focus on searchBox and pull up keyboard
        searchBox.requestFocus();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @OnClick(R.id.search_back)
    public void backButtonOnClick(View view) {

        // Hide keyboard
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        onBackPressed();
    }
}
