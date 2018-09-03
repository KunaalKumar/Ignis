package com.kunaalkumar.ignis.network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created for Ignis for Android
 * Version : 1.0
 * Created by Kunaal Kumar
 */
public class ComicVineClient {

    static Elements getElements(String query, int pageNumber) {
        try {
            Document doc = Jsoup.connect("https://comicvine.gamespot.com/search/?i=&q=" +
                    query + "&page=" + pageNumber).get();
            Elements searchResults = doc.select("#js-sort-filter-results > li");
            // If no search results found, return null
            if (searchResults.size() != 1) {
                return searchResults;
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Element getCharacter(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.select("#default-content > aside > div:nth-child(5)").first();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
