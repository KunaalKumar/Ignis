package com.kunaalkumar.ignis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.SharedPrefs;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.settings_back)
    ImageView backButton;

    @BindView(R.id.theme_button)
    SwitchCompat nightMode;

    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPrefs = new SharedPrefs(this);
        if (sharedPrefs.getDarkThemeState()) {
            setTheme(R.style.DarkTheme);
        } else setTheme(R.style.LightTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        if (sharedPrefs.getDarkThemeState()) {
            nightMode.setChecked(true);
        }

        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    sharedPrefs.setDarkThemeState(true);
                    restart();
                } else {
                    sharedPrefs.setDarkThemeState(false);
                    restart();
                }
            }
        });
    }

    @OnClick(R.id.settings_back)
    public void onBackButtonPressed(View view) {
        onBackPressed();
    }

    private void restart() {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
        finish();
    }

}
