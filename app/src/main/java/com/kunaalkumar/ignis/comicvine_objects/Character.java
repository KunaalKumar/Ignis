package com.kunaalkumar.ignis.comicvine_objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Character {

    private final static String FIELD_LIST = "aliases, birth, character_enemies";

    @SerializedName("aliases")
    private String aliases;

    @SerializedName("birth")
    private String birth;

    @SerializedName("character_enemies")
    private List<String> characterEnemies;

    @SerializedName("character_friends")
    private List<String> characterFriends;

    @SerializedName("count_of_issue_appearances")
    private List<String> countOfIssueAppearances;

    @SerializedName("date_last_updated")
    private String dateLastUpdated;

    @SerializedName("deck")
    private String briefSummary;

    @SerializedName("description")
    private String description;

    @SerializedName("first_appeared_in_issue")
    private FirstAppearedInIssue firstAppearedInIssue;

    @SerializedName("gender")
    private String gender;

    @SerializedName("id")
    private Integer id;

    @SerializedName("image")
    private Image image;

    @SerializedName("origin")
    private Origin origin;

    @SerializedName("publisher")
    private Publisher publisher;

    @SerializedName("real_name")
    private List<String> realName;

    public Character(String aliases, String birth, List<String> characterEnemies, List<String> characterFriends, List<String> countOfIssueAppearances, String dateLastUpdated, String briefSummary, String description, FirstAppearedInIssue firstAppearedInIssue, String gender, Integer id, Image image, Origin origin, Publisher publisher, List<String> realName) {
        this.aliases = aliases;
        this.birth = birth;
        this.characterEnemies = characterEnemies;
        this.characterFriends = characterFriends;
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
    }

    public static String getFieldList() {
        return FIELD_LIST;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public List<String> getCharacterEnemies() {
        return characterEnemies;
    }

    public void setCharacterEnemies(List<String> characterEnemies) {
        this.characterEnemies = characterEnemies;
    }

    public List<String> getCharacterFriends() {
        return characterFriends;
    }

    public void setCharacterFriends(List<String> characterFriends) {
        this.characterFriends = characterFriends;
    }

    public List<String> getCountOfIssueAppearances() {
        return countOfIssueAppearances;
    }

    public void setCountOfIssueAppearances(List<String> countOfIssueAppearances) {
        this.countOfIssueAppearances = countOfIssueAppearances;
    }

    public String getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(String dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public String getBriefSummary() {
        return briefSummary;
    }

    public void setBriefSummary(String briefSummary) {
        this.briefSummary = briefSummary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FirstAppearedInIssue getFirstAppearedInIssue() {
        return firstAppearedInIssue;
    }

    public void setFirstAppearedInIssue(FirstAppearedInIssue firstAppearedInIssue) {
        this.firstAppearedInIssue = firstAppearedInIssue;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<String> getRealName() {
        return realName;
    }

    public void setRealName(List<String> realName) {
        this.realName = realName;
    }

    // characters/first_appeared_in_issue
    private class FirstAppearedInIssue {
        @SerializedName("id")
        private Integer id;

        @SerializedName("name")
        private String name;

        @SerializedName("issue_number")
        private Integer issueNumber;

        public FirstAppearedInIssue(Integer id, String name, Integer issueNumber) {
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

        public Integer getIssueNumber() {
            return issueNumber;
        }

        public void setIssueNumber(Integer issueNumber) {
            this.issueNumber = issueNumber;
        }
    }

    // characters/image
    private class Image {

        @SerializedName("icon_url")
        private String iconUrl;

        @SerializedName("medium_url")
        private String mediumUrl;

        @SerializedName("screen_url")
        private String screenUrl;

        @SerializedName("screen_large_url")
        private String screenLargeUrl;

        @SerializedName("small_url")
        private String smallUrl;

        @SerializedName("super_url")
        private String superUrl;

        @SerializedName("thumb_url")
        private String thumbUrl;

        @SerializedName("tiny_url")
        private String tinyUrl;

        @SerializedName("original_url")
        private String originalUrl;

        public Image(String iconUrl, String mediumUrl, String screenUrl, String screenLargeUrl, String smallUrl, String superUrl, String thumbUrl, String tinyUrl, String originalUrl) {
            this.iconUrl = iconUrl;
            this.mediumUrl = mediumUrl;
            this.screenUrl = screenUrl;
            this.screenLargeUrl = screenLargeUrl;
            this.smallUrl = smallUrl;
            this.superUrl = superUrl;
            this.thumbUrl = thumbUrl;
            this.tinyUrl = tinyUrl;
            this.originalUrl = originalUrl;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getMediumUrl() {
            return mediumUrl;
        }

        public void setMediumUrl(String mediumUrl) {
            this.mediumUrl = mediumUrl;
        }

        public String getScreenUrl() {
            return screenUrl;
        }

        public void setScreenUrl(String screenUrl) {
            this.screenUrl = screenUrl;
        }

        public String getScreenLargeUrl() {
            return screenLargeUrl;
        }

        public void setScreenLargeUrl(String screenLargeUrl) {
            this.screenLargeUrl = screenLargeUrl;
        }

        public String getSmallUrl() {
            return smallUrl;
        }

        public void setSmallUrl(String smallUrl) {
            this.smallUrl = smallUrl;
        }

        public String getSuperUrl() {
            return superUrl;
        }

        public void setSuperUrl(String superUrl) {
            this.superUrl = superUrl;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }

        public String getTinyUrl() {
            return tinyUrl;
        }

        public void setTinyUrl(String tinyUrl) {
            this.tinyUrl = tinyUrl;
        }

        public String getOriginalUrl() {
            return originalUrl;
        }

        public void setOriginalUrl(String originalUrl) {
            this.originalUrl = originalUrl;
        }
    }

    private class Origin {
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

    private class Publisher {
        @SerializedName("id")
        private Integer id;

        @SerializedName("name")
        private String name;

        public Publisher(Integer id, String name) {
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
