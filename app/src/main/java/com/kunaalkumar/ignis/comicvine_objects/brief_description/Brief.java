package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;

public class Brief {

    @SerializedName("api_detail_url")
    private String apiDetailUrl;

    @SerializedName("deck")
    private String deck;

    @SerializedName("date_last_updated")
    private String dateLastUpdated;

    @SerializedName("image")
    private Image image;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private Integer id;

    @SerializedName("site_detail_url")
    private String siteDetailUrl;

    public Brief(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl) {
        this.apiDetailUrl = apiDetailUrl;
        this.deck = deck;
        this.dateLastUpdated = dateLastUpdated;
        this.image = image;
        this.name = name;
        this.id = id;
        this.siteDetailUrl = siteDetailUrl;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getDeck() {
        return deck;
    }


    public String getDateLastUpdated() {
        return dateLastUpdated;
    }


    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }
}
