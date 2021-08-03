package com.techelevator.controller;

import com.techelevator.dao.FlashcardDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Flashcard;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
public class FlashcardController {

    private FlashcardDao flashcardDao;
    private UserDao userDao;

    public FlashcardController(FlashcardDao flashcardDao, UserDao userDao)
    {
        this.flashcardDao = flashcardDao;
        this.userDao = userDao;
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
            throw new Exception();
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


}
