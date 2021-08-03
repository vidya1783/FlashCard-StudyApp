package com.techelevator.controller;

import com.techelevator.dao.FlashcardDao;
import com.techelevator.dao.TagDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Flashcard;
import com.techelevator.model.Tag;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
public class TagController {
    private FlashcardDao flashcardDao;
    private UserDao userDao;
    private TagDao tagDao;
    public TagController(FlashcardDao flashcardDao, UserDao userDao, TagDao tagDao) {
        this.flashcardDao = flashcardDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
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
            throw new Exception();
        }

        return newTag;
    }



}
