package com.techelevator.controller;

import com.techelevator.dao.FlashcardDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Flashcard;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
