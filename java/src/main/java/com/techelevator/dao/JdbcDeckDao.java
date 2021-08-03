package com.techelevator.dao;

import com.techelevator.model.Deck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcDeckDao implements DeckDao {

    JdbcTemplate jdbcTemplate;

    public JdbcDeckDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
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
}
