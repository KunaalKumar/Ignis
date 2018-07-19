package com.kunaalkumar.ignis.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.settings_back)
    ImageView backButton;

    @BindView(R.id.theme_button)
    SwitchCompat nightMode;

    /*
     * License
     * */
    @BindView(R.id.settings_libraries)
    ImageView licenseImage;
    @BindView(R.id.settings_licenses_text)
    TextView licenseText;

    /*
     * Google plus community
     * */
    @BindView(R.id.settings_google_plus_image)
    ImageView googlePlusImage;
    @BindView(R.id.settings_google_plus_text)
    TextView googlePlusText;

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

        setVersionName();

        setClickListeners();

    }

    private void setClickListeners() {

        // License
        licenseImage.setOnClickListener(licenseOnClick());
        licenseText.setOnClickListener(licenseOnClick());

        // Google plus
        googlePlusImage.setOnClickListener(googlePlusOnClick());
        googlePlusText.setOnClickListener(googlePlusOnClick());

    }

    // Returns onClick listener for license
    private View.OnClickListener licenseOnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, LicenseActivity.class));
            }
        };
    }

    private View.OnClickListener googlePlusOnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(MainActivity.GOOGLE_PLUS_URL));
                startActivity(i);
            }
        };
    }

    // Return version name from app.gradle
    private void setVersionName() {
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            versionNumber.setText(pInfo.versionName);
            return;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        versionNumber.setText(R.string.error_name_nf);
    }

    private void restartOnThemeChanged() {
        MainActivity.CHANGED = true;
        setResult(RESULT_OK, null);
        finish();
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @OnClick(R.id.settings_back)
    public void onBackButtonPressed(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (MainActivity.CHANGED) {
            MainActivity.CHANGED = false;
            startActivity(new Intent(SettingsActivity.this, MainActivity.class));
        } else {
            super.onBackPressed();
        }
        finish();
    }
}
