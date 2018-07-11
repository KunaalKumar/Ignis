package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;

public class PublisherBrief extends Brief {

    @SerializedName("aliases")
    private String aliases;

    @SerializedName("location_address")
    private String locationAddress;

    @SerializedName("location_city")
    private String locationCity;

    @SerializedName("location_state")
    private String locationState;

    public PublisherBrief(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, String locationAddress, String locationCity, String locationState) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl);
        this.aliases = aliases;
        this.locationAddress = locationAddress;
        this.locationCity = locationCity;
        this.locationState = locationState;
    }

    public String getAliases() {
        return aliases;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public String getLocationState() {
        return locationState;
    }
}