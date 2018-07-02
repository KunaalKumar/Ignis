package com.kunaalkumar.ignis.SuperheroData;

import java.util.List;

public class Connections {
    private List<String> group_affiliation;
    private List<String> relatives;

    public Connections(List<String> group_affiliation, List<String> relatives) {
        this.group_affiliation = group_affiliation;
        this.relatives = relatives;
    }

    public List<String> getGroup_affiliation() {
        return group_affiliation;
    }

    public void setGroup_affiliation(List<String> group_affiliation) {
        this.group_affiliation = group_affiliation;
    }

    public List<String> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<String> relatives) {
        this.relatives = relatives;
    }
}
