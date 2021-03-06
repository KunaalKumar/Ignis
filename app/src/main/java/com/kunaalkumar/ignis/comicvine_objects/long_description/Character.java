package com.kunaalkumar.ignis.comicvine_objects.long_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.CharacterBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.OriginBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class Character extends CharacterBrief {

    @SerializedName("character_enemies")
    private Relation[] characterEnemies;

    @SerializedName("character_friends")
    private Relation[] characterFriends;

    @SerializedName("creators")
    private Relation[] creators;

    @SerializedName("issue_credits")
    private Relation[] issuesAppearedIn;

    @SerializedName("issues_died_in")
    private Relation[] issuesDiedIn;

    @SerializedName("movies")
    private Relation[] movies;

    @SerializedName("powers")
    private Relation[] powers;

    @SerializedName("story_arc_credits")
    private Relation[] storyArcCredits;

    @SerializedName("team_enemies")
    private Relation[] teamEnemies;

    @SerializedName("team_friends")
    private Relation[] teamFriends;

    @SerializedName("teams")
    private Relation[] teams;

    @SerializedName("volume_credits")
    private Relation[] volumeCredits;

    public Relation[] getCharacterEnemies() {
        return characterEnemies;
    }

    public Relation[] getCharacterFriends() {
        return characterFriends;
    }

    public Relation[] getCreators() {
        return creators;
    }

    public Relation[] getIssuesAppearedIn() {
        return issuesAppearedIn;
    }

    public Relation[] getIssuesDiedIn() {
        return issuesDiedIn;
    }

    public Relation[] getMovies() {
        return movies;
    }

    public Relation[] getPowers() {
        return powers;
    }

    public Relation[] getStoryArcCredits() {
        return storyArcCredits;
    }

    public Relation[] getTeamEnemies() {
        return teamEnemies;
    }

    public Relation[] getTeamFriends() {
        return teamFriends;
    }

    public Relation[] getTeams() {
        return teams;
    }

    public Relation[] getVolumeCredits() {
        return volumeCredits;
    }

    public Character(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, String birth, Integer countOfIssueAppearances, FirstAppearedInIssue firstAppearedInIssue, Integer gender, OriginBrief originBrief, Relation publisher, String realName, Relation[] characterEnemies, Relation[] characterFriends, Relation[] creators, Relation[] issuesAppearedIn, Relation[] issuesDiedIn, Relation[] movies, Relation[] powers, Relation[] storyArcCredits, Relation[] teamEnemies, Relation[] teamFriends, Relation[] teams, Relation[] volumeCredits) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl, aliases, birth, countOfIssueAppearances, firstAppearedInIssue, gender, originBrief, publisher, realName);
        this.characterEnemies = characterEnemies;
        this.characterFriends = characterFriends;
        this.creators = creators;
        this.issuesAppearedIn = issuesAppearedIn;
        this.issuesDiedIn = issuesDiedIn;
        this.movies = movies;
        this.powers = powers;
        this.storyArcCredits = storyArcCredits;
        this.teamEnemies = teamEnemies;
        this.teamFriends = teamFriends;
        this.teams = teams;
        this.volumeCredits = volumeCredits;
    }
}
