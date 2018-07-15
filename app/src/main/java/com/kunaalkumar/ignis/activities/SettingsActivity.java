package com.kunaalkumar.ignis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.utils.SharedPrefs;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.settings_back)
    ImageView backButton;

    @BindView(R.id.theme_button)
    SwitchCompat nightMode;

    @BindView(R.id.settings_libraries)
    ImageView licenses;

    @BindView(R.id.version_number)
    TextView versionNumber;

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

        versionNumber.setText(getVersionName());
    }

    // Return version name from app.gradle
    private String getVersionName() {
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "Not found";
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

    @OnClick(R.id.settings_libraries)
    public void onLicensesPressed() {
        startActivity(new Intent(this, LicenseActivity.class));
    }
}
