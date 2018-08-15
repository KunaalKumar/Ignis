package com.kunaalkumar.ignis.fragments.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kunaalkumar.ignis.adapters.SearchCharacterAdapter;
import com.peekandpop.shalskar.peekandpop.PeekAndPop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Parent class to fragments in search view pager
 */
public class SearchFragment extends Fragment {

    TextView textView;

    ProgressBar progressBar;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SearchCharacterAdapter adapter;

    private Integer pageNumber;

    public static PeekAndPop peekAndPop;

    View view;

    public SearchFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
