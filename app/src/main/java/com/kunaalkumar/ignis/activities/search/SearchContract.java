package com.kunaalkumar.ignis.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public interface SearchContract {

    interface MvpView {

        EditText getSearchBox();

        ImageView getClearButton();

        RecyclerView getSearchHistoryView();

        RecyclerView getSearchRecyclerView();

    }

    interface Presenter {

        void saveState(FragmentManager fragmentManager, Bundle bundle);

        void restoreState(FragmentManager fragmentManager, Bundle bundle);

        void initSearchBox();

        void handleIntent(Intent intent);

        void showHistory(boolean show);
    }
}
