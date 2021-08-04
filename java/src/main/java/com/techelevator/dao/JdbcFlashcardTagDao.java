package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.FlashcardTag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcFlashcardTagDao implements FlashcardTagDao {
    JdbcTemplate jdbcTemplate;

    public JdbcFlashcardTagDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FlashcardTag createFlashcardTag(Long flashcardId, Long tagId) {

            FlashcardTag newFlashcardTag = new FlashcardTag(flashcardId, tagId);
            String sql = " INSERT INTO flashcard_tag (flashcard_id, tag_id) " +
                    " VALUES (?, ?) ;";
            try {
                jdbcTemplate.queryForObject(sql, Long.class, flashcardId, tagId );
            } catch (Exception ex){
                System.err.println(ex.getMessage());

            }

            return newFlashcardTag;
        }



}
