package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class SearchResult extends Brief {

    @SerializedName("resource_type")
    private String resourceType;

    @SerializedName("count_of_issues")
    private Integer countOfIssues;

    @SerializedName("publisher")
    private Relation publisher;

    @SerializedName("start_year")
    private String startYear;

    @SerializedName("origin")
    private Relation origin;

    @SerializedName("real_name")
    private String realName;

    @SerializedName("volume")
    private Relation volume;

    @SerializedName("country")
    private String country;

    public SearchResult(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String resourceType, Integer countOfIssues, Relation publisher, String startYear, Relation origin, String realName, Relation volume, String country) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl);
        this.resourceType = resourceType;
        this.countOfIssues = countOfIssues;
        this.publisher = publisher;
        this.startYear = startYear;
        this.origin = origin;
        this.realName = realName;
        this.volume = volume;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Integer getCountOfIssues() {
        return countOfIssues;
    }

    public Relation getPublisher() {
        return publisher;
    }

    public String getStartYear() {
        return startYear;
    }

    public Relation getOrigin() {
        return origin;
    }

    public String getRealName() {
        return realName;
    }

    public Relation getVolume() {
        return volume;
    }
}
