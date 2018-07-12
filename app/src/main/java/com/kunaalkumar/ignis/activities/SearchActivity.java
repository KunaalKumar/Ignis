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

import android.annotation.SuppressLint;
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
import com.kunaalkumar.ignis.adapters.DefaultAdapter;
import com.kunaalkumar.ignis.comicvine_objects.SearchResult;
import com.kunaalkumar.ignis.comicvine_objects.long_description.ApiResponse;
import com.kunaalkumar.ignis.network.ApiClient;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

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

    @BindView(R.id.search_recycler_view)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DefaultAdapter adapter;

    InputMethodManager imm;

    private PeekAndPop peekAndPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d("Ignis", "SearchActivity started");
        ButterKnife.bind(this);

        init();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {

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
                    searchCall();
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

    @OnClick(R.id.search_clear)
    public void onClearClick(View view) {
        searchBox.setText(null);
    }

    @OnClick(R.id.search_back)
    public void onBackClick(View view) {
        hideKeyboard(view);
        onBackPressed();
    }

    // Retrofit call to search for word in superheroSearch
    private void searchCall() {

        Retrofit retrofit = ClientInstance.getClient();
        ApiClient client = retrofit.create(ApiClient.class);
        Call<ApiResponse<SearchResult[]>> call = client.search(searchBox.getText().toString(), API_KEY, FORMAT);
        call.enqueue(new Callback<ApiResponse<SearchResult[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<SearchResult[]>> call, Response<ApiResponse<SearchResult[]>> response) {
                SearchResult[] searchResults = response.body().getResults();

                adapter = new DefaultAdapter(searchResults, SearchActivity.this, peekAndPop);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
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
