package com.kunaalkumar.ignis.activities.settings;

import android.app.Activity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.widget.SwitchCompat;

/**
 * {@link SettingsActivity} Contract for MVP
 */
public interface SettingsContract {

    interface MvpView {

        CollapsingToolbarLayout getCollapsingToolbar();

        SwitchCompat getNightModeSwitch();

        SwitchCompat getPreviewImageQualitySwitch();

        SeekBar getSeekBar();

        TextView getSeekBarMaxView();

        TextView getSeekBarCurrentView();

        TextView getVersionNumberView();

        SwitchCompat getDynamicCardColorSwitch();
    }

    interface Presenter {

        void setState();

        void setDarkThemeState(boolean state);

        void setDynamicCardColorState(boolean state);

        void setPeekHighResImageState(boolean state);

        void initSeekbar();

        void onSeekBarProgressChange(int currentVal);

        void setSearchHistorySize();

        View.OnClickListener onClickLicense(final Activity activity);

        View.OnClickListener onClickGooglePlus(final Activity activity);

        View.OnClickListener onClickReddit(final Activity activity);

        View.OnClickListener onClickPlayStore(final Activity activity);

        void setVersionNumber();

        boolean returnBack();
    }
}
