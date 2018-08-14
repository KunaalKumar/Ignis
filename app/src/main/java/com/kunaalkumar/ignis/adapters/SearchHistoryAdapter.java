package com.kunaalkumar.ignis.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.kunaalkumar.ignis.R;
import com.kunaalkumar.ignis.activities.search.SearchPresenter;
import com.kunaalkumar.ignis.fragments.search.SearchCharacterFragment;
import com.kunaalkumar.ignis.utils.SharedPrefs;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static net.yslibrary.android.keyboardvisibilityevent.util.UIUtil.hideKeyboard;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {

    public ArrayList<String> searchHistory;
    public SearchPresenter presenter;

    public SearchHistoryAdapter(ArrayList<String> searchHistory, SearchPresenter presenter) {
        this.searchHistory = searchHistory;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public SearchHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search_history, parent, false);
        return new SearchHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchHistoryViewHolder holder, final int position) {
        holder.searchText.setText(searchHistory.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.searchCall(searchHistory.get(position));
            }
        });

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                final ArrayList<String> temp = new ArrayList<>();
                for (int i = searchHistory.size() - 1; i >= 0; i--) {
                    temp.add(searchHistory.get(i));
                }

                Snackbar.make(view, "Removed " + searchHistory.get(position), Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SharedPrefs.replaceSearchHistory(temp);
                                searchHistory = SharedPrefs.getSearchHistory();
                                notifyDataSetChanged();
                            }
                        }).show();

                SharedPrefs.removeFromSearchHistory(searchHistory.get(position));
                searchHistory.remove(searchHistory.get(position));
                notifyDataSetChanged();

                return true;
            }
        });
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
