package com.techelevator.controller;

import com.techelevator.dao.DeckDao;
import com.techelevator.dao.FlashcardDao;
import com.techelevator.dao.FlashcardPlusTagDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.FlashcardPlusTag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class FlashcardPlusTagController{

    private FlashcardPlusTagDao flashcardPlusTagDao;
    private UserDao userDao;

    public FlashcardPlusTagController(FlashcardPlusTagDao flashcardPlusTagDao, UserDao userDao)
    {
        this.flashcardPlusTagDao = flashcardPlusTagDao;
        this.userDao = userDao;
    }

    @RequestMapping(path="taggedcard/{cardId}", method = RequestMethod.GET)
    public FlashcardPlusTag getFlashcardPlusTagById(@PathVariable long cardId, Principal principal) throws Exception {
        if (principal==null) { throw new Exception("Get authenticated and access your own cards.");}
        FlashcardPlusTag flashcardPlusTag = new FlashcardPlusTag();
        try {
            flashcardPlusTag = flashcardPlusTagDao.getFlashcardWithTagByFlashcardId(cardId);
        } catch (Exception ex)
        {
            throw ex;
        }

        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));

        if (flashcardPlusTag.getCreatorId()!=userId)
        {
            throw new Exception("Hello, the creator ID does not match");
        }

        return flashcardPlusTag;
    }


}
