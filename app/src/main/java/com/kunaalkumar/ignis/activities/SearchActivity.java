package com.kunaalkumar.ignis.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.utils.SharedPrefs;
import com.kunaalkumar.ignis.adapters.DefaultAdapter;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.SearchResult;
import com.kunaalkumar.ignis.comicvine_objects.long_description.ApiResponse;
import com.kunaalkumar.ignis.network.ApiClient;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.Arrays;

import static com.kunaalkumar.ignis.activities.MainActivity.API_KEY;
import static com.kunaalkumar.ignis.activities.MainActivity.FORMAT;

public class SearchActivity extends AppCompatActivity {


    @BindView(R.id.search_toolbar)
    Toolbar toolbar;

    @BindView(R.id.search_searchBox)
    EditText searchBox;

    @BindView(R.id.search_back)
    ImageView backButton;

    @BindView(R.id.search_clear)
    ImageView clearButton;

    public static RecyclerView recyclerView;
    public static DefaultAdapter adapter;

    public static Integer pageNumber;
    public static String query;

    InputMethodManager imm;

    public static PeekAndPop peekAndPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (SettingsActivity.sharedPrefs == null) {
            SettingsActivity.sharedPrefs = new SharedPrefs(this);
        }

        SharedPrefs.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d("Ignis", "SearchActivity started");
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        recyclerView = findViewById(R.id.search_recycler_view);
        KeyboardVisibilityEvent.setEventListener(
                SearchActivity.this,
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        // closed
                        if (!isOpen) {
                            searchBox.clearFocus();
                        }
                    }
                });

        // Basic init for peekAndPop
        peekAndPop = new PeekAndPop.Builder(this)
                .peekLayout(R.layout.peek_preview)
                .parentViewGroupToDisallowTouchEvents(recyclerView)
                .build();


        // Request focus on searchBox and pull up keyboard
        searchBox.requestFocus();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        // Listener for on "enter" pressed
        searchBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    query = searchBox.getText().toString();
                    pageNumber = 1;
                    searchCall(SearchActivity.this);
                    searchBox.clearFocus();
                    hideKeyboard(view);
                    Toast.makeText(SearchActivity.this, "Entered " + searchBox.getText(), Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(searchBox.getText())) {
                    clearButton.setVisibility(View.VISIBLE);
                } else {
                    clearButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick(R.id.search_back)
    public void onSearchBackPressed(View view) {
        hideKeyboard(view);
        onBackPressed();
    }

    @OnClick(R.id.search_clear)
    public void onClearClick(View view) {
        searchBox.setText(null);
    }

    // Increments pageNumber and calls searchCall
    public static void nextPage(Activity activity) {
        pageNumber++;
        searchCall(activity);
    }


    /*
     * Retrofit call to search for word in superheroSearch
     * Make sure to init query variable before calling this
     */
    public static void searchCall(final Activity activity) {

        Retrofit retrofit = ClientInstance.getClient();
        ApiClient client = retrofit.create(ApiClient.class);
        Call<ApiResponse<SearchResult[]>> call = client.search(query, API_KEY, FORMAT, pageNumber);
        call.enqueue(new Callback<ApiResponse<SearchResult[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<SearchResult[]>> call, Response<ApiResponse<SearchResult[]>> response) {

                if (response.body().getError().equals("OK") && response.body().getNumberOfPageResults() != 0) {
                    if (pageNumber == 1) {
                        adapter = new DefaultAdapter(activity, peekAndPop);
                        recyclerView.setAdapter(adapter);
                        DefaultAdapter.searchResults.addAll(Arrays.asList(response.body().getResults()));
                        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    } else {
                        DefaultAdapter.searchResults.addAll(Arrays.asList(response.body().getResults()));
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(activity, "No more", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ApiResponse<SearchResult[]>> call, Throwable t) {
                Log.d("Ignis", t.toString());
            }
        });
    }

    private void hideKeyboard(View view) {
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
