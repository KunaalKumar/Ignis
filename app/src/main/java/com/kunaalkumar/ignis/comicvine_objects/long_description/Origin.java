package com.kunaalkumar.ignis.comicvine_objects.long_description;

import com.google.gson.annotations.SerializedName;
import com.kunaalkumar.ignis.comicvine_objects.brief_description.OriginBrief;
import com.kunaalkumar.ignis.comicvine_objects.misc.Relation;

public class Origin extends OriginBrief {

    @SerializedName("characters")
    private Relation[] characters;

    public Origin(Integer id, String name, String apiDetailUrl, String siteDetailUrl, Relation[] characters) {
        super(id, name, apiDetailUrl, siteDetailUrl);
        this.characters = characters;
    }

    public Relation[] getCharacters() {
        return characters;
    }
}
