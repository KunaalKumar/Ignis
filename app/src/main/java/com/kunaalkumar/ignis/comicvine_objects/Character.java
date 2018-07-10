package com.kunaalkumar.ignis.comicvine_objects;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.CharacterBrief;

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

    public Character(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, String siteDetailUrl, Integer id) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, siteDetailUrl, id);
    }


    public class Relation {

        @SerializedName("api_detail_url")
        private String apiDetailUrl;

        @SerializedName("id")
        private Integer id;

        @SerializedName("name")
        private String name;

        public Relation(String apiDetailUrl, Integer id, String name) {
            this.apiDetailUrl = apiDetailUrl;
            this.id = id;
            this.name = name;
        }

        public String getApiDetailUrl() {
            return apiDetailUrl;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }


}
