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
