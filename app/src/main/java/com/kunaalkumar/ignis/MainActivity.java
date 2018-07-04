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

import com.google.gson.stream.JsonReader;
import com.kunaalkumar.ignis.comicvine_objects.Character;
import com.kunaalkumar.ignis.network.ApiClient;
import com.kunaalkumar.ignis.network.ApiInterface;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.superhero_search)
    EditText superheroSearch;

    @BindView(R.id.search_button)
    Button searchButton;

    @BindView(R.id.superhero_information)
    TextView superheroInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.search_button)
    public void search(View view) {
        Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG).show();

        Retrofit retrofit = ApiClient.getClient();
        ApiInterface client = retrofit.create(ApiInterface.class);

        Call<Character> call = client.getCharacter();
        call.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                Log.d("Ignis", response.body().toString());
                Character character = response.body();
                superheroInformation.setText(character.getGender());
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                Log.d("Ignis", t.toString());
            }
        });
    }

}
