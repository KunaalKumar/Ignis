package com.kunaalkumar.ignis.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kunaalkumar.ignis.R;

public class MainActivity extends AppCompatActivity {

    public static final String FORMAT = "json";

    // Api key for ComicVine
    public static final String API_KEY = "9aa1dc67801a2cdc8460790837f94b73057ce351";

    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.settings)
    ImageView settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.search)
    public void searchOnClick(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @OnClick(R.id.settings)
    public void settingsOnClick(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
