package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.Image;
import com.kunaalkumar.ignis.comicvine_objects.Publisher;

public class CharacterBrief extends Brief {

    @SerializedName("aliases")
    private String aliases;

    @SerializedName("birth")
    private String birth;

    @SerializedName("count_of_issue_appearances")
    private Integer countOfIssueAppearances;

    @SerializedName("first_appeared_in_issue")
    private FirstAppearedInIssue firstAppearedInIssue;

    @SerializedName("gender")
    private Integer gender;

    @SerializedName("origin")
    private Origin origin;

    @SerializedName("publisher")
    private Publisher publisher;

    @SerializedName("real_name")
    private String realName;

    public CharacterBrief( String deck, String dateLastUpdated, Image image, String name, String siteDetailUrl, Integer id) {
        super(deck, dateLastUpdated, image, name, siteDetailUrl, id);
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

        public String getName() {
            return name;
        }

        public Double getIssueNumber() {
            return issueNumber;
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

        public String getName() {
            return name;
        }

    }

}