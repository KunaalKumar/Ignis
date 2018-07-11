package com.kunaalkumar.ignis.comicvine_objects.long_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.PublisherBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class Publisher extends PublisherBrief {

    @SerializedName("characters")
    private Relation[] characters;

    @SerializedName("story_arcs")
    private Relation[] storyArcs;

    @SerializedName("teams")
    private Relation[] teams;

    @SerializedName("volumes")
    private Relation[] volumes;

    public Publisher(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, String locationAddress, String locationCity, String locationState, Relation[] characters, Relation[] storyArcs, Relation[] teams, Relation[] volumes) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl, aliases, locationAddress, locationCity, locationState);
        this.characters = characters;
        this.storyArcs = storyArcs;
        this.teams = teams;
        this.volumes = volumes;
    }

    public Relation[] getCharacters() {
        return characters;
    }

    public Relation[] getStoryArcs() {
        return storyArcs;
    }

    public Relation[] getTeams() {
        return teams;
    }

    public Relation[] getVolumes() {
        return volumes;
    }
}
