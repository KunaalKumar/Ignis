package com.kunaalkumar.ignis.comicvine_objects.long_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.VolumeBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.FirstAppearedInIssue;
import com.kunaalkumar.ignis.comicvine_objects.misc.Image;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class Volume extends VolumeBrief {

    @SerializedName("characters")
    private Relation[] characters;

    @SerializedName("concepts")
    private Relation[] concepts;

    @SerializedName("issues")
    private Relation[] issues;

    @SerializedName("objects")
    private Relation[] objects;

    @SerializedName("people")
    private Relation[] people;

    public Volume(String apiDetailUrl, String deck, String dateLastUpdated, Image image, String name, Integer id, String siteDetailUrl, String aliases, Integer countOfIssues, FirstAppearedInIssue firstIssue, FirstAppearedInIssue lastIssue, Relation publisher, String startYear, Relation[] characters, Relation[] concepts, Relation[] issues, Relation[] objects, Relation[] people) {
        super(apiDetailUrl, deck, dateLastUpdated, image, name, id, siteDetailUrl, aliases, countOfIssues, firstIssue, lastIssue, publisher, startYear);
        this.characters = characters;
        this.concepts = concepts;
        this.issues = issues;
        this.objects = objects;
        this.people = people;
    }

    public Relation[] getCharacters() {
        return characters;
    }

    public Relation[] getConcepts() {
        return concepts;
    }

    public Relation[] getIssues() {
        return issues;
    }

    public Relation[] getObjects() {
        return objects;
    }

    public Relation[] getPeople() {
        return people;
    }
}
