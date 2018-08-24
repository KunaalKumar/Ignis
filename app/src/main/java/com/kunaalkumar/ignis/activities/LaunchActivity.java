/*
 * This activity is responsible for on-boarding the app icon whilst the rest of the app loads.
 *
 * Author: Kunaal Kumar
 * */


package com.kunaalkumar.ignis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kunaalkumar.ignis.activities.main.MainActivity;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import androidx.appcompat.app.AppCompatActivity;


public class LaunchActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
