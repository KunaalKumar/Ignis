package com.kunaalkumar.ignis;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.kunaalkumar.ignis.adapters.DefaultAdapter;
import com.kunaalkumar.ignis.comicvine_objects.ApiResponse;
import com.kunaalkumar.ignis.comicvine_objects.Result;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.kunaalkumar.ignis.network.ApiClient;

public class MainActivity extends AppCompatActivity {

    public static final String FORMAT = "json";

    @BindView(R.id.superhero_search)
    EditText superheroSearch;

    @BindView(R.id.search_button)
    Button searchButton;

    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.settings)
    ImageView settings;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DefaultAdapter adapter;


    // Api key for ComicVine
    private static final String apiKey = "9aa1dc67801a2cdc8460790837f94b73057ce351";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Fresco.initialize(this);
    }

    @OnClick(R.id.search)
    public void searchOnClick(View view) {
        Toast.makeText(this, "Clicked search", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.settings)
    public void settingsOnClick(View view) {
        Toast.makeText(this, "Clicked settings", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.search_button)
    public void searchButton(View view) {
        Toast.makeText(MainActivity.this, "Getting results", Toast.LENGTH_SHORT).show();

        Retrofit retrofit = ClientInstance.getClient();
        ApiClient client = retrofit.create(ApiClient.class);

        Call<ApiResponse> call = client.search(apiKey, superheroSearch.getText().toString(), FORMAT);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Result[] results = response.body().getResults();

                adapter = new DefaultAdapter(results, getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Ignis", t.toString());
            }
        });

    }

}
