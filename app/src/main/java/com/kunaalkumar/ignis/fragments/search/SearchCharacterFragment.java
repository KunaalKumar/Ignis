package com.kunaalkumar.ignis.fragments.search;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.activities.MainActivity;
import com.kunaalkumar.ignis.activities.SearchActivity;
import com.kunaalkumar.ignis.adapters.SearchCharacterAdapter;
import com.kunaalkumar.ignis.adapters.SearchHistoryAdapter;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.CharacterBrief;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.SearchResult;
import com.kunaalkumar.ignis.comicvine_objects.long_description.ApiResponse;
import com.kunaalkumar.ignis.network.ApiClient;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.kunaalkumar.ignis.utils.SharedPrefs;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.kunaalkumar.ignis.activities.MainActivity.API_KEY;
import static com.kunaalkumar.ignis.activities.MainActivity.FORMAT;

public class SearchCharacterFragment extends Fragment {

    public static final String TITLE = "Character";

    @BindView(R.id.search_character_empty_text)
    TextView textView;

    @BindView(R.id.search_character_loading)
    ProgressBar progressBar;


    public RecyclerView historyRecyclerView;
    public SearchHistoryAdapter historyAdapter;

    private RecyclerView recyclerView;
    private SearchCharacterAdapter adapter;

    private Integer pageNumber;

    public static PeekAndPop peekAndPop;

    View view;

    public SearchCharacterFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_character, container, false);

        ButterKnife.bind(this, view);

//        historyRecyclerView = view.findViewById(R.id.search_character_history);

        recyclerView = view.findViewById(R.id.search_character_recycler_view);

        showLoadingState(false);

        initPeekAndPop();

//        historyAdapter = new SearchHistoryAdapter(SharedPrefs.getSearchHistory(), getActivity());
//        historyRecyclerView.setAdapter(historyAdapter);
//        historyRecyclerView.setLayoutManager(new ∂LinearLayoutManager(getContext()));

        return view;
    }

//    private static void showSuggestions(boolean bool) {
//        if (bool) {
//            historyRecyclerView.setVisibility(View.VISIBLE);
//            recyclerView.setVisibility(View.GONE);
//        } else {
//            historyRecyclerView.setVisibility(View.GONE);
//            recyclerView.setVisibility(View.VISIBLE);
//        }
//    }


    private void initPeekAndPop() {
        // Basic init for peekAndPop
        peekAndPop = new PeekAndPop.Builder(getActivity())
                .peekLayout(R.layout.peek_preview)
                .parentViewGroupToDisallowTouchEvents(recyclerView)
                .build();
    }


    private void showLoadingState(boolean state) {
        if (state) {
            textView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    /*




    .___              .__
    |   | ____   ____ |__| ______
    |   |/ ___\ /    \|  |/  ___/
    |   / /_/  >   |  \  |\___ \
    |___\___  /|___|  /__/____  >
       /_____/      \/        \/


                Call to Comic Vine API




     */

    /*
     * Retrofit call to search for word in superheroSearch
     * Make sure to init query variable before calling this
     */
    public void searchCall(final Activity activity, final String query, final boolean isNewCall) {
        if (isNewCall) {
            pageNumber = 1;
        } else {
            pageNumber++;
        }

        showLoadingState(true);

        recyclerView.requestFocus();
        SharedPrefs.addToSearchHistory(query);
        Retrofit retrofit = ClientInstance.getClient();
        ApiClient client = retrofit.create(ApiClient.class);
        String filter = "name:" + query.toString();
        String field_list = "name,real_name,publisher,image,id";
        Call<ApiResponse<CharacterBrief[]>> call = client.searchCharacters(filter,
                API_KEY,
                FORMAT,
                pageNumber,
                field_list,
                10);

        call.enqueue(new Callback<ApiResponse<CharacterBrief[]>>() {
            @Override
            public void onResponse(Call<ApiResponse<CharacterBrief[]>> call, Response<ApiResponse<CharacterBrief[]>> response) {

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                if (response.body().getError().equals("OK") && response.body().getNumberOfPageResults() != 0) {
                    if (pageNumber == 1) {
                        adapter = new SearchCharacterAdapter((SearchActivity) getActivity(), peekAndPop, query);
                        recyclerView.setAdapter(adapter);

                        // Recyclerview Optimizations
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setItemViewCacheSize(20);
                        recyclerView.setDrawingCacheEnabled(true);
                        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

                        adapter.searchResults.addAll(Arrays.asList(response.body().getResults()));
                        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

                    } else {
                        adapter.searchResults.addAll(Arrays.asList(response.body().getResults()));
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    if (adapter == null) {
                        textView.setVisibility(View.VISIBLE);
                        textView.setText("No search results");
                    } else if (isNewCall) {
                        recyclerView.setVisibility(View.GONE);
                        textView.setVisibility(View.VISIBLE);
                        textView.setText("No search results");
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<CharacterBrief[]>> call, Throwable t) {
                Log.d("Ignis", t.toString());
            }
        });
    }

    private void attachOnScrollListener(final String query) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(getActivity(), "Last item", Toast.LENGTH_LONG).show();
                    searchCall(getActivity(), query, false);
                }
            }
        });
    }
}
