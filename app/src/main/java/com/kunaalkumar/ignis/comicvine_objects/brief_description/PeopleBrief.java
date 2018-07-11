package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;

public class PeopleBrief extends Brief {

    @SerializedName("aliases")
    private String aliases;

    @SerializedName("birth")
    private String birth;

    @SerializedName("count_of_isssue_appearances")
    private Integer countOfIssueAppearances;

    @SerializedName("country")
    private String country;

    @SerializedName("death")
    private String death;

    @SerializedName("email")
    private String email;

    @SerializedName("gender")
    private Integer gender;

    @SerializedName("hometown")
    private String hometown;

    public PeopleBrief(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, String birth, Integer countOfIssueAppearances, String country, String death, String email, Integer gender, String hometown) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl);
        this.aliases = aliases;
        this.birth = birth;
        this.countOfIssueAppearances = countOfIssueAppearances;
        this.country = country;
        this.death = death;
        this.email = email;
        this.gender = gender;
        this.hometown = hometown;
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

    public String getCountry() {
        return country;
    }

    public String getDeath() {
        return death;
    }

    public String getEmail() {
        return email;
    }

    public Integer getGender() {
        return gender;
    }

    public String getHometown() {
        return hometown;
    }
}
