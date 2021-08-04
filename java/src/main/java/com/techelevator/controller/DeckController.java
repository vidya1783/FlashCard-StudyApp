package com.techelevator.controller;

import com.techelevator.dao.DeckDao;
import com.techelevator.dao.FlashcardDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Deck;
import com.techelevator.model.Flashcard;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class DeckController {
    private DeckDao deckDao;
    private UserDao userDao;

    public DeckController(DeckDao deckDao, UserDao userDao)
    {
        this.deckDao = deckDao;
        this.userDao = userDao;
    };

    @RequestMapping(path="deck", method= RequestMethod.POST)
    public Deck createDeck
            (@RequestBody Deck deck, Principal principal) throws Exception
    {
        Deck newDeck;
        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));
        deck.setCreatorId(userId);

        try {
            newDeck = deckDao.createDeck(deck.getCreatorId(),
                    deck.getDeckName(), deck.getDeckDescription());
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            throw ex;
        }
        return newDeck;
    }


    @RequestMapping(path="decks", method= RequestMethod.GET)
    public List<Deck> viewUsersDecks(Principal principal) throws Exception
    {
        List<Deck> returnDeckList = new ArrayList<>();
        try {
            returnDeckList = deckDao.getMyDecks(principal);
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            throw ex;
        }
        return returnDeckList;
    }

    @RequestMapping(path="deck/{deckId}", method= RequestMethod.GET)
    public Deck getDeckByDeckId(@PathVariable Long deckId, Principal principal) throws Exception {
        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));

        Deck searchedDeck = deckDao.retrieveDeck(deckId);

        if (searchedDeck.getCreatorId()!=userId)
        {
            throw new Exception("unauthorized");
        }
        return searchedDeck;
    }

    //

    @RequestMapping(path="deck", method=RequestMethod.PUT)
    public Deck updateUsersDeck(@RequestBody Deck deck, Principal principal) throws Exception
    {
        Deck updatedDeck = new Deck();
        try {
            Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));
            Deck retrievedDeck = deckDao.retrieveDeck(deck.getDeckId());
            if (retrievedDeck.getCreatorId()!=userId) {
                throw new Exception();
            }
            updatedDeck = deckDao.updateDeck(deck);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return updatedDeck;
    }

}
