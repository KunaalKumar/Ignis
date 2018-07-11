package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class IssueBrief extends Brief {

    @SerializedName("aliases")
    private String aliases;

    @SerializedName("cover_date")
    private String coverDate;

    @SerializedName("issue_number")
    private String issueNumber;

    @SerializedName("store_date")
    private String storeDate;

    @SerializedName("volume")
    private Relation volume;

    public IssueBrief(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, String coverDate, String issueNumber, String storeDate, Relation volume) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl);
        this.aliases = aliases;
        this.coverDate = coverDate;
        this.issueNumber = issueNumber;
        this.storeDate = storeDate;
        this.volume = volume;
    }

    public String getAliases() {
        return aliases;
    }

    public String getCoverDate() {
        return coverDate;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public String getStoreDate() {
        return storeDate;
    }

    public Relation getVolume() {
        return volume;
    }
}
