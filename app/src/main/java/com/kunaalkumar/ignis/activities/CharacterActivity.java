package com.kunaalkumar.ignis.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.comicvine_objects.long_description.ApiResponse;
import com.kunaalkumar.ignis.comicvine_objects.long_description.Character;
import com.kunaalkumar.ignis.network.ApiClient;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.kunaalkumar.ignis.utils.SharedPrefs;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.kunaalkumar.ignis.adapters.DefaultAdapter.EXTRA_ID;
import static com.kunaalkumar.ignis.adapters.DefaultAdapter.EXTRA_NAME;
import static com.kunaalkumar.ignis.adapters.DefaultAdapter.EXTRA_URL_HD;
import static com.kunaalkumar.ignis.adapters.DefaultAdapter.EXTRA_URL_STD;

public class CharacterActivity extends AppCompatActivity {

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

    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ButterKnife.bind(this);

        final Intent intent = getIntent();
        searchCall(intent.getIntExtra(EXTRA_ID, -1));

        final String urlStd = intent.getStringExtra(EXTRA_URL_STD);
        final String urlHD = intent.getStringExtra(EXTRA_URL_HD);
        loadMainImage(urlStd, urlHD);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        showTitleOnCollapse(intent);

    }

    private void loadMainImage(final String urlStd, final String urlHD) {
        Picasso.get()
                .load(urlHD)
                .into(characterImage, new com.squareup.picasso.Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) characterImage.getDrawable()).getBitmap();

                        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                            public void onGenerated(Palette p) {
                                colorify(p);
                            }
                        });
                        super.onSuccess();
                    }
                });
    }

    private void showTitleOnCollapse(final Intent intent) {
        // Only shows title when toolbar is collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(intent.getStringExtra(EXTRA_NAME));
                    isShow = true;
                } else if (isShow) {
                    // There should a space between double quote otherwise it wont work
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    // TODO: find a better name `-.-`
    private void colorify(Palette p) {
        collapsingToolbarLayout.setExpandedTitleColor(p.getVibrantColor(getResources()
                .getColor(R.color.colorAccent)));
        collapsingToolbarLayout.setCollapsedTitleTextColor(p.getVibrantColor(getResources()
                .getColor(R.color.colorAccent)));

        Drawable mDrawable = getResources().getDrawable(R.drawable.ic_share_24dp);
        mDrawable.setTint(p.getVibrantColor(getResources().
                getColor(R.color.colorAccent)));
        fab.setImageDrawable(mDrawable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Return drawable colors back to default
        Drawable mDrawable = getResources().getDrawable(R.drawable.ic_share_24dp);
        mDrawable.setColorFilter(null);
    }

    // Retrofit call to search character for given id
    private void searchCall(Integer id) {

        Retrofit retrofit = ClientInstance.getClient();
        ApiClient client = retrofit.create(ApiClient.class);
        Call<ApiResponse<Character>> call = client.getCharacter(id, MainActivity.API_KEY,
                MainActivity.FORMAT);

        call.enqueue(new Callback<ApiResponse<Character>>() {
            @Override
            public void onResponse(Call<ApiResponse<Character>> call,
                                   Response<ApiResponse<Character>> response) {
//      Do something with this
                character = response.body().getResults();
            }

            @Override
            public void onFailure(Call<ApiResponse<Character>> call, Throwable t) {
                Toast.makeText(CharacterActivity.this, "ERROR: Character not found",
                        Toast.LENGTH_LONG).show();

                Log.d("Ignis", t.toString());
                t.printStackTrace();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    @OnClick(R.id.character_fab)
    public void onClickFab(View view) {
        Toast.makeText(this, "To Do: Implement sharing",
                Toast.LENGTH_LONG).show();
    }
}
