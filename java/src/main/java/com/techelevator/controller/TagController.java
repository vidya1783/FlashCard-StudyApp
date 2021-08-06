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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@Service // added by JM for the sake of serving a card tag update
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

    @RequestMapping(path="tags", method=RequestMethod.GET)
    public List<Tag> getAllTags()
    {
        return tagDao.getAllTags();

    }

    @RequestMapping(path="tag/{flashcardId}", method= RequestMethod.GET)
    public List<FlashcardTag> getFlashcardTagsByFlashcardId(@PathVariable Long flashcardId){
        List <FlashcardTag> FlashcardTagsList = flashcardTagDao.getFlashcardTagsByFlashcardId(flashcardId);
        return FlashcardTagsList;
    }


    @RequestMapping(path="deletetag/{flashcardId}/{tagId}", method= RequestMethod.DELETE)
    public boolean deleteTagFromFlashcard(@PathVariable Long flashcardId,
                                          @PathVariable Long tagId, Principal principal){
        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));
        if(tagDao.getTagByTagId(tagId).getCreatorId()!= userId) return false;
        return flashcardTagDao.deleteTagFromCard(flashcardId, tagId);
    }

    @RequestMapping(path="updatetag", method= RequestMethod.PUT)
    public boolean updateTag(@RequestBody Tag tag, Principal principal){

        Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));
        if (userId != tag.getCreatorId()){
            return false;
        }

        return tagDao.updateTag(tag.getTagText(), tag.getTagId());

    }

    // requires tags to already be in the database or throws an error
    // takes a list of tags and a card Id, tries to make that tag list match the database
    // by updating appropriate tables
    public List<Tag> updateCardTagList(List<Tag> newCardTagList, Long cardId, Principal principal)
    throws Exception {
        boolean ownsCard = flashcardDao.ownsCard(principal, cardId);
        if (!ownsCard) {throw new Exception("Not an owner of the card sought to be updated.");}
        Flashcard retrievedCard = flashcardDao.viewFlashcardById(cardId);

        // this function is incomplete
        return null;
        ////////////////////////////////////// needs to be completed
    }



}
