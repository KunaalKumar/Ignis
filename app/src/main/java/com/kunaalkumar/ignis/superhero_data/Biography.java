package com.kunaalkumar.ignis.superhero_data;

import java.util.List;

public class Biography {
    private String full_name;
    private List<String> alter_egos;
    private String[] aliases;
    private String place_of_birth;
    private String first_appearance;
    private String publisher;
    private String alignment;

    public Biography(String full_name, List<String> alter_egos, String[] aliases, String place_of_birth, String first_appearance, String publisher, String alignment) {
        this.full_name = full_name;
        this.alter_egos = alter_egos;
        this.aliases = aliases;
        this.place_of_birth = place_of_birth;
        this.first_appearance = first_appearance;
        this.publisher = publisher;
        this.alignment = alignment;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public List<String> getAlter_egos() {
        return alter_egos;
    }

    public void setAlter_egos(List<String> alter_egos) {
        this.alter_egos = alter_egos;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getFirst_appearance() {
        return first_appearance;
    }

    public void setFirst_appearance(String first_appearance) {
        this.first_appearance = first_appearance;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
}
