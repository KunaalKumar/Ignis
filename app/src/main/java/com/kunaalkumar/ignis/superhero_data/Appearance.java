package com.kunaalkumar.ignis.superhero_data;

public class Appearance {
    private String gender;
    private String race;
    // Imperial and metric
    private String[] height;
    private String[] weight;
    private String eye_color;
    private String hair_color;

    public Appearance(String gender, String race, String[] height, String[] weight, String eye_color, String hair_color) {
        this.gender = gender;
        this.race = race;
        this.height = height;
        this.weight = weight;
        this.eye_color = eye_color;
        this.hair_color = hair_color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String[] getHeight() {
        return height;
    }

    public void setHeight(String[] height) {
        this.height = height;
    }

    public String[] getWeight() {
        return weight;
    }

    public void setWeight(String[] weight) {
        this.weight = weight;
    }

    public String getEye_color() {
        return eye_color;
    }

    public void setEye_color(String eye_color) {
        this.eye_color = eye_color;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }
}
