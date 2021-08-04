package com.techelevator.controller;

import com.techelevator.dao.FlashcardDao;
import com.techelevator.dao.FlashcardTagDao;
import com.techelevator.dao.TagDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Flashcard;
import com.techelevator.model.FlashcardTag;
import com.techelevator.model.Tag;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class TagController {
    private FlashcardDao flashcardDao;
    private UserDao userDao;
    private TagDao tagDao;
    private FlashcardTagDao flashcardTagDao;
    public TagController(FlashcardDao flashcardDao, UserDao userDao, TagDao tagDao, FlashcardTagDao flashcardTagDao) {
        this.flashcardDao = flashcardDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
        this.flashcardTagDao = flashcardTagDao;
    }

    @RequestMapping(path="tag", method= RequestMethod.POST)
    public Tag createTag(@RequestBody Tag tag, Principal principal) throws Exception
    {
        Tag newTag;
        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));
        tag.setCreatorId(userId);

        try {
            newTag = tagDao.createTag(tag.getTagText(), tag.getCreatorId());

        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            throw ex;
        }

        return newTag;
    }

    @RequestMapping(path="tag/flashcard", method= RequestMethod.POST)
    public FlashcardTag createFlashcardTag(@RequestBody FlashcardTag flashcardTag, Principal principal) throws Exception
    {
        FlashcardTag newFlashcardTag;
        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));


        try {
            newFlashcardTag = flashcardTagDao.createFlashcardTag(flashcardTag.getFlashcardId(), flashcardTag.getTagId());

        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            throw ex;
        }

        return newFlashcardTag;
    }

    @RequestMapping(path="tag/{flashcardId}", method= RequestMethod.GET)
    public List<FlashcardTag> getFlashcardTagsByFlashcardId(@PathVariable Long flashcardId){
        List <FlashcardTag> FlashcardTagsList = flashcardTagDao.getFlashcardTagsByFlashcardId(flashcardId);
        return FlashcardTagsList;
    }

    @RequestMapping(path="deletetag/{flashcardId}/{tagId}", method= RequestMethod.DELETE)
    public boolean deleteTagFromFlashcard(@PathVariable Long flashcardId,@PathVariable Long tagId){
        return flashcardTagDao.deleteTagFromCard(flashcardId, tagId);
    }



}
