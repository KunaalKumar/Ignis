package com.kunaalkumar.ignis.comicvine_objects;

import com.google.gson.annotations.SerializedName;

public class Character {

    @SerializedName("aliases")
    private String aliases;

    @SerializedName("birth")
    private String birth;

    @SerializedName("count_of_issue_appearances")
    private Integer countOfIssueAppearances;

    @SerializedName("date_last_updated")
    private String dateLastUpdated;

    @SerializedName("deck")
    private String briefSummary;

    @SerializedName("description")
    private String description;

    @SerializedName("first_appeared_in_issue")
    private FirstAppearedInIssue firstAppearedInIssue;

    @SerializedName("gender")
    private Integer gender;

    @SerializedName("id")
    private Integer id;

    @SerializedName("image")
    private Image image;

    @SerializedName("origin")
    private Origin origin;

    @SerializedName("publisher")
    private Publisher publisher;

    @SerializedName("real_name")
    private String realName;

    @SerializedName("name")
    private String name;
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

    @SerializedName("api_detail_url")
    private String apiDetailUrl;

    public Character(String aliases, String birth, Integer countOfIssueAppearances, String dateLastUpdated, String briefSummary, String description, FirstAppearedInIssue firstAppearedInIssue, Integer gender, Integer id, Image image, Origin origin, Publisher publisher, String realName, String name, Relation[] characterEnemies, Relation[] characterFriends, Relation[] creators, Relation[] issuesAppearedIn, Relation[] issuesDiedIn, Relation[] movies, Relation[] powers, Relation[] storyArcCredits, Relation[] teamEnemies, Relation[] teamFriends, Relation[] teams, Relation[] volumeCredits, String apiDetailUrl) {
        this.aliases = aliases;
        this.birth = birth;
        this.countOfIssueAppearances = countOfIssueAppearances;
        this.dateLastUpdated = dateLastUpdated;
        this.briefSummary = briefSummary;
        this.description = description;
        this.firstAppearedInIssue = firstAppearedInIssue;
        this.gender = gender;
        this.id = id;
        this.image = image;
        this.origin = origin;
        this.publisher = publisher;
        this.realName = realName;
        this.name = name;
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
        this.apiDetailUrl = apiDetailUrl;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public void setApiDetailUrl(String apiDetailUrl) {
        this.apiDetailUrl = apiDetailUrl;
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

    public String getAliases() {
        return aliases;
    }

    public String getBirth() {
        return birth;
    }

    public Integer getCountOfIssueAppearances() {
        return countOfIssueAppearances;
    }

    public String getDateLastUpdated() {
        return dateLastUpdated;
    }

    public String getBriefSummary() {
        return briefSummary;
    }

    public String getDescription() {
        return description;
    }

    public FirstAppearedInIssue getFirstAppearedInIssue() {
        return firstAppearedInIssue;
    }

    public int getGender() {
        return gender;
    }

    public Integer getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }

    public Origin getOrigin() {
        return origin;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public String getRealName() {
        return realName;
    }

    public String getName() {
        return name;
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

    public class FirstAppearedInIssue {
        @SerializedName("id")
        private Integer id;

        @SerializedName("name")
        private String name;

        @SerializedName("issue_number")
        private Double issueNumber;

        public FirstAppearedInIssue(Integer id, String name, Double issueNumber) {
            this.id = id;
            this.name = name;
            this.issueNumber = issueNumber;
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

        public Double getIssueNumber() {
            return issueNumber;
        }

        public void setIssueNumber(Double issueNumber) {
            this.issueNumber = issueNumber;
        }
    }

    public class Origin {
        @SerializedName("id")
        private Integer id;

        @SerializedName("name")
        private String name;

        public Origin(Integer id, String name) {
            this.id = id;
            this.name = name;
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
