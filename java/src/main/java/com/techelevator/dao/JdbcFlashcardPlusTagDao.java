package com.techelevator.dao;

import com.techelevator.model.Flashcard;
import com.techelevator.model.FlashcardPlusTag;
import com.techelevator.model.FlashcardTag;
import com.techelevator.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcFlashcardPlusTagDao implements FlashcardPlusTagDao {

    FlashcardDao flashcardDao;
    TagDao tagDao;

    JdbcTemplate jdbcTemplate;

    public JdbcFlashcardPlusTagDao(JdbcTemplate jdbcTemplate,
                               FlashcardDao flashcardDao, TagDao tagDao)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.flashcardDao = flashcardDao;
        this.tagDao = tagDao;
    }

    @Override
    public FlashcardPlusTag getFlashcardWithTagByFlashcardId(Long flashcardId) throws Exception {
        Flashcard retrievedCard = new Flashcard();
        try {
            retrievedCard = flashcardDao.viewFlashcardById(flashcardId);
        } catch (Exception ex) {
            throw ex;
        }

        FlashcardPlusTag flashcardPlusTag = new FlashcardPlusTag(retrievedCard);

        List<Tag> tagsOnFlashcard = tagDao.tagsOnAsingleCard(retrievedCard.getFlashcardId());

        flashcardPlusTag.setTagList(tagsOnFlashcard);

        return flashcardPlusTag;
    }

}