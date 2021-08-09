package com.techelevator.controller;

import com.techelevator.dao.DeckDao;
import com.techelevator.dao.FlashcardDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Deck;
import com.techelevator.model.Flashcard;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class FlashcardController {

    private FlashcardDao flashcardDao;
    private UserDao userDao;
    private DeckDao deckDao;

    public FlashcardController(FlashcardDao flashcardDao, UserDao userDao, DeckDao deckDao)
    {
        this.flashcardDao = flashcardDao;
        this.userDao = userDao;
        this.deckDao = deckDao;
    }

    /**
     * @return the ID of a newly created card
     * **/
    @RequestMapping(path="flashcard", method= RequestMethod.POST)
    public Flashcard createFlashcard(@RequestBody Flashcard flashcard, Principal principal) throws Exception
    {
        Flashcard newFlashcard;
        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));
        flashcard.setCreatorId(userId);

        try {
            newFlashcard = flashcardDao.createTextFlashcard(flashcard.getCreatorId(),
                    flashcard.getQuestionText(), flashcard.getAnswerText());
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            throw ex;
        }

        return newFlashcard;
    }

    @RequestMapping(path="flashcard/{id}", method = RequestMethod.GET)
    public Flashcard getUsersFlashcard(@PathVariable Long id, Principal principal) throws Exception {
        Flashcard soughtFlashcard;

        try {
            soughtFlashcard = flashcardDao.viewFlashcardById(id);
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            throw ex;
        }

        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));

        if (soughtFlashcard.getCreatorId()!=userId)
        {
            throw new Exception("Hello, the creator ID does not match");
        }

        return soughtFlashcard;
    }

    @RequestMapping(path="flashcards",method=RequestMethod.GET)
    public List<Flashcard> getAllFlashcardsForUser(Principal principal) throws Exception
    {
        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));
        List<Flashcard> usersFlashcards = new ArrayList<>();
        try {
            usersFlashcards = flashcardDao.getALlCardsByCreatorId(userId);
        } catch (Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        return usersFlashcards;
    }

    @RequestMapping(path="flashcard", method=RequestMethod.PUT)
    public Flashcard updateUsersFlashcard(@RequestBody Flashcard flashcard, Principal principal) throws Exception
    {
        Flashcard updatedFlashcard = new Flashcard();
        try {
            Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));
            Flashcard retrievedCard = flashcardDao.viewFlashcardById(flashcard.getFlashcardId());
            if (retrievedCard.getCreatorId()!=userId) {
                throw new Exception("User ID's don't match");
            }
            updatedFlashcard = flashcardDao.updateCard(flashcard);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return updatedFlashcard;
    }

    @RequestMapping(path="deckcards/{deckId}", method = RequestMethod.GET)
    public List<Flashcard> getDeckCards(@PathVariable Long deckId, Principal principal) throws Exception {
        List<Flashcard> flashcardList = new ArrayList<>();
        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));

        Deck foundDeck = deckDao.retrieveDeck(deckId);
        if (foundDeck.getCreatorId()!=userId) {
            throw new Exception("ID's don't match");
        }

        try {
            flashcardList = flashcardDao.getAllFlashcardsInDeck(deckId);
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            throw ex;
        }

        return flashcardList;
    }

    @RequestMapping(path="notindeckcards/{deckId}", method = RequestMethod.GET)
    public List<Flashcard> getNotInDeckCards(@PathVariable Long deckId, Principal principal){
        List<Flashcard> flashcardList = new ArrayList<>();
        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));

        Deck foundDeck = deckDao.retrieveDeck(deckId);
        if (foundDeck.getCreatorId()!=userId) {
            return null;
        }

        try {
            flashcardList = flashcardDao.getAllFlashcardsNotInDeck(deckId);
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            return null;
        }

        return flashcardList;
    }




}
