package com.kunaalkumar.ignis.activities.character;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.adapters.RelationAdapter;
import com.kunaalkumar.ignis.comicvine_objects.long_description.ApiResponse;
import com.kunaalkumar.ignis.comicvine_objects.long_description.Character;
import com.kunaalkumar.ignis.network.ApiClient;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.core.graphics.ColorUtils;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.kunaalkumar.ignis.activities.main.MainPresenter.API_KEY;
import static com.kunaalkumar.ignis.activities.main.MainPresenter.FORMAT;
import static com.kunaalkumar.ignis.adapters.SearchCharacterAdapter.EXTRA_ID;
import static com.kunaalkumar.ignis.adapters.SearchCharacterAdapter.EXTRA_NAME;
import static com.kunaalkumar.ignis.adapters.SearchCharacterAdapter.EXTRA_URL_HD;
import static com.kunaalkumar.ignis.adapters.SearchCharacterAdapter.EXTRA_URL_STD;
import static com.kunaalkumar.ignis.adapters.SearchCharacterAdapter.shareUrlIntent;

public class CharacterPresenter implements CharacterContract.Presenter {

    CharacterContract.MvpView view;
    Activity activity;
    Intent intent;

    // Drawables
    Drawable shareDrawable;
    Drawable infoDrawable;
    Drawable backDrawable;

    // Information t0 be fetched from API
    private String field_list = "name," +
            "site_detail_url," +
            "image," +
            "deck," +
            "real_name," +
            "aliases," +
            "publisher," +
            "creators," +
            "gender," +
            "origin," +
            "birth";

    private Character character;

    private RecyclerView creatorRecylerView;
    private RelationAdapter creatorAdapter;


    public CharacterPresenter(CharacterContract.MvpView view, Intent intent) {
        this.view = view;
        activity = ((Activity) view);
        this.intent = intent;


        view.getCollapsingToolbarLayout().setTitleEnabled(true);
        view.getCollapsingToolbarLayout().setExpandedTitleColor(Color.WHITE);

        shareDrawable = activity.getResources().getDrawable(R.drawable.ic_share_24dp);
        infoDrawable = activity.getResources().getDrawable(R.drawable.ic_info_24dp);
        backDrawable = activity.getResources().getDrawable(R.drawable.ic_arrow_back_24dp);

        showTitleOnCollapse();

        activity.getWindow().getDecorView().
                setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    // Retrofit call to search character for given id
    public void searchCall(Intent intent) {

        // Id of character
        Integer id = intent.getIntExtra(EXTRA_ID, -1);

        final String urlStd = intent.getStringExtra(EXTRA_URL_STD);
        final String urlHD = intent.getStringExtra(EXTRA_URL_HD);

        Retrofit retrofit = ClientInstance.getClient();
        ApiClient client = retrofit.create(ApiClient.class);
        Call<ApiResponse<Character>> call = client.getCharacter(id, API_KEY,
                FORMAT, field_list);

        call.enqueue(new Callback<ApiResponse<Character>>() {
            @Override
            public void onResponse(Call<ApiResponse<Character>> call,
                                   Response<ApiResponse<Character>> response) {
                character = response.body().getResults();

                // Init deck
                if (character.getDeck() != null) {
                    view.getCharacterDeckView().setText(null);
                    view.getCharacterDeckView().setText(character.getDeck());
                }

                // Init real name
                if (character.getRealName() != null) {
                    view.getRealNameView().setText(character.getRealName());
                }

                // Init aliases
                if (character.getAliases() != null) {
                    String[] aliases = character.getAliases().split("\\r?\\n");

                    view.getAliasesView().setText(null);
                    for (String alias : aliases
                    ) {
                        view.getAliasesView().append(alias + "\n");
                    }
                }

                // Init publisher
                if (character.getPublisher() != null) {
                    view.getPublisherView().setText(character.getPublisher().getName());
                }

                // Init creator
                if (character.getCreators() != null) {
                    creatorAdapter = new RelationAdapter(character.getCreators());
                    creatorRecylerView = view.getCreatorsRecyclerView();
                    creatorRecylerView.setAdapter(creatorAdapter);
                    creatorRecylerView.setLayoutManager(new LinearLayoutManager(activity));
                }

                // Init origin
                if (character.getOriginBrief() != null) {
                    view.getOriginView().setText(character.getOriginBrief().getName());
                }

                if (character.getBirth() != null) {
                    view.getBirthdayView().setText(character.getBirth());
                }
                // Init gender
                view.getGenderView().setText(character.getGender());

                // Show character information now that it's loaded
                view.getNestedScrollView().setVisibility(View.VISIBLE);

                loadMainImage(urlStd, urlHD);

            }

            @Override
            public void onFailure(Call<ApiResponse<Character>> call, Throwable t) {
                Toast.makeText((Activity) view, "Server failed to respond.",
                        Toast.LENGTH_LONG).show();

                Log.d("Ignis", t.toString());
                t.printStackTrace();
            }
        });
    }

    /**
     * Return drawable colors back to default
     */
    @Override
    public void resetDrawableColors() {
        shareDrawable.setColorFilter(null);
        infoDrawable.setColorFilter(null);
        backDrawable.setColorFilter(null);
    }

    @Override
    public void fabClicked() {
        if (character == null) {
            Toast.makeText(activity, "Try again in a second",
                    Toast.LENGTH_LONG).show();
        } else {
            shareUrlIntent(activity, character.getName(), character.getSiteDetailUrl());
        }
    }

    private void loadMainImage(final String urlStd, final String urlHD) {
        Picasso.get()
                .load(urlHD)
                .into(view.getCharacterImageView(), new com.squareup.picasso.Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) view.getCharacterImageView().getDrawable()).getBitmap();

                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            public void onGenerated(Palette p) {
                                setViewColors(p);

                                // Set drawables
                                view.getFab().setImageDrawable(shareDrawable);
                                view.getCharacterDeckInfoView().setImageDrawable(infoDrawable);
                            }
                        });
                        super.onSuccess();
                    }
                });
    }

    /**
     * Add colors to entire UI
     */
    private void setViewColors(Palette p) {

        int headerBg;
        // Used for character title and back as well
        int buttonBg;
        int cardBg;
        int parentBg;
        int textColor;
        int headerElementsColor;

        headerBg = p.getLightVibrantColor(R.attr.backgroundColor);
        buttonBg = p.getVibrantColor(Color.BLACK);
        cardBg = p.getLightVibrantColor(Color.WHITE);
        parentBg = p.getMutedColor(Color.BLACK);
        textColor = p.getDarkMutedColor(Color.BLACK);
        headerElementsColor = p.getVibrantColor(Color.BLACK);

        // Buttonbg (!) headbg

        /**
         * Potential clashes
         *
         * headerElementsColor and headerBg
         */

        if ((doColorsClash(headerElementsColor, headerBg))) {
            if (isColorDark(headerBg)) {
                headerElementsColor = p.getLightVibrantColor(Color.WHITE);
            } else {
                headerElementsColor = p.getMutedColor(Color.BLACK);
            }
        }

        // Collapsed header and status bar color
        activity.getWindow().setStatusBarColor(headerBg);
        activity.getWindow().setNavigationBarColor(headerBg);
        view.getCollapsingToolbarLayout().setContentScrimColor(headerBg);

        // Card backgrounds
        applyColorToLayoutBackgrounds(cardBg, new View[]{
                view.getCharacterGeneralInformationParentLayout(),
                view.getCharacterDeckParentLayout(),
                view.getComicInfoParentLayout()});

        // Parent backgrounds
        applyColorToLayoutBackgrounds(parentBg, new View[]{
                view.getCharacterParentLayout(),
                view.getCharacterInfoParentLayout()
        });

        // Drawables color filter
        applyColorFilterToDrawables(textColor, new Drawable[]{
                infoDrawable,
                shareDrawable
        });

        applyColorFilterToDrawables(headerElementsColor, new Drawable[]{
                backDrawable
        });

        // Character Title colors
        view.getCollapsingToolbarLayout().setCollapsedTitleTextColor(
                headerElementsColor);


        // Text colors
        applyTextColorToTextViews(textColor, new TextView[]{
                view.getDeckTitleView(),
                view.getGeneralInfoTitleView(),
                view.getComicInfoTitleView(),
                view.getCharacterDeckView(),
                view.getRealNameTitleView(),
                view.getRealNameView(),
                view.getAliasesTitleView(),
                view.getAliasesView(),
                view.getPublisherTitleView(),
                view.getCreatorsTitleView(),
                view.getGenderTitleView(),
                view.getGenderView(),
                view.getOriginTitleView(),
                view.getOriginView(),
                view.getBirthdayTitleView(),
                view.getBirthdayView()
        });

        // Button backgrounds
        applyBackgroundColorToButtons(buttonBg, new View[]{
                view.getFab(),
                view.getPublisherView(),
                view.getOriginView()});

        // Button text color
        applyTextColorToButtons(textColor, new Button[]{
                view.getPublisherView(),
                view.getOriginView()
        });

        // Apply colors to relations
        applyColorToRelations(buttonBg, textColor,
                creatorAdapter.buttons);
    }

    /**
     * Check if two given colors clash
     * Contrast must be less than 1.30 for them to clash
     */
    private boolean doColorsClash(int color1, int color2) {
        double contrast = ColorUtils.calculateContrast(color1, color2);
        return contrast - 1 <= 0.30;
    }

    /**
     * Check if given color is dark
     */
    private boolean isColorDark(int color) {
        return ColorUtils.calculateLuminance(color) < 0.5;
    }

    /**
     * Apply given color to the background of layouts in the array
     */
    private void applyColorToLayoutBackgrounds(int color, View[] layouts) {
        for (View layout : layouts
        ) {
            layout.setBackgroundColor(color);
        }
    }

    /**
     * Apply given color to drawables in array
     */
    private void applyColorFilterToDrawables(int color, Drawable[] drawables) {
        for (Drawable drawable : drawables
        ) {
            drawable.setColorFilter(
                    color, PorterDuff.Mode.SRC_ATOP);

        }
    }

    /**
     * Apply given color to the text color of TextViews in the array
     */
    private void applyTextColorToTextViews(int color, TextView[] textViews) {
        for (TextView textView : textViews
        ) {
            textView.setTextColor(color);
        }
    }

    /**
     * Apply given color to the background of views in the array
     */
    private void applyBackgroundColorToButtons(int color, Object[] views) {
        for (Object view : views
        ) {
            ((View) view).setBackgroundTintList(
                    ColorStateList.valueOf(color));
        }
    }

    /**
     * Apply given color to the text of buttons in the array
     */
    private void applyTextColorToButtons(int color, Button[] buttons) {
        for (Button button : buttons
        ) {
            button.setTextColor(
                    ColorStateList.valueOf(color));
        }
    }

    /**
     * Apply given colors to the text and background of each relation button
     */
    private void applyColorToRelations(int bgColor, int textColor, ArrayList<Button> buttons) {
        for (Button button : buttons
        ) {
            button.setBackgroundTintList(
                    ColorStateList.valueOf(bgColor));
            button.setTextColor(textColor);
        }
    }

    /**
     * Only show title when toolbar is collapsed.
     */
    private void showTitleOnCollapse() {
        // Only shows title when toolbar is collapsed
        view.getAppBarLayout().addOnOffsetChangedListener(
                new AppBarLayout.OnOffsetChangedListener() {

                    boolean isShow = true;
                    int scrollRange = -1;

                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (scrollRange == -1) {
                            scrollRange = appBarLayout.getTotalScrollRange();
                        }
                        if (scrollRange + verticalOffset == 0) {
                            view.getCollapsingToolbarLayout().setTitle(
                                    intent.getStringExtra(EXTRA_NAME));

                            isShow = true;
                        } else if (isShow) {
                            // There should a space between double quote otherwise it wont work
                            view.getCollapsingToolbarLayout().setTitle(" ");
                            isShow = false;
                        }
                    }
                });
    }
}
