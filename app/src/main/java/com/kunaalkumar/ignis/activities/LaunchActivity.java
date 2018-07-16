/*
 * This activity is responsible for on-boarding the app icon whilst the rest of the app loads.
 *
 * Author: Kunaal Kumar
 * */


package com.kunaalkumar.ignis.activities;

import android.content.Intent;
import android.os.Bundle;

import com.kunaalkumar.ignis.utils.SharedPrefs;

import androidx.appcompat.app.AppCompatActivity;

import static com.kunaalkumar.ignis.activities.MainActivity.SHORTCUT_FAV;

public class LaunchActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        SettingsActivity.sharedPrefs = new SharedPrefs(this);

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);

        if (getIntent().getBooleanExtra(MainActivity.SHORTCUT_FAV, false)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(SHORTCUT_FAV, true);
            startActivity(intent);
            setIntent(null);
            finish();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
