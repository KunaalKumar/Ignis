package com.kunaalkumar.ignis.activities.character;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CharacterActivity extends AppCompatActivity implements CharacterContract.MvpView {

    @BindView(R.id.character_toolbar)
    Toolbar toolbar;

    @BindView(R.id.character_fab)
    FloatingActionButton fab;

    @BindView(R.id.character_nestedScrollView)
    NestedScrollView nestedScrollView;

    /**
     * Layouts
     */
    @BindView(R.id.character_appbar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.character_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.character_deck_parent)
    MaterialCardView deckParentLayout;
    @BindView(R.id.character_parent_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.character_info_parent)
    LinearLayout infoParent;
    @BindView(R.id.character_general_info_parent)
    MaterialCardView generalInformationParentLayout;
    @BindView(R.id.character_comic_info_parent)
    MaterialCardView comicInfoParentLayout;

    /**
     * Card titles
     */
    @BindView(R.id.character_deck_title)
    TextView deckTitle;
    @BindView(R.id.character_gi_title)
    TextView generalInfoTitle;
    @BindView(R.id.character_comic_title)
    TextView comicInfoTitle;

    /**
     * Titles
     */
    @BindView(R.id.character_gi_real_name_title)
    TextView realNameTitle;
    @BindView(R.id.character_gi_aliases_title)
    TextView aliasesTitle;
    @BindView(R.id.character_gi_publisher_title)
    TextView publisherTitle;
    @BindView(R.id.character_gi_creators_title)
    TextView creatorsTitle;
    @BindView(R.id.character_gi_gender_title)
    TextView genderTitle;
    @BindView(R.id.character_gi_origin_title)
    TextView originTitle;
    @BindView(R.id.character_gi_birth_title)
    TextView birthdayTitle;

    /**
     * Information (to be populated)
     */
    @BindView(R.id.character_image)
    ImageView characterImage;
    @BindView(R.id.character_deck_text)
    TextView deckText;
    @BindView(R.id.character_deck_info)
    ImageView deckInfoIcon;
    @BindView(R.id.character_gi_real_name)
    TextView realName;
    @BindView(R.id.character_gi_aliases)
    TextView aliases;
    @BindView(R.id.character_gi_publisher)
    MaterialButton publisher;
    @BindView(R.id.character_gi_creators)
    RecyclerView creators;
    @BindView(R.id.character_gi_gender)
    TextView gender;
    @BindView(R.id.character_gi_origin)
    MaterialButton origin;
    @BindView(R.id.character_gi_birth)
    TextView birthday;


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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    protected void onPause() {
        getWindow().getDecorView().
                setSystemUiVisibility(0);
        super.onPause();
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

    @OnClick(R.id.character_gi_publisher)
    public void onClickPublisher(View view) {
        Toast.makeText(this, "Publisher info coming later", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.character_gi_origin)
    public void onClickOrigin(View view) {
        Toast.makeText(this, "Origin info coming later", Toast.LENGTH_LONG).show();
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
    public ImageView getCharacterImageView() {
        return characterImage;
    }

    @Override
    public TextView getCharacterDeckView() {
        return deckText;
    }

    @Override
    public MaterialCardView getCharacterDeckParentLayout() {
        return deckParentLayout;
    }

    @Override
    public ImageView getCharacterDeckInfoView() {
        return deckInfoIcon;
    }

    @Override
    public LinearLayout getCharacterInfoParentLayout() {
        return infoParent;
    }

    @Override
    public CoordinatorLayout getCharacterParentLayout() {
        return coordinatorLayout;
    }

    @Override
    public TextView getDeckTitleView() {
        return deckTitle;
    }

    @Override
    public TextView getRealNameTitleView() {
        return realNameTitle;
    }

    @Override
    public TextView getRealNameView() {
        return realName;
    }

    @Override
    public MaterialCardView getCharacterGeneralInformationParentLayout() {
        return generalInformationParentLayout;
    }

    @Override
    public TextView getAliasesTitleView() {
        return aliasesTitle;
    }

    @Override
    public TextView getAliasesView() {
        return aliases;
    }

    @Override
    public TextView getPublisherTitleView() {
        return publisherTitle;
    }

    @Override
    public MaterialButton getPublisherView() {
        return publisher;
    }

    @Override
    public TextView getCreatorsTitleView() {
        return creatorsTitle;
    }

    @Override
    public RecyclerView getCreatorsRecyclerView() {
        return creators;
    }

    @Override
    public TextView getGeneralInfoTitleView() {
        return generalInfoTitle;
    }

    @Override
    public TextView getComicInfoTitleView() {
        return comicInfoTitle;
    }

    @Override
    public MaterialCardView getComicInfoParentLayout() {
        return comicInfoParentLayout;
    }

    @Override
    public TextView getGenderTitleView() {
        return genderTitle;
    }

    @Override
    public TextView getGenderView() {
        return gender;
    }

    @Override
    public NestedScrollView getNestedScrollView() {
        return nestedScrollView;
    }

    @Override
    public TextView getOriginTitleView() {
        return originTitle;
    }

    @Override
    public MaterialButton getOriginView() {
        return origin;
    }

    @Override
    public TextView getBirthdayTitleView() {
        return birthdayTitle;
    }

    @Override
    public TextView getBirthdayView() {
        return birthday;
    }
}
