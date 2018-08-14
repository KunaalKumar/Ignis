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
import com.kunaalkumar.ignis.activities.search.SearchActivity;
import com.kunaalkumar.ignis.adapters.SearchCharacterAdapter;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.CharacterBrief;
import com.kunaalkumar.ignis.comicvine_objects.long_description.ApiResponse;
import com.kunaalkumar.ignis.network.ApiClient;
import com.kunaalkumar.ignis.network.ClientInstance;
import com.kunaalkumar.ignis.utils.SharedPrefs;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

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

import static com.kunaalkumar.ignis.activities.main.MainPresenter.API_KEY;
import static com.kunaalkumar.ignis.activities.main.MainPresenter.FORMAT;


public class SearchCharacterFragment extends Fragment {

    public static final String TITLE = "Character";

    @BindView(R.id.search_character_empty_text)
    TextView textView;

    @BindView(R.id.search_character_loading)
    ProgressBar progressBar;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
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

        recyclerView = view.findViewById(R.id.search_character_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());

        showLoadingState(false);

        initPeekAndPop();

        return view;
    }


    private void initPeekAndPop() {
        // Basic init for peekAndPop
        peekAndPop = new PeekAndPop.Builder(getActivity())
                .peekLayout(R.layout.peek_preview)
                .parentViewGroupToDisallowTouchEvents(recyclerView)
                .build();
    }


    private void showLoadingState(boolean state) {
        if (state) {
            if (textView != null && progressBar != null && recyclerView != null) {
                textView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        } else {
            if (textView != null && progressBar != null) {
                textView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
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
    public void searchCall(final String query, final boolean isNewCall) {

        if (isNewCall) {
            pageNumber = 0;
            showLoadingState(true);
        } else {
            pageNumber += 10;
        }

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

                if (progressBar != null && recyclerView != null) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }

                if (response.body().getError().equals("OK") && response.body().getNumberOfPageResults() != 0) {
                    if (isNewCall) {
                        adapter = new SearchCharacterAdapter((SearchActivity) getActivity(), peekAndPop, query);
                        recyclerView.setAdapter(adapter);

                        // Recyclerview Optimizations
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setItemViewCacheSize(20);
                        recyclerView.setDrawingCacheEnabled(true);
                        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                        adapter.searchResults.addAll(Arrays.asList(response.body().getResults()));
                        recyclerView.setLayoutManager(layoutManager);

                    } else {
                        adapter.searchResults.addAll(Arrays.asList(response.body().getResults()));
                        adapter.notifyDataSetChanged();
                    }

                    System.out.print("");
                    // Attach scroll listener if more to load
                    attachOnScrollListener(query,
                            response.body().getNumberOfPageResults() + pageNumber,
                            response.body().getNumberOfTotalResults());

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

    private void attachOnScrollListener(final String query, final int numResults, final int numTotal) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    //End of list
                    if (numResults ==
                            numTotal) {
                        Toast.makeText(getActivity(), "No more to load", Toast.LENGTH_LONG).show();
                        recyclerView.removeOnScrollListener(this);
                    } else {
                        Toast.makeText(getActivity(), "Loading more", Toast.LENGTH_LONG).show();
                        searchCall(query, false);
                        recyclerView.removeOnScrollListener(this);
                    }
                }
            }
        });

    }
}
