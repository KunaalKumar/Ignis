package com.kunaalkumar.ignis.comicvine_objects;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("name")
    private String name;

    @SerializedName("resource_type")
    private String resourceType;

    @SerializedName("id")
    private Integer id;

    @SerializedName("api_detail_url")
    private String apiDetailUrl;

    @SerializedName("image")
    private Image image;

    public Result(String name, String resourceType, Integer id, String apiDetailUrl, Image image) {
        this.name = name;
        this.resourceType = resourceType;
        this.id = id;
        this.apiDetailUrl = apiDetailUrl;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Integer getId() {
        return id;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }
}
