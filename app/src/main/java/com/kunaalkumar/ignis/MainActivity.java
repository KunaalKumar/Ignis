package com.kunaalkumar.ignis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.kunaalkumar.ignis.adapters.DefaultAdapter;
import com.kunaalkumar.ignis.comicvine_objects.ApiResponse;
import com.kunaalkumar.ignis.comicvine_objects.Result;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.kunaalkumar.ignis.network.ApiClient;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;

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

    private PeekAndPop peekAndPop;

    // Api key for ComicVine
    public static final String API_KEY = "9aa1dc67801a2cdc8460790837f94b73057ce351";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        peekAndPop = new PeekAndPop.Builder(this)
                .peekLayout(R.layout.peek_preview)
                .parentViewGroupToDisallowTouchEvents(recyclerView)
                .build();
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

        searchCall();
    }

    // Retrofit call to search for word in superheroSearch
    private void searchCall() {

        Retrofit retrofit = ClientInstance.getClient();
        ApiClient client = retrofit.create(ApiClient.class);
        Call<ApiResponse<Result[]>> call = client.search(superheroSearch.getText().toString(), API_KEY, FORMAT);
        call.enqueue(new Callback<ApiResponse<Result[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<Result[]>> call, Response<ApiResponse<Result[]>> response) {
                Result[] results = response.body().getResults();

                adapter = new DefaultAdapter(results, MainActivity.this, peekAndPop);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onFailure(Call<ApiResponse<Result[]>> call, Throwable t) {
                Log.d("Ignis", t.toString());
            }
        });
    }
}
