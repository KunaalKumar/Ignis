package com.kunaalkumar.ignis.activities.settings;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.activities.LicenseActivity;
import com.kunaalkumar.ignis.activities.main.MainActivity;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import static android.app.Activity.RESULT_OK;
import static com.kunaalkumar.ignis.activities.main.MainPresenter.CHANGED;

public class SettingsPresenter implements SettingsContract.Presenter {


    // Links
    public static final String GOOGLE_PLUS_URL = "https://plus.google.com/communities/117230352217222987710";
    public static final String REDDIT_URL = "https://www.reddit.com/r/ignisandroid";
    public static final String PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.kunaalkumar.ignis";


    SettingsContract.MvpView view;

    private Integer possibleSize;

    SettingsPresenter(SettingsContract.MvpView view) {
        this.view = view;
    }

    // Get current state from shared prefs and set it
    @Override
    public void setState() {

        view.getCollapsingToolbar().setTitleEnabled(true);

        if (SharedPrefs.getDarkThemeState()) {
            view.getCollapsingToolbar().setCollapsedTitleTextColor(Color.WHITE);
        }

        view.getNightModeSwitch().setChecked(SharedPrefs.getDarkThemeState());
        view.getDynamicCardColorSwitch().setChecked(SharedPrefs.getDynamicCardColorState());
        view.getPreviewImageQualitySwitch().setChecked(SharedPrefs.getPeekHighResImageState());
        view.getImageWidthSwitch().setChecked(SharedPrefs.getImageWidthState());
    }

    @Override
    public void setDarkThemeState(boolean state) {
        SharedPrefs.setDarkThemeState(state);
        restartOnThemeChanged();
    }

    @Override
    public void setPeekHighResImageState(boolean state) {
        SharedPrefs.setPeekHighResImageState(state);
    }

    @Override
    public void initSeekbar() {
        // Init seekbar
        view.getSeekBar().setMax(SharedPrefs.ABSOLUTE_MAX_SEARCH_HISTORY);
        view.getSeekBarMaxView().setText(SharedPrefs.ABSOLUTE_MAX_SEARCH_HISTORY.toString());
        view.getSeekBar().setProgress(SharedPrefs.getSearchHistorySize());
        view.getSeekBarCurrentView().setText(SharedPrefs.getSearchHistorySize().toString());

        possibleSize = SharedPrefs.getSearchHistorySize();
    }

    /**
     * Called when seekbar progress is changed
     * Min value is 2;
     */
    @Override
    public void onSeekBarProgressChange(int currentVal) {
        if (currentVal < 2) {
            Toast.makeText((Activity) view, "Minimum value is 2", Toast.LENGTH_LONG).show();

            view.getSeekBar().setProgress(2);
            possibleSize = 2;
        } else {
            possibleSize = currentVal;
        }
        view.getSeekBarCurrentView().setText(possibleSize.toString());
    }

    /**
     * Restart and change theme
     */
    private void restartOnThemeChanged() {
        CHANGED = true;
        ((Activity) view).setResult(RESULT_OK, null);
        ((Activity) view).finish();

        Intent intent = new Intent((Activity) view, SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ((Activity) view).startActivity(intent);
    }

    @Override
    public void setSearchHistorySize() {
        SharedPrefs.setSearchHistorySize(possibleSize);
    }

    @Override
    public View.OnClickListener onClickLicense(final Activity activity) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(
                        new Intent(activity, LicenseActivity.class));
            }
        };
    }

    @Override
    public View.OnClickListener onClickGooglePlus(final Activity activity) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(GOOGLE_PLUS_URL));
                activity.startActivity(i);
            }
        };
    }

    @Override
    public View.OnClickListener onClickReddit(final Activity activity) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(REDDIT_URL));
                activity.startActivity(i);
            }
        };
    }

    @Override
    public View.OnClickListener onClickPlayStore(final Activity activity) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(PLAY_STORE_URL));
                activity.startActivity(i);
            }
        };
    }

    /**
     * Returns version name from app.gradle
     */
    @Override
    public void setVersionNumber() {
        try {
            PackageInfo pInfo = ((Activity) view).getPackageManager()
                    .getPackageInfo(((Activity) view).getPackageName(), 0);
            view.getVersionNumberView().setText(pInfo.versionName);
            return;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        view.getVersionNumberView().setText(R.string.error_name_nf);
    }

    /**
     * @return true if theme was changed, false else
     */
    @Override
    public boolean returnBack() {
        if (CHANGED) {
            CHANGED = false;
            ((Activity) view).startActivity(new Intent(((Activity) view), MainActivity.class));
            return true;
        }
        ((Activity) view).finish();
        return false;
    }

    @Override
    public void setDynamicCardColorState(boolean state) {
        SharedPrefs.setDynamicCardColorState(state);
    }

    @Override
    public void setImageWidth(boolean state) {
        SharedPrefs.setKeyImageWidth(state);
    }
}
