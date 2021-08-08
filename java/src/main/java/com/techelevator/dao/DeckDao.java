package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.Flashcard;

import java.security.Principal;
import java.util.List;

public interface DeckDao {

    public Deck createDeck(Long creatorId, String deckName, String deckDescription) throws Exception;

    public List<Deck> getMyDecks(Principal principal);

    public Deck retrieveDeck(Long deckId);

    public Deck updateDeck(Deck deckToUpdate) throws Exception;

    public List<Flashcard> addDeckCard(Long deckId, Long flashcardId) throws Exception;

}
