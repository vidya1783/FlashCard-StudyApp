package com.techelevator.controller;

import com.techelevator.dao.DeckDao;
import com.techelevator.dao.FlashcardDao;
import com.techelevator.model.Deck;
import com.techelevator.model.Flashcard;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeckController {
    private DeckDao deckDao;

    public DeckController(DeckDao deckDao)
    {
        this.deckDao = deckDao;
    };

    @RequestMapping(path="deck", method= RequestMethod.POST)
    public Deck createDeck
            (@RequestBody Deck deck) throws Exception
    {
        Deck newDeck;
        Long id = deck.getCreatorId();
        String deckName = deck.getDeckName();
        String deckDescription = deck.getDeckDescription();

        try {
            newDeck = deckDao.createDeck(deck.getCreatorId(),
                    deck.getDeckName(), deck.getDeckDescription());
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            throw new Exception();
        }
        return newDeck;
    }

}
