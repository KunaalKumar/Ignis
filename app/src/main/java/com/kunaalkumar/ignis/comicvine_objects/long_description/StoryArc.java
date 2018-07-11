package com.kunaalkumar.ignis.comicvine_objects.long_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.StoryArcBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class StoryArc extends StoryArcBrief {

    @SerializedName("aliases")
    private String aliases;

    // Note: There is a typo in the api call itself
    @SerializedName("count_of_isssue_appearances")
    private Integer countOfIssueAppearances;

    @SerializedName("episodes")
    private Relation[] episodes;

    @SerializedName("first_appeared_in_episode")
    private Relation firstAppearedInEpisode;

    @SerializedName("first_appeared_in_issue")
    private FirstAppearedInIssue firstAppearedInIssue;

    @SerializedName("issues")
    private Relation[] issues;

    @SerializedName("publisher")
    private Relation publisher;

    public StoryArc(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, Integer countOfIssueAppearances, FirstAppearedInIssue firstAppearedInIssue, Relation publisher, String aliases1, Integer countOfIssueAppearances1, Relation[] episodes, Relation firstAppearedInEpisode, FirstAppearedInIssue firstAppearedInIssue1, Relation[] issues, Relation publisher1) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl, aliases, countOfIssueAppearances, firstAppearedInIssue, publisher);
        this.aliases = aliases1;
        this.countOfIssueAppearances = countOfIssueAppearances1;
        this.episodes = episodes;
        this.firstAppearedInEpisode = firstAppearedInEpisode;
        this.firstAppearedInIssue = firstAppearedInIssue1;
        this.issues = issues;
        this.publisher = publisher1;
    }

    @Override
    public String getAliases() {
        return aliases;
    }

    @Override
    public Integer getCountOfIssueAppearances() {
        return countOfIssueAppearances;
    }

    public Relation[] getEpisodes() {
        return episodes;
    }

    public Relation getFirstAppearedInEpisode() {
        return firstAppearedInEpisode;
    }

    @Override
    public FirstAppearedInIssue getFirstAppearedInIssue() {
        return firstAppearedInIssue;
    }

    public Relation[] getIssues() {
        return issues;
    }

    @Override
    public Relation getPublisher() {
        return publisher;
    }
}
