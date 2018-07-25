package com.kunaalkumar.ignis.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.kunaalkumar.ignis.adapters.DefaultAdapter.EXTRA_ID;
import static com.kunaalkumar.ignis.adapters.DefaultAdapter.EXTRA_NAME;
import static com.kunaalkumar.ignis.adapters.DefaultAdapter.EXTRA_URL;

public class CharacterActivity extends AppCompatActivity {

    @BindView(R.id.character_image)
    ImageView characterImage;

    @BindView(R.id.character_toolbar)
    Toolbar toolbar;

    @BindView(R.id.character_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    private Palette palette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_URL);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        collapsingToolbar.setTitleEnabled(true);
        collapsingToolbar.setTitle(intent.getStringExtra(EXTRA_NAME));
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);

        searchCall(intent.getIntExtra(EXTRA_ID, -1));

        Picasso.get()
                .load(url)
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

    // TODO: find a better name `-.-`
    private void colorify(Palette p) {
        palette = p;
        collapsingToolbar.setExpandedTitleColor(p.getVibrantColor(getResources()
                .getColor(R.color.colorAccent)));
        collapsingToolbar.setCollapsedTitleTextColor(p.getVibrantColor(getResources()
                .getColor(R.color.colorAccent)));
    }

    // Retrofit call to search character for given id
    private void searchCall(Integer id) {

        Retrofit retrofit = ClientInstance.getClient();
        ApiClient client = retrofit.create(ApiClient.class);
        Call<ApiResponse<Character>> call = client.getCharacter(id, MainActivity.API_KEY, MainActivity.FORMAT);

        call.enqueue(new Callback<ApiResponse<Character>>() {
            @Override
            public void onResponse(Call<ApiResponse<Character>> call, Response<ApiResponse<Character>> response) {
//      Do something with this
//      Character character = response.body().getResults()
            }

            @Override
            public void onFailure(Call<ApiResponse<Character>> call, Throwable t) {
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
}
