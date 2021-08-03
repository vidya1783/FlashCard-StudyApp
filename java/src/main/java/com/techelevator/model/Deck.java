package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deck {
    @JsonProperty("deck_id")
    private Long deckId = -1L;
    @JsonProperty("creator_id")
    private Long creatorId;
    @JsonProperty("deck_name")
    private String deckName;
    @JsonProperty("deck_description")
    private String deckDescription;

    public Deck(){};

    public Deck(Long creatorId, String deckName, String deckDescription){
        this.setCreatorId(creatorId);
        this.setDeckName(deckName);
        this.setDeckDescription(deckDescription);
    };


    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getDeckDescription() {
        return deckDescription;
    }

    public void setDeckDescription(String deckDescription) {
        this.deckDescription = deckDescription;
    }
}
