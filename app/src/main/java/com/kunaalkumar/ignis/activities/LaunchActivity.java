/*
* This activity is responsible for on-boarding the app icon whilst the rest of the app loads.
*
* Author: Kunaal Kumar
* */


package com.kunaalkumar.ignis.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
