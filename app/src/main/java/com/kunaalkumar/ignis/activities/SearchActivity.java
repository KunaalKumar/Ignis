package com.kunaalkumar.ignis.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.kunaalkumar.ignis.R;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_back)
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d("Ignis", "SearchActivity started");
        ButterKnife.bind(this);
    }

    @OnClick(R.id.search_back)
    public void backButtonOnClick(View view) {
        onBackPressed();
    }
}
