package com.kunaalkumar.ignis.fragments.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kunaalkumar.ignis.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchIssueFragment extends SearchFragment {

    public static final String TITLE = "Issue";

    View view;

    public SearchIssueFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_issue, container, false);
        return view;
    }
}
