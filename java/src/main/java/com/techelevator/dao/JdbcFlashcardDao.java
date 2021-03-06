package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.Flashcard;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcFlashcardDao implements FlashcardDao {
    JdbcTemplate jdbcTemplate;
    UserDao userDao;

    public JdbcFlashcardDao(JdbcTemplate jdbcTemplate, UserDao userDao)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
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

    @Override
    public List<Flashcard> getAllFlashcardsInDeck(Long deckId) throws Exception
    {
        // must be functionally limited to just one authenticated user
        List<Flashcard> flashcardList = new ArrayList<>();
        Flashcard addThisFlashcard = new Flashcard();
        String returnFlashcardSql = "SELECT f.flashcard_id, f.creator_id, f.question_text, " +
        " f.answer_text FROM flashcard f JOIN flashcard_deck fd " +
                "ON f.flashcard_id = fd.flashcard_id " +
                "WHERE fd.deck_id = ?; ";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(returnFlashcardSql, deckId);
            while (results.next())
            {
                addThisFlashcard = mapRowToFlashcard(results);
                flashcardList.add(addThisFlashcard);
            }

        }
        catch (Exception ex) {
            throw ex;
        }

        return flashcardList;
    }

    @Override
    public List<Flashcard> getAllFlashcardsNotInDeck(Long deckId) throws Exception {
        List<Flashcard> flashcardList = new ArrayList<>();
        Flashcard addThisFlashcard = new Flashcard();
        String returnFlashcardSql = "SELECT flashcard_id, creator_id, question_text, answer_text "
                + " FROM flashcard f WHERE flashcard_id NOT IN (SELECT DISTINCT f.flashcard_id " +
                "FROM flashcard f JOIN flashcard_deck fd ON f.flashcard_id = fd.flashcard_id " +
                "JOIN deck d ON d.creator_id=f.creator_id WHERE d.deck_id = ?);";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(returnFlashcardSql, deckId);
            while (results.next())
            {
                addThisFlashcard = mapRowToFlashcard(results);
                flashcardList.add(addThisFlashcard);
            }

        }
        catch (Exception ex) {
            throw ex;
        }

        return flashcardList;
    }


    @Override
    public List<Flashcard> getALlCardsByCreatorId(Long creatorId) throws Exception {
        List<Flashcard> flashcardList = new ArrayList<>();
        Flashcard addThisFlashcard = new Flashcard();
        String returnFlashcardSql = "SELECT f.flashcard_id, f.creator_id, f.question_text, " +
                " f.answer_text FROM flashcard f WHERE f.creator_id = ?; ";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(returnFlashcardSql, creatorId);
            while (results.next())
            {
                addThisFlashcard = mapRowToFlashcard(results);
                flashcardList.add(addThisFlashcard);
            }

        }
        catch (Exception ex) {
            throw ex;
        }

        return flashcardList;
    }


    @Override
    public Flashcard updateCard(Flashcard cardToUpdate) throws Exception {
        String sql = "UPDATE flashcard SET question_text = ?, " +
                "answer_text = ? WHERE flashcard_id = ?;";
        int rowsUpdated = -1;

        try {
            rowsUpdated = jdbcTemplate.update(sql,cardToUpdate.getQuestionText(),
                    cardToUpdate.getAnswerText(), cardToUpdate.getFlashcardId());
            cardToUpdate = viewFlashcardById(cardToUpdate.getFlashcardId());

        } catch (Exception ex) {
            throw new Exception();
        }

        if (rowsUpdated==1) {return cardToUpdate;}
        else {throw new Exception();}
    }


    private Flashcard mapRowToFlashcard(SqlRowSet rowSet) {
        Flashcard mappedFlashcard = new Flashcard();
        mappedFlashcard.setCreatorId(rowSet.getLong("creator_id"));
        mappedFlashcard.setQuestionText(rowSet.getString("question_text"));
        mappedFlashcard.setAnswerText(rowSet.getString("answer_text"));
        mappedFlashcard.setFlashcardId(rowSet.getLong("flashcard_id"));
        return mappedFlashcard;
    }

    // new function, not completely tested
    public boolean ownsCard(Principal principal, Long cardId) {
        boolean returnValue = false;
        try {
            Long userId = Long.valueOf(userDao.findIdByUsername(principal.getName()));
            Flashcard retrievedCard = viewFlashcardById(cardId);
            if (userId != retrievedCard.getCreatorId()) {
                returnValue = false;
            }
            else { returnValue = true; }
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }

        return returnValue;
    }
}
