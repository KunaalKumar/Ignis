package com.kunaalkumar.ignis.comicvine_objects;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.CharacterBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;
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

    public Character(String deck, String dateLastUpdated, Image image, String name, String siteDetailUrl, Integer id, String aliases, String birth, Integer countOfIssueAppearances, FirstAppearedInIssue firstAppearedInIssue, Integer gender, Origin origin, Publisher publisher, String realName, Relation[] characterEnemies, Relation[] characterFriends, Relation[] creators, Relation[] issuesAppearedIn, Relation[] issuesDiedIn, Relation[] movies, Relation[] powers, Relation[] storyArcCredits, Relation[] teamEnemies, Relation[] teamFriends, Relation[] teams, Relation[] volumeCredits) {
        super(deck, dateLastUpdated, image, name, siteDetailUrl, id, aliases, birth, countOfIssueAppearances, firstAppearedInIssue, gender, origin, publisher, realName);
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
