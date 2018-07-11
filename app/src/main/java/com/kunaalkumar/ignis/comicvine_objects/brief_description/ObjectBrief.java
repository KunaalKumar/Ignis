package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;

public class ObjectBrief extends Brief {

    @SerializedName("aliases")
    private String aliases;

    @SerializedName("count_of_issue_appearances")
    private Integer countOfIssueAppearances;

    @SerializedName("first_appeared_in_issue")
    private FirstAppearedInIssue firstAppearedInIssue;

    @SerializedName("start_year")
    private String startYear;

    public String getAliases() {
        return aliases;
    }

    public Integer getCountOfIssueAppearances() {
        return countOfIssueAppearances;
    }

    public FirstAppearedInIssue getFirstAppearedInIssue() {
        return firstAppearedInIssue;
    }

    public String getStartYear() {
        return startYear;
    }

    public ObjectBrief(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, Integer countOfIssueAppearances, FirstAppearedInIssue firstAppearedInIssue, String startYear) {

        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl);
        this.aliases = aliases;
        this.countOfIssueAppearances = countOfIssueAppearances;
        this.firstAppearedInIssue = firstAppearedInIssue;
        this.startYear = startYear;
    }
}
