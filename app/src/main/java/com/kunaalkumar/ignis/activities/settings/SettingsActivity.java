package com.kunaalkumar.ignis.activities.settings;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

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


public class SettingsActivity extends AppCompatActivity implements SettingsContract.MvpView {

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

    private SettingsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        presenter = new SettingsPresenter(this);

        presenter.setState();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenter.initSeekbar();
        presenter.setVersionNumber();
        setListeners();

    }

    private void setListeners() {
        // Night mode switch listener
        nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                presenter.setDarkThemeState(isChecked);
            }
        });

        // Preview image quality switch listener
        previewImageQuality.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                presenter.setPeekHighResImageState(isChecked);
            }
        });

        // Seekbar change listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                presenter.onSeekBarProgressChange(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(SettingsActivity.this, "Started touching ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                presenter.setSearchHistorySize();
            }
        });

        // License
        licenseImage.setOnClickListener(presenter.onClickLicense(this));
        licenseText.setOnClickListener(presenter.onClickLicense(this));

        // Google plus
        googlePlusImage.setOnClickListener(presenter.onClickGooglePlus(this));
        googlePlusText.setOnClickListener(presenter.onClickGooglePlus(this));

        // Reddit
        redditImage.setOnClickListener(presenter.onClickReddit(this));
        redditText.setOnClickListener(presenter.onClickReddit(this));

        // Play store
        playStoreImage.setOnClickListener(presenter.onClickPlayStore(this));
        playStoreText.setOnClickListener(presenter.onClickPlayStore(this));
    }

    @Override
    public void onBackPressed() {
        if (!presenter.returnBack()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!presenter.returnBack()) {
            super.onBackPressed();
        }
        return true;
    }

    @Override
    public CollapsingToolbarLayout getCollapsingToolbar() {
        return collapsingToolbarLayout;
    }

    @Override
    public SwitchCompat getNightModeSwitch() {
        return nightMode;
    }

    @Override
    public SwitchCompat getPreviewImageQualitySwitch() {
        return previewImageQuality;
    }

    @Override
    public SeekBar getSeekBar() {
        return seekBar;
    }

    @Override
    public TextView getSeekBarMaxView() {
        return maxSeekBarVal;
    }

    @Override
    public TextView getSeekBarCurrentView() {
        return currentSeekBarVal;
    }

    @Override
    public TextView getVersionNumberView() {
        return versionNumber;
    }
}
