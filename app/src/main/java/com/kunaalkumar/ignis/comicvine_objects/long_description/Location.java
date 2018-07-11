package com.kunaalkumar.ignis.comicvine_objects.long_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.LocationBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class Location extends LocationBrief {

    @SerializedName("issue_credits")
    private Relation[] issueCredits;

    @SerializedName("movies")
    private Relation[] movies;

    @SerializedName("start_year")
    private String startYear;

    @SerializedName("story_arc_credits")
    private Relation[] storyArcCredits;

    @SerializedName("volume_credits")
    private Relation[] volumeCredits;

    public Location(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, Integer countOfIssueAppearances, FirstAppearedInIssue firstAppearedInIssue, String startYear, Relation[] issueCredits, Relation[] movies, String startYear1, Relation[] storyArcCredits, Relation[] volumeCredits) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl, aliases, countOfIssueAppearances, firstAppearedInIssue, startYear);
        this.issueCredits = issueCredits;
        this.movies = movies;
        this.startYear = startYear1;
        this.storyArcCredits = storyArcCredits;
        this.volumeCredits = volumeCredits;
    }

    public Relation[] getIssueCredits() {
        return issueCredits;
    }

    public Relation[] getMovies() {
        return movies;
    }

    @Override
    public String getStartYear() {
        return startYear;
    }

    public Relation[] getStoryArcCredits() {
        return storyArcCredits;
    }

    public Relation[] getVolumeCredits() {
        return volumeCredits;
    }
}
