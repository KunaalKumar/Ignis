package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class VolumeBrief extends Brief {

    @SerializedName("aliases")
    private String aliases;

    @SerializedName("count_of_issues")
    private Integer countOfIssues;

    @SerializedName("first_issue")
    private FirstAppearedInIssue firstIssue;

    @SerializedName("last_issue")
    private FirstAppearedInIssue lastIssue;

    @SerializedName("publisher")
    private Relation publisher;

    @SerializedName("start_year")
    private String startYear;

    public VolumeBrief(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, Integer countOfIssues, FirstAppearedInIssue firstIssue, FirstAppearedInIssue lastIssue, Relation publisher, String startYear) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl);
        this.aliases = aliases;
        this.countOfIssues = countOfIssues;
        this.firstIssue = firstIssue;
        this.lastIssue = lastIssue;
        this.publisher = publisher;
        this.startYear = startYear;
    }

    public String getAliases() {
        return aliases;
    }

    public Integer getCountOfIssues() {
        return countOfIssues;
    }

    public FirstAppearedInIssue getFirstIssue() {
        return firstIssue;
    }

    public FirstAppearedInIssue getLastIssue() {
        return lastIssue;
    }

    public Relation getPublisher() {
        return publisher;
    }

    public String getStartYear() {
        return startYear;
    }
}
