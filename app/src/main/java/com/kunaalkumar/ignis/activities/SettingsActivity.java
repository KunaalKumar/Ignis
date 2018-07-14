package com.kunaalkumar.ignis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
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

    @BindView(R.id.licenses)
    CardView licenses;

    public static SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                    restartOnThemeChanged();
                } else {
                    sharedPrefs.setDarkThemeState(false);
                    restartOnThemeChanged();
                }
            }
        });
    }

    private void restartOnThemeChanged() {
        startActivity(new Intent(this, SettingsActivity.class));
        finish();
    }

    @OnClick(R.id.settings_back)
    public void onBackButtonPressed(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.licenses)
    public void onLicensesPressed() {
        startActivity(new Intent(this, LicenseActivity.class));
    }
}
