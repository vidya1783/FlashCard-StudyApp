package com.techelevator.dao;

import com.techelevator.model.Deck;
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

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Deck> getMyDecks(Principal principal) {
        List<Deck> usersDecks = new ArrayList<>();
        Deck deckToAdd = new Deck();

        String userName = principal.getName();
        String sql = "SELECT d.creator_id, d.deck_description, " +
                "d.deck_id, d.deck_name FROM deck d JOIN " +
                "user u ON d.creator_id = u.user_id WHERE " +
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
