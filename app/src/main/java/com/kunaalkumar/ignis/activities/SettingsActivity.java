package com.kunaalkumar.ignis.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.settings_parent_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.theme_switch)
    SwitchCompat nightMode;

    @BindView(R.id.peek_image_quality_switch)
    SwitchCompat previewImageQuality;

    @BindView(R.id.settings_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    /*
     * Seekbar
     * */
    @BindView(R.id.slider_search_history)
    AppCompatSeekBar seekBar;
    @BindView(R.id.slider_search_history_current_val)
    TextView currentSeekBarVal;
    @BindView(R.id.slider_search_history_max_val)
    TextView maxSeekBarVal;

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

    /*
     * Reddit
     * */
    @BindView(R.id.settings_reddit_image)
    ImageView redditImage;
    @BindView(R.id.settings_reddit_text)
    TextView redditText;

    /*
     * Play store
     * */
    @BindView(R.id.settings_play_store_image)
    ImageView playStoreImage;
    @BindView(R.id.settings_play_store_text)
    TextView playStoreText;

    @BindView(R.id.version_number)
    TextView versionNumber;


    public static SharedPrefs sharedPrefs;

    private Integer possibleSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (sharedPrefs.getDarkThemeState()) {
            setTheme(R.style.DarkTheme_Settings);
        } else setTheme(R.style.LightTheme_Settings);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        collapsingToolbarLayout.setTitleEnabled(true);
        if (sharedPrefs.getDarkThemeState()) {
            collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        }

        nightMode.setChecked(sharedPrefs.getDarkThemeState());
        previewImageQuality.setChecked(SharedPrefs.getPeekHighResImageState());

        init();

        setVersionName();

        setClickListeners();
    }

    private void init() {
        // Init night mode switch
        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                sharedPrefs.setDarkThemeState(isChecked);
                restartOnThemeChanged();
            }
        });

        // Init preview image quality switch
        previewImageQuality.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                SharedPrefs.setPeekHighResImageState(isChecked);
//                Snackbar.make(findViewById(android.R.id.content), Boolean.toString(isChecked),
//                        Snackbar.LENGTH_SHORT).show();
            }
        });

        // Init seekbar
        seekBar.setMax(SharedPrefs.ABSOLUTE_MAX_SEARCH_HISTORY);
        maxSeekBarVal.setText(SharedPrefs.ABSOLUTE_MAX_SEARCH_HISTORY.toString());
        seekBar.setProgress(sharedPrefs.getSearchHistorySize());
        currentSeekBarVal.setText(sharedPrefs.getSearchHistorySize().toString());
        possibleSize = sharedPrefs.getSearchHistorySize();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i < 2) {
                    Toast.makeText(SettingsActivity.this, "Minimum value is 2", Toast.LENGTH_LONG).show();

                    seekBar.setProgress(2);
                    possibleSize = 2;
                } else {
                    possibleSize = i;
                }
                currentSeekBarVal.setText(possibleSize.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(SettingsActivity.this, "Started touching ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(SettingsActivity.this, "Set to " + seekBar.getProgress(), Toast.LENGTH_LONG).show();
                sharedPrefs.setSearchHistorySize(possibleSize);
            }
        });
    }

    private void setClickListeners() {

        // License
        licenseImage.setOnClickListener(licenseOnClick());
        licenseText.setOnClickListener(licenseOnClick());

        // Google plus
        googlePlusImage.setOnClickListener(googlePlusOnClick());
        googlePlusText.setOnClickListener(googlePlusOnClick());

        // Reddit
        redditImage.setOnClickListener(redditOnClick());
        redditText.setOnClickListener(redditOnClick());

        // Play store
        playStoreImage.setOnClickListener(playStoreOnClick());
        playStoreText.setOnClickListener(playStoreOnClick());

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

    @Override
    public boolean onSupportNavigateUp() {
        if (MainActivity.CHANGED) {
            MainActivity.CHANGED = false;
            startActivity(new Intent(SettingsActivity.this, MainActivity.class));
        } else {
            super.onBackPressed();
        }
        finish();
        return true;
    }

    /*




    .___              .__
    |   | ____   ____ |__| ______
    |   |/ ___\ /    \|  |/  ___/
    |   / /_/  >   |  \  |\___ \
    |___\___  /|___|  /__/____  >
       /_____/      \/        \/


                onClick listeners




     */

    // Returns onClick listener for license
    private View.OnClickListener licenseOnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, LicenseActivity.class));
            }
        };
    }

    // Returns onClick listener for google plus
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

    // Returns onClick listener for reddit
    private View.OnClickListener redditOnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(MainActivity.REDDIT_URL));
                startActivity(i);
            }
        };
    }

    // Returns onClick listener for play store
    private View.OnClickListener playStoreOnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(MainActivity.PLAY_STORE_URL));
                startActivity(i);
            }
        };
    }
}
