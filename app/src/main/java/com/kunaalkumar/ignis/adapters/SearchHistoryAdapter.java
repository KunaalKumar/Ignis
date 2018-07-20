package com.kunaalkumar.ignis.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kunaalkumar.ignis.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {

    public ArrayList<String> searchHistory;

    public SearchHistoryAdapter(ArrayList<String> searchHistory) {
        this.searchHistory = searchHistory;
    }

    @NonNull
    @Override
    public SearchHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search, parent, false);
        return new SearchHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryViewHolder holder, int position) {
        holder.searchText.setText(searchHistory.get(position));
    }

    @Override
    public int getItemCount() {
        return searchHistory.size();
    }

    /*




   .___              .__
   |   | ____   ____ |__| ______
   |   |/ ___\ /    \|  |/  ___/
   |   / /_/  >   |  \  |\___ \
   |___\___  /|___|  /__/____  >
      /_____/      \/        \/


               Search history view holder




    */

    public class SearchHistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_search_text)
        TextView searchText;

        @BindView(R.id.search_parent_layout)
        RelativeLayout parentLayout;

        public SearchHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
