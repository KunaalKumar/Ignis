package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class StoryArcBrief extends Brief {

    @SerializedName("aliases")
    private String aliases;

    @SerializedName("count_of_isssue_appearances")
    private Integer countOfIssueAppearances;

    @SerializedName("first_appeared_in_issue")
    private FirstAppearedInIssue firstAppearedInIssue;

    @SerializedName("publisher")
    private Relation publisher;

    public String getAliases() {
        return aliases;
    }

    public Integer getCountOfIssueAppearances() {
        return countOfIssueAppearances;
    }

    public FirstAppearedInIssue getFirstAppearedInIssue() {
        return firstAppearedInIssue;
    }

    public Relation getPublisher() {
        return publisher;
    }

    public StoryArcBrief(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, Integer countOfIssueAppearances, FirstAppearedInIssue firstAppearedInIssue, Relation publisher) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl);
        this.aliases = aliases;
        this.countOfIssueAppearances = countOfIssueAppearances;
        this.firstAppearedInIssue = firstAppearedInIssue;
        this.publisher = publisher;
    }
}
