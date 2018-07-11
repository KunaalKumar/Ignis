package com.kunaalkumar.ignis.comicvine_objects.brief_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

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

    @SerializedName("originBrief")
    private OriginBrief originBrief;

    @SerializedName("publisher")
    private Relation publisher;

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

    public OriginBrief getOriginBrief() {
        return originBrief;
    }

    public Relation getPublisher() {
        return publisher;
    }

    public String getRealName() {
        return realName;
    }

    public CharacterBrief(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, String birth, Integer countOfIssueAppearances, FirstAppearedInIssue firstAppearedInIssue, Integer gender, OriginBrief originBrief, Relation publisher, String realName) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl);
        this.aliases = aliases;
        this.birth = birth;
        this.countOfIssueAppearances = countOfIssueAppearances;
        this.firstAppearedInIssue = firstAppearedInIssue;
        this.gender = gender;
        this.originBrief = originBrief;
        this.publisher = publisher;
        this.realName = realName;
    }
}