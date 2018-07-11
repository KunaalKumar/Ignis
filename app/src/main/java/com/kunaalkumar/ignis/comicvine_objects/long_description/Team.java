package com.kunaalkumar.ignis.comicvine_objects.long_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.TeamBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class Team extends TeamBrief {

    @SerializedName("aliases")
    private String aliases;

    @SerializedName("character_enemies")
    private Relation[] characterEnemies;

    @SerializedName("character_friends")
    private Relation[] characterFriends;

    @SerializedName("characters")
    private Relation[] characters;

    @SerializedName("disbanded_in_issues")
    private Relation[] disbandedInIssues;

    @SerializedName("isssues_disbanded_in")
    private Relation[] issuesDisbandedIn;

    @SerializedName("issue_credits")
    private Relation[] issueCredits;

    @SerializedName("movies")
    private Relation[] movies;

    @SerializedName("story_arc_credits")
    private Relation[] storyArcCredits;

    @SerializedName("volume_credits")
    private Relation[] volumeCredits;

    public Team(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, Integer countOfIssueAppearances, Integer countOfTeamMembers, FirstAppearedInIssue firstAppearedInIssue, Relation publisher, String aliases1, Relation[] characterEnemies, Relation[] characterFriends, Relation[] characters, Relation[] disbandedInIssues, Relation[] issuesDisbandedIn, Relation[] issueCredits, Relation[] movies, Relation[] storyArcCredits, Relation[] volumeCredits) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl, aliases, countOfIssueAppearances, countOfTeamMembers, firstAppearedInIssue, publisher);
        this.aliases = aliases1;
        this.characterEnemies = characterEnemies;
        this.characterFriends = characterFriends;
        this.characters = characters;
        this.disbandedInIssues = disbandedInIssues;
        this.issuesDisbandedIn = issuesDisbandedIn;
        this.issueCredits = issueCredits;
        this.movies = movies;
        this.storyArcCredits = storyArcCredits;
        this.volumeCredits = volumeCredits;
    }

    @Override
    public String getAliases() {
        return aliases;
    }

    public Relation[] getCharacterEnemies() {
        return characterEnemies;
    }

    public Relation[] getCharacterFriends() {
        return characterFriends;
    }

    public Relation[] getCharacters() {
        return characters;
    }

    public Relation[] getDisbandedInIssues() {
        return disbandedInIssues;
    }

    public Relation[] getIssuesDisbandedIn() {
        return issuesDisbandedIn;
    }

    public Relation[] getIssueCredits() {
        return issueCredits;
    }

    public Relation[] getMovies() {
        return movies;
    }

    public Relation[] getStoryArcCredits() {
        return storyArcCredits;
    }

    public Relation[] getVolumeCredits() {
        return volumeCredits;
    }
}
