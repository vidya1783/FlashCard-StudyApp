package com.techelevator.dao;

import com.techelevator.model.Deck;

public interface DeckDao {

    public Deck createDeck(Long creatorId, String deckName, String deckDescription) throws Exception;

}
