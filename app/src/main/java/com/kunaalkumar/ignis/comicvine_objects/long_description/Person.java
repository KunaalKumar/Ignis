package com.kunaalkumar.ignis.comicvine_objects.long_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.PeopleBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class Person extends PeopleBrief {

    @SerializedName("created_characters")
    private Relation[] createdCharacters;

    @SerializedName("issues")
    private Relation[] issues;

    @SerializedName("story_arc_credits")
    private Relation[] storyArcCredits;

    @SerializedName("volume_credits")
    private Relation[] volumeCredits;

    @SerializedName("website")
    private String website;

    public Person(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, String birth, Integer countOfIssueAppearances, String country, String death, String email, Integer gender, String hometown, Relation[] createdCharacters, Relation[] issues, Relation[] storyArcCredits, Relation[] volumeCredits, String website) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl, aliases, birth, countOfIssueAppearances, country, death, email, gender, hometown);
        this.createdCharacters = createdCharacters;
        this.issues = issues;
        this.storyArcCredits = storyArcCredits;
        this.volumeCredits = volumeCredits;
        this.website = website;
    }

    public Relation[] getCreatedCharacters() {
        return createdCharacters;
    }

    public Relation[] getIssues() {
        return issues;
    }

    public Relation[] getStoryArcCredits() {
        return storyArcCredits;
    }

    public Relation[] getVolumeCredits() {
        return volumeCredits;
    }

    public String getWebsite() {
        return website;
    }
}
