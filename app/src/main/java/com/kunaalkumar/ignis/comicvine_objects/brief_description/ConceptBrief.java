package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;

public class ConceptBrief extends Brief {

    @SerializedName("aliases")
    private String aliases;

    @SerializedName("count_of_issue_appearances")
    private Integer count_of_issue_appearances;

    @SerializedName("first_appeared_in_issue")
    private FirstAppearedInIssue firstAppearedInIssue;

    public ConceptBrief(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, Integer count_of_issue_appearances, FirstAppearedInIssue firstAppearedInIssue) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl);
        this.aliases = aliases;
        this.count_of_issue_appearances = count_of_issue_appearances;
        this.firstAppearedInIssue = firstAppearedInIssue;
    }

    public String getAliases() {
        return aliases;
    }

    public Integer getCount_of_issue_appearances() {
        return count_of_issue_appearances;
    }

    public FirstAppearedInIssue getFirstAppearedInIssue() {
        return firstAppearedInIssue;
    }
}
