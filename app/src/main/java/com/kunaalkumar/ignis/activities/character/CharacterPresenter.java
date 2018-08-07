package com.kunaalkumar.ignis.activities.character;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.comicvine_objects.long_description.ApiResponse;
import com.kunaalkumar.ignis.comicvine_objects.long_description.Character;
import com.kunaalkumar.ignis.network.ApiClient;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.squareup.picasso.Picasso;

import androidx.palette.graphics.Palette;
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
    private String field_list = "name,site_detail_url,image,deck";

    private Character character;


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
    }

    // Retrofit call to search character for given id
    public void searchCall(Intent intent) {

        // Id of character
        Integer id = intent.getIntExtra(EXTRA_ID, -1);

        final String urlStd = intent.getStringExtra(EXTRA_URL_STD);
        final String urlHD = intent.getStringExtra(EXTRA_URL_HD);
        loadMainImage(urlStd, urlHD);

        Retrofit retrofit = ClientInstance.getClient();
        ApiClient client = retrofit.create(ApiClient.class);
        Call<ApiResponse<Character>> call = client.getCharacter(id, API_KEY,
                FORMAT, field_list);

        call.enqueue(new Callback<ApiResponse<Character>>() {
            @Override
            public void onResponse(Call<ApiResponse<Character>> call,
                                   Response<ApiResponse<Character>> response) {
                character = response.body().getResults();
                view.getCharacterDeck().setText(character.getDeck());
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
                .into(view.getCharacterImage(), new com.squareup.picasso.Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) view.getCharacterImage().getDrawable()).getBitmap();

                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            public void onGenerated(Palette p) {
                                colorify(p);
                            }
                        });

                        super.onSuccess();
                    }
                });
    }

    /**
     * Add colors to entire UI
     * TODO: find a better name `-.-`
     */
    private void colorify(Palette p) {

        // Background color
        view.getCharacterParent().setBackgroundColor(
                p.getDarkMutedColor(Color.WHITE));

        view.getCharacterInfoParent().setBackgroundColor(
                p.getDarkMutedColor(Color.WHITE));

        // Title colors
        view.getCollapsingToolbarLayout().setCollapsedTitleTextColor(
                p.getVibrantColor(activity.getResources()
                        .getColor(R.color.colorAccent)));

        backDrawable.setColorFilter(
                p.getVibrantColor(activity.getResources()
                        .getColor(R.color.colorAccent)), PorterDuff.Mode.SRC_ATOP);

        // Fab colors
        shareDrawable.setColorFilter(
                p.getVibrantColor(activity.getResources()
                        .getColor(R.color.colorAccent)), PorterDuff.Mode.SRC_ATOP);

        view.getFab().setImageDrawable(shareDrawable);
        view.getFab().setBackgroundTintList(ColorStateList.valueOf(p.getDarkMutedColor(Color.WHITE)));

        // Deck colors
        view.getCharacterDeckParentLayout().setBackgroundColor(
                p.getMutedColor(Color.WHITE));

        view.getCharacterDeck().setTextColor(
                p.getLightMutedColor(activity.getResources()
                        .getColor(R.color.colorAccent)));
        view.getCharacterDeck().setBackgroundColor(
                p.getMutedColor(Color.WHITE));

        infoDrawable.setColorFilter(
                p.getVibrantColor(activity.getResources()
                        .getColor(R.color.colorAccent)), PorterDuff.Mode.SRC_ATOP);

        view.getCharacterDeckInfo().setImageDrawable(infoDrawable);
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
