package com.kunaalkumar.ignis;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kunaalkumar.ignis.comicvine_objects.ApiResponse;
import com.kunaalkumar.ignis.comicvine_objects.CharacterResults;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.kunaalkumar.ignis.network.ApiClient;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.superhero_search)
    EditText superheroSearch;

    @BindView(R.id.search_button)
    Button searchButton;

    @BindView(R.id.superhero_information)
    ImageView superheroInformation;

    // Api key for ComicVine
    private static final String apiKey = "9aa1dc67801a2cdc8460790837f94b73057ce351";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.search_button)
    public void search(View view) {
        Toast.makeText(MainActivity.this, "Getting results", Toast.LENGTH_SHORT).show();

        Retrofit retrofit = ClientInstance.getClient();
        ApiClient client = retrofit.create(ApiClient.class);

        // TODO: Check for if name is null
        Call<ApiResponse<CharacterResults>> call = client.searchCharacters(apiKey, "name:" + superheroSearch.getText().toString(), "json");

        call.enqueue(new Callback<ApiResponse<CharacterResults>>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse<CharacterResults>> call, @NonNull Response<ApiResponse<CharacterResults>> response) {
                Log.d("Ignis", response.body().toString());
                ApiResponse<CharacterResults> apiResponse = response.body();

                if (apiResponse.getResults().length != 0) {
                    String image_url = apiResponse.getResults()[0].getImage().getOriginalUrl();
                    Picasso.get().load(image_url).into(superheroInformation);
                }
//                for (CharacterResults characterResults : apiResponse.getResults()
//                        ) {
//                    if (!(characterResults.getRealName() == null)) {
//                        superheroInformation.append(characterResults.getName() + "\n");
//                    }
//                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse<CharacterResults>> call, @NonNull Throwable t) {
                Log.d("Ignis", t.toString());
            }
        });
    }

}
