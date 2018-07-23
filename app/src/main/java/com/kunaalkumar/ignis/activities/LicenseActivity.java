package com.kunaalkumar.ignis.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import net.yslibrary.licenseadapter.Library;
import net.yslibrary.licenseadapter.LicenseAdapter;
import net.yslibrary.licenseadapter.Licenses;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LicenseActivity extends AppCompatActivity {

    @BindView(R.id.license_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.license_back)
    ImageView backButton;

    List<Library> libraries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPrefs.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        ButterKnife.bind(this);

        populateLibraries();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LicenseAdapter(libraries));
    }

    @OnClick(R.id.license_back)
    public void onBack(View view) {
        onBackPressed();
    }

    private void populateLibraries() {
        libraries.add(Licenses.fromGitHubApacheV2("square/retrofit"));
        libraries.add(Licenses.fromGitHubApacheV2("square/picasso"));
        libraries.add(Licenses.noContent("Android SDK", "Google Inc.", "https://developer.android.com/sdk/terms.html"));
        libraries.add(Licenses.noContent("Crashlytics", "Google Inc.", "https://firebase.google.com/terms/crashlytics/"));
        libraries.add(Licenses.noContent("Firebase Performance Monitoring", "Google Inc.", "https://developers.google.com/terms/"));
        libraries.add(Licenses.noContent("Firebase Test Lab", "Google Inc.", "https://cloud.google.com/terms/"));
        libraries.add(Licenses.fromGitHubApacheV2("JakeWharton/butterknife"));
        libraries.add(Licenses.fromGitHubApacheV2("material-components/material-components-android"));
        libraries.add(Licenses.fromGitHubApacheV2("shalskar/PeekAndPop"));
        libraries.add(Licenses.fromGitHubApacheV2("yshrsmz/KeyboardVisibilityEvent"));
        libraries.add(Licenses.fromGitHubApacheV2("yshrsmz/LicenseAdapter"));
    }
}
