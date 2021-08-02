package com.techelevator.controller;

import com.techelevator.dao.FlashcardDao;
import com.techelevator.model.Flashcard;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlashcardController {

    private FlashcardDao flashcardDao;

    public FlashcardController(FlashcardDao flashcardDao)
    {
        this.flashcardDao = flashcardDao;
    }

    /**
     * @return the ID of a newly created card
     * **/
    @RequestMapping(path="flashcard", method= RequestMethod.POST)
    public Flashcard createFlashcard(@RequestBody Flashcard flashcard) throws Exception
    {
        Flashcard newFlashcard;

        Long id = flashcard.getCreatorId();
        String questionText = flashcard.getQuestionText();
        String answerText = flashcard.getAnswerText();


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
