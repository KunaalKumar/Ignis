package com.kunaalkumar.ignis.comicvine_objects;

import com.google.gson.annotations.SerializedName;

public class Character extends CharacterResults {

    @SerializedName("character_enemies")
    private RelatedCharacter[] characterEnemies;

    @SerializedName("character_friends")
    private RelatedCharacter[] characterFriends;

    @SerializedName("count_of_issue_appearances")
    private Integer coundOfIssueAppearances;

    @SerializedName("creators")
    private RelatedCharacter[] creators;

    public Character(String aliases, String birth, Integer countOfIssueAppearances, String dateLastUpdated, String briefSummary, String description, CharacterResults.FirstAppearedInIssue firstAppearedInIssue, int gender, Integer id, CharacterResults.Image image, CharacterResults.Origin origin, CharacterResults.Publisher publisher, String realName, String name) {
        super(aliases, birth, countOfIssueAppearances, dateLastUpdated, briefSummary, description, firstAppearedInIssue, gender, id, image, origin, publisher, realName, name);
    }


    public class RelatedCharacter {

        @SerializedName("id")
        private Integer id;

        @SerializedName("name")
        private String name;

        public RelatedCharacter(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
