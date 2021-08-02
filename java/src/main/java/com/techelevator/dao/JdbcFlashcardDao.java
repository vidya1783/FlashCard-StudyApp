package com.techelevator.dao;

import com.techelevator.model.Flashcard;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcFlashcardDao implements FlashcardDao {
    JdbcTemplate jdbcTemplate;

    public JdbcFlashcardDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Flashcard createTextFlashcard(Long creatorId, String questionText, String answerText) throws Exception {

        Flashcard newFlashcard = new Flashcard(creatorId, questionText, answerText);
        Long newFlashcardId;
        String makeFlashcardSql = "INSERT INTO flashcard (creator_id, question_text, answer_text) VALUES " +
                "(?,?,?) RETURNING flashcard_id;";

        try {
        newFlashcardId = jdbcTemplate.queryForObject(makeFlashcardSql, Long.class, creatorId,
                questionText, answerText);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            throw new Exception();
        }

        newFlashcard.setId(newFlashcardId);
        return newFlashcard;

    }
}
