package com.kunaalkumar.ignis.comicvine_objects.long_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.IssueBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class Issue extends IssueBrief {

    @SerializedName("character_credits")
    private Relation[] characterCredits;

    @SerializedName("character_died_in")
    private Relation[] characterDiedIn;

    @SerializedName("concept_credits")
    private Relation[] conceptCredits;

    @SerializedName("first_appearance_characters")
    private Relation[] firstAppearanceCharacters;

    @SerializedName("first_appearance_locations")
    private Relation[] firstAppearanceLocations;

    @SerializedName("first_appearance_objects")
    private Relation[] firstAppearanceObjects;

    @SerializedName("first_appearance_storyarcs")
    private Relation[] firstAppearanceStoryarcs;

    @SerializedName("first_appearance_teams")
    private Relation[] firstAppearanceTeams;

    @SerializedName("location_credits")
    private Relation[] locationCredits;

    @SerializedName("object_credits")
    private Relation[] objectCredits;

    // May need to make a new object just for this
    @SerializedName("person_credits")
    private Relation[] personCredits;

    @SerializedName("story_arc_credits")
    private Relation[] storyArcCredits;

    @SerializedName("team_credits")
    private Relation[] teamCredits;

    public Issue(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, String coverDate, String issueNumber, String storeDate, Relation volume, Relation[] characterCredits, Relation[] characterDiedIn, Relation[] conceptCredits, Relation[] firstAppearanceCharacters, Relation[] firstAppearanceLocations, Relation[] firstAppearanceObjects, Relation[] firstAppearanceStoryarcs, Relation[] firstAppearanceTeams, Relation[] locationCredits, Relation[] objectCredits, Relation[] personCredits, Relation[] storyArcCredits, Relation[] teamCredits) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl, aliases, coverDate, issueNumber, storeDate, volume);
        this.characterCredits = characterCredits;
        this.characterDiedIn = characterDiedIn;
        this.conceptCredits = conceptCredits;
        this.firstAppearanceCharacters = firstAppearanceCharacters;
        this.firstAppearanceLocations = firstAppearanceLocations;
        this.firstAppearanceObjects = firstAppearanceObjects;
        this.firstAppearanceStoryarcs = firstAppearanceStoryarcs;
        this.firstAppearanceTeams = firstAppearanceTeams;
        this.locationCredits = locationCredits;
        this.objectCredits = objectCredits;
        this.personCredits = personCredits;
        this.storyArcCredits = storyArcCredits;
        this.teamCredits = teamCredits;
    }

    public Relation[] getCharacterCredits() {
        return characterCredits;
    }

    public Relation[] getCharacterDiedIn() {
        return characterDiedIn;
    }

    public Relation[] getConceptCredits() {
        return conceptCredits;
    }

    public Relation[] getFirstAppearanceCharacters() {
        return firstAppearanceCharacters;
    }

    public Relation[] getFirstAppearanceLocations() {
        return firstAppearanceLocations;
    }

    public Relation[] getFirstAppearanceObjects() {
        return firstAppearanceObjects;
    }

    public Relation[] getFirstAppearanceStoryarcs() {
        return firstAppearanceStoryarcs;
    }

    public Relation[] getFirstAppearanceTeams() {
        return firstAppearanceTeams;
    }

    public Relation[] getLocationCredits() {
        return locationCredits;
    }

    public Relation[] getObjectCredits() {
        return objectCredits;
    }

    public Relation[] getPersonCredits() {
        return personCredits;
    }

    public Relation[] getStoryArcCredits() {
        return storyArcCredits;
    }

    public Relation[] getTeamCredits() {
        return teamCredits;
    }
}
