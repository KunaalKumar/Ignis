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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kunaalkumar.ignis.comicvine_objects.ApiResponse;
import com.kunaalkumar.ignis.comicvine_objects.Character;
import com.kunaalkumar.ignis.comicvine_objects.CharacterResults;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.kunaalkumar.ignis.network.ApiClient;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.superhero_search)
    EditText superheroSearch;

    @BindView(R.id.search_button)
    Button searchButton;

    @BindView(R.id.superhero_information)
    TextView superheroInformation;

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
            public void onResponse(Call<ApiResponse<CharacterResults>> call, Response<ApiResponse<CharacterResults>> response) {
                Log.d("Ignis", response.body().toString());
                ApiResponse<CharacterResults> apiResponse = response.body();


                if (apiResponse.getResults().length == 0) {
                    superheroInformation.setText("No results found.");
                } else {
                    superheroInformation.setText("");
                }

                for (CharacterResults characterResults : apiResponse.getResults()
                        ) {
                    if (!(characterResults.getRealName() == null)) {
                        superheroInformation.append(characterResults.getName() + "\n");
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<CharacterResults>> call, Throwable t) {
                Log.d("Ignis", t.toString());
            }
        });
    }

}
