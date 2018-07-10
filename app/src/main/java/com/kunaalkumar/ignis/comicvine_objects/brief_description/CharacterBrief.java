package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.Image;
import com.kunaalkumar.ignis.comicvine_objects.Publisher;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;

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

    public String getAliases() {
        return aliases;
    }

    public String getBirth() {
        return birth;
    }

    public Integer getCountOfIssueAppearances() {
        return countOfIssueAppearances;
    }

    public FirstAppearedInIssue getFirstAppearedInIssue() {
        return firstAppearedInIssue;
    }

    public Integer getGender() {
        return gender;
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

    public CharacterBrief(String deck, String dateLastUpdated, Image image, String name, String siteDetailUrl, Integer id, String aliases, String birth, Integer countOfIssueAppearances, FirstAppearedInIssue firstAppearedInIssue, Integer gender, Origin origin, Publisher publisher, String realName) {
        super(deck, dateLastUpdated, image, name, siteDetailUrl, id);
        this.aliases = aliases;
        this.birth = birth;
        this.countOfIssueAppearances = countOfIssueAppearances;
        this.firstAppearedInIssue = firstAppearedInIssue;
        this.gender = gender;
        this.origin = origin;
        this.publisher = publisher;
        this.realName = realName;
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