package com.kunaalkumar.ignis.comicvine_objects.misc;

import com.google.gson.annotations.SerializedName;

public class Relation {

    @SerializedName("api_detail_url")
    private String apiDetailUrl;

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    public Relation(String apiDetailUrl, Integer id, String name) {
        this.apiDetailUrl = apiDetailUrl;
        this.id = id;
        this.name = name;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}