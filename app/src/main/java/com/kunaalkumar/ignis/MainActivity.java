package com.kunaalkumar.ignis;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Displays main screen with news, favorites and random fragments
 */
public class MainActivity extends AppCompatActivity {

    public static FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

}
