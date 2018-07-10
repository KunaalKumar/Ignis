package com.kunaalkumar.ignis.comicvine_objects.misc;

import com.google.gson.annotations.SerializedName;

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