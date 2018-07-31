package com.kunaalkumar.ignis.activities.main;

import android.content.Intent;

/**
 * {@link MainActivity} Contract for MVP
 */
public interface MainContract {

    interface MvpView {

    }

    interface Presenter {
        void handleChangeTheme(int requestCode, int resultCode, Intent data);
    }
}
