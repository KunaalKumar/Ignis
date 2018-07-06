package com.kunaalkumar.ignis.comicvine_objects;

import com.google.gson.annotations.SerializedName;

public class Character extends CharacterResults {

    @SerializedName("character_enemies")
    private Relation[] characterEnemies;

    @SerializedName("character_friends")
    private Relation[] characterFriends;

    @SerializedName("count_of_issue_appearances")
    private Integer countOfIssueAppearances;

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

    public Character(String aliases, String birth, Integer countOfIssueAppearances, String dateLastUpdated, String briefSummary, String description, FirstAppearedInIssue firstAppearedInIssue, int gender, Integer id, Image image, Origin origin, Publisher publisher, String realName, String name, Relation[] characterEnemies, Relation[] characterFriends, Integer countOfIssueAppearances1, Relation[] creators, Relation[] issuesAppearedIn, Relation[] issuesDiedIn, Relation[] movies, Relation[] powers, Relation[] storyArcCredits, Relation[] teamEnemies, Relation[] teamFriends, Relation[] teams, Relation[] volumeCredits) {
        super(aliases, birth, countOfIssueAppearances, dateLastUpdated, briefSummary, description, firstAppearedInIssue, gender, id, image, origin, publisher, realName, name);
        this.characterEnemies = characterEnemies;
        this.characterFriends = characterFriends;
        this.countOfIssueAppearances = countOfIssueAppearances1;
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

    public Relation[] getCharacterEnemies() {
        return characterEnemies;
    }

    public void setCharacterEnemies(Relation[] characterEnemies) {
        this.characterEnemies = characterEnemies;
    }

    public Relation[] getCharacterFriends() {
        return characterFriends;
    }

    public void setCharacterFriends(Relation[] characterFriends) {
        this.characterFriends = characterFriends;
    }

    @Override
    public Integer getCountOfIssueAppearances() {
        return countOfIssueAppearances;
    }

    @Override
    public void setCountOfIssueAppearances(Integer countOfIssueAppearances) {
        this.countOfIssueAppearances = countOfIssueAppearances;
    }

    public Relation[] getCreators() {
        return creators;
    }

    public void setCreators(Relation[] creators) {
        this.creators = creators;
    }

    public Relation[] getIssuesAppearedIn() {
        return issuesAppearedIn;
    }

    public void setIssuesAppearedIn(Relation[] issuesAppearedIn) {
        this.issuesAppearedIn = issuesAppearedIn;
    }

    public Relation[] getIssuesDiedIn() {
        return issuesDiedIn;
    }

    public void setIssuesDiedIn(Relation[] issuesDiedIn) {
        this.issuesDiedIn = issuesDiedIn;
    }

    public Relation[] getMovies() {
        return movies;
    }

    public void setMovies(Relation[] movies) {
        this.movies = movies;
    }

    public Relation[] getPowers() {
        return powers;
    }

    public void setPowers(Relation[] powers) {
        this.powers = powers;
    }

    public Relation[] getStoryArcCredits() {
        return storyArcCredits;
    }

    public void setStoryArcCredits(Relation[] storyArcCredits) {
        this.storyArcCredits = storyArcCredits;
    }

    public Relation[] getTeamEnemies() {
        return teamEnemies;
    }

    public void setTeamEnemies(Relation[] teamEnemies) {
        this.teamEnemies = teamEnemies;
    }

    public Relation[] getTeamFriends() {
        return teamFriends;
    }

    public void setTeamFriends(Relation[] teamFriends) {
        this.teamFriends = teamFriends;
    }

    public Relation[] getTeams() {
        return teams;
    }

    public void setTeams(Relation[] teams) {
        this.teams = teams;
    }

    public Relation[] getVolumeCredits() {
        return volumeCredits;
    }

    public void setVolumeCredits(Relation[] volumeCredits) {
        this.volumeCredits = volumeCredits;
    }

    public Character(String aliases, String birth, Integer countOfIssueAppearances, String dateLastUpdated, String briefSummary, String description, CharacterResults.FirstAppearedInIssue firstAppearedInIssue, int gender, Integer id, CharacterResults.Image image, CharacterResults.Origin origin, CharacterResults.Publisher publisher, String realName, String name) {
        super(aliases, birth, countOfIssueAppearances, dateLastUpdated, briefSummary, description, firstAppearedInIssue, gender, id, image, origin, publisher, realName, name);
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

        public void setApiDetailUrl(String apiDetailUrl) {
            this.apiDetailUrl = apiDetailUrl;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
