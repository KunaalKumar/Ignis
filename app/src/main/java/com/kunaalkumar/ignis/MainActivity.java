package com.kunaalkumar.ignis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // Access token for superheroapi.com
    private String SUPERHERO_ACCESS_TOKEN = "10205048573171973";
    private String BASE_URL = "http://superheroapi.com/api/" + SUPERHERO_ACCESS_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
