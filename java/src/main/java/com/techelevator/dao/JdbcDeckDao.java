package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.Flashcard;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Service
public class JdbcDeckDao implements DeckDao {

    JdbcTemplate jdbcTemplate;
    FlashcardDao flashcardDao;

    public JdbcDeckDao(JdbcTemplate jdbcTemplate, FlashcardDao flashcardDao)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.flashcardDao = flashcardDao;
    }

    @Override
    public Deck createDeck(Long creatorId, String deckName, String deckDescription) throws Exception {
        Long newDeckId;
        Deck newDeck = new Deck(creatorId, deckName, deckDescription);
        String sql = " INSERT INTO deck (creator_id, deck_name, deck_description) " +
        " VALUES (?, ?, ?) RETURNING deck_id;";
        try {
            newDeckId = jdbcTemplate.queryForObject(sql, Long.class, creatorId, deckName, deckDescription );
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            throw new Exception();
        }
        newDeck.setDeckId(newDeckId);
        return newDeck;
    }

    @Override
    public Deck retrieveDeck(Long deckId)
    {
        Deck foundDeck = new Deck();
        String sql = "SELECT deck_id, creator_id, deck_name, deck_description FROM deck " +
                "WHERE deck_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deckId);
        while (results.next())
        {
            foundDeck = mapRowToDeck(results);
        }

        return foundDeck;

    }

    @Override
    public Deck updateDeck(Deck deckToUpdate) throws Exception {
        String sql = "UPDATE deck SET deck_name = ?, " +
                "deck_description = ? WHERE deck_id = ?;";
        int rowsUpdated = -1;

        try {
            rowsUpdated = jdbcTemplate.update(sql,deckToUpdate.getDeckName(),
                    deckToUpdate.getDeckDescription(), deckToUpdate.getDeckId());
            deckToUpdate = retrieveDeck(deckToUpdate.getDeckId());

        } catch (Exception ex) {
            throw new Exception();
        }

        if (rowsUpdated==1) {return deckToUpdate;}
        else {throw new Exception();}
    }

    @Override
    public List<Flashcard> addDeckCard(Long deckId, Long flashcardId) throws Exception {
        String sql = "INSERT INTO flashcard_deck (deck_id, flashcard_id) VALUES (?, ?);";
        int rowsUpdated = -1;
        try {
            rowsUpdated = jdbcTemplate.update(sql,deckId, flashcardId);
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
        if (rowsUpdated != 1) {throw new Exception("Card not added");}
        return flashcardDao.getAllFlashcardsInDeck(deckId);

    }

    @Override
    public boolean removeDeckCard(Long deckId, Long flashcardId) throws Exception {
        String sql = "DELETE FROM flashcard_deck " +
                "WHERE deck_id = ? AND flashcard_id = ?;";
        int rows = 0;
        try {
            rows = jdbcTemplate.update(sql,deckId, flashcardId);
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
        if (rows != 1) {throw new Exception("Card not deleted");}
        if (rows == 1){ return true;};
        return false;
    }

    @Override
    public List<Deck> getMyDecks(Principal principal) {
        List<Deck> usersDecks = new ArrayList<>();
        Deck deckToAdd = new Deck();

        String userName = principal.getName();
        String sql = "SELECT d.creator_id, d.deck_description, " +
                "d.deck_id, d.deck_name FROM deck d JOIN " +
                "users u ON d.creator_id = u.user_id WHERE " +
                "u.username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
        while (results.next())
        {
            deckToAdd = mapRowToDeck(results);
            usersDecks.add(deckToAdd);
        }

        return usersDecks;

    }

    private Deck mapRowToDeck(SqlRowSet rowSet) {
        Deck mappedDeck = new Deck();
        mappedDeck.setCreatorId(rowSet.getLong("creator_id"));
        mappedDeck.setDeckDescription(rowSet.getString("deck_description"));
        mappedDeck.setDeckId(rowSet.getLong("deck_id"));
        mappedDeck.setDeckName(rowSet.getString("deck_name"));
        return mappedDeck;
    }
}
