package com.kunaalkumar.ignis.comicvine_objects.long_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.ConceptBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class Concept extends ConceptBrief {

    @SerializedName("issue_credits")
    private Relation issueCredits;

    @SerializedName("movies")
    private Relation[] movies;

    @SerializedName("volume_credits")
    private Relation[] volume_credits;

    public Concept(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, Integer count_of_issue_appearances, FirstAppearedInIssue firstAppearedInIssue, Relation issueCredits, Relation[] movies, Relation[] volume_credits) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl, aliases, count_of_issue_appearances, firstAppearedInIssue);
        this.issueCredits = issueCredits;
        this.movies = movies;
        this.volume_credits = volume_credits;
    }

    public Relation getIssueCredits() {
        return issueCredits;
    }

    public void setIssueCredits(Relation issueCredits) {
        this.issueCredits = issueCredits;
    }

    public Relation[] getMovies() {
        return movies;
    }

    public void setMovies(Relation[] movies) {
        this.movies = movies;
    }

    public Relation[] getVolume_credits() {
        return volume_credits;
    }

    public void setVolume_credits(Relation[] volume_credits) {
        this.volume_credits = volume_credits;
    }
}