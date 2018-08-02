package com.kunaalkumar.ignis.activities.character;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kunaalkumar.ignis.adapters.SearchCharacterAdapter.shareUrlIntent;

public class CharacterActivity extends AppCompatActivity implements CharacterContract.MvpView {

    @BindView(R.id.character_image)
    ImageView characterImage;

    @BindView(R.id.character_toolbar)
    Toolbar toolbar;

    @BindView(R.id.character_appbar_layout)
    AppBarLayout appBarLayout;

    @BindView(R.id.character_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.character_fab)
    FloatingActionButton fab;

    private CharacterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ButterKnife.bind(this);

        presenter = new CharacterPresenter(this, getIntent());

        presenter.searchCall(getIntent());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.resetDrawableColors();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    @OnClick(R.id.character_fab)
    public void onClickFab(View view) {
        presenter.fabClicked();
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public AppBarLayout getAppBarLayout() {
        return appBarLayout;
    }

    @Override
    public CollapsingToolbarLayout getCollapsingToolbarLayout() {
        return collapsingToolbarLayout;
    }

    @Override
    public FloatingActionButton getFab() {
        return fab;
    }

    @Override
    public ImageView getCharacterImage() {
        return characterImage;
    }
}
