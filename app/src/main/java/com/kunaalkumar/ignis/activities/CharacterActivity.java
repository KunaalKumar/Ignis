package com.kunaalkumar.ignis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.comicvine_objects.long_description.ApiResponse;
import com.kunaalkumar.ignis.comicvine_objects.long_description.Character;
import com.kunaalkumar.ignis.network.ApiClient;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.kunaalkumar.ignis.adapters.DefaultAdapter.EXTRA_ID;
import static com.kunaalkumar.ignis.adapters.DefaultAdapter.EXTRA_NAME;
import static com.kunaalkumar.ignis.adapters.DefaultAdapter.EXTRA_URL;
import static com.kunaalkumar.ignis.adapters.DefaultAdapter.loadImageFromURL;

public class CharacterActivity extends AppCompatActivity {

    @BindView(R.id.character_image)
    ImageView characterImage;

    @BindView(R.id.character_back)
    ImageView characterBack;

    @BindView(R.id.character_name)
    TextView characterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_URL);

        characterName.setText(intent.getStringExtra(EXTRA_NAME));

        searchCall(intent.getIntExtra(EXTRA_ID, -1));

//        Glide.with(this)
//                .load(url)
//                .apply(new RequestOptions()
//                .dontAnimate())
//                .into(characterImage);

        loadImageFromURL(url, characterImage);
    }

    @OnClick(R.id.character_back)
    public void onBackClick(View view) {
        onBackPressed();
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
}
