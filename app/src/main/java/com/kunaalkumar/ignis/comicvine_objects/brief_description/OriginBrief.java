package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;

public class OriginBrief {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("api_detail_url")
    private String apiDetailUrl;

    @SerializedName("site_detail_url")
    private String siteDetailUrl;

    public OriginBrief(Integer id, String name, String apiDetailUrl, String siteDetailUrl) {
        this.id = id;
        this.name = name;
        this.apiDetailUrl = apiDetailUrl;
        this.siteDetailUrl = siteDetailUrl;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}