package com.kunaalkumar.ignis.network;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.kunaalkumar.ignis.activities.search.SearchPresenter;
import com.kunaalkumar.ignis.web_scraper.SearchResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Responsible for fetching search results
 */
public class FetchSearchResults extends AsyncTask<Void, Void, Void> {

    String query;
    SearchPresenter searchPresenter;
    ProgressBar progressBar;
    int pageNumber;

    private ArrayList<SearchResult> resultObjs = new ArrayList<>();

    public FetchSearchResults(SearchPresenter searchPresenter, String query,
                              ProgressBar progressBar,
                              int pageNumber) {
        this.searchPresenter = searchPresenter;
        this.query = query;
        this.progressBar = progressBar;
        this.pageNumber = pageNumber;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        synchronized (this) {
            try {
                Document doc = Jsoup.connect("https://comicvine.gamespot.com/search/?i=&q=" +
                        query + "&page=" + pageNumber).get();
                Elements searchResults = doc.select("#js-sort-filter-results > li");
                // Add search results to arraylist
                for (Element result : searchResults) {
                    resultObjs.add(new SearchResult(result));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressBar.setVisibility(View.GONE);
        searchPresenter.populateSearchResults(resultObjs);
    }
}
