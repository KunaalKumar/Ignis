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
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kunaalkumar.ignis.comicvine_objects.ApiResponse;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.kunaalkumar.ignis.network.ApiClient;

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

        Retrofit retrofit = ClientInstance.getClient();
        ApiClient client = retrofit.create(ApiClient.class);

        // TODO: Check for if name is null
        Call<ApiResponse> call = client.searchCharacters(superheroSearch.getText().toString());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d("Ignis", response.body().toString());
                ApiResponse apiResopnse = response.body();
                superheroInformation.setText(Html.fromHtml(Html.fromHtml(apiResopnse.getResults()[0].getDescription()).toString()));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Ignis", t.toString());
            }
        });
    }

}
