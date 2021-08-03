package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.Flashcard;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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

        newFlashcard.setFlashcardId(newFlashcardId);
        return newFlashcard;

    }

    @Override
    public Flashcard viewFlashcardById(Long flashcardId) throws Exception {
        Flashcard returnFlashcard = new Flashcard();
        String returnFlashcardSql = "SELECT flashcard_id, creator_id, question_text, answer_text FROM flashcard " +
                "WHERE flashcard_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(returnFlashcardSql, flashcardId);
            while (results.next())
            {
                returnFlashcard = mapRowToFlashcard(results);
            }

        }
        catch (Exception ex) {
            throw ex;
        }

        return returnFlashcard;
    }

    private Flashcard mapRowToFlashcard(SqlRowSet rowSet) {
        Flashcard mappedFlashcard = new Flashcard();
        mappedFlashcard.setCreatorId(rowSet.getLong("creator_id"));
        mappedFlashcard.setQuestionText(rowSet.getString("question_text"));
        mappedFlashcard.setAnswerText(rowSet.getString("answer_text"));
        mappedFlashcard.setFlashcardId(rowSet.getLong("flashcard_id"));
        return mappedFlashcard;
    }
}
