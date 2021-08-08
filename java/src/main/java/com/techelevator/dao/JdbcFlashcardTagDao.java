package com.techelevator.dao;

import com.techelevator.model.Deck;
import com.techelevator.model.Flashcard;
import com.techelevator.model.FlashcardTag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                jdbcTemplate.update(sql, flashcardId, tagId );
            } catch (Exception ex){
                System.err.println(ex.getMessage());

            }

            return newFlashcardTag;
        }

    @Override
    public List<FlashcardTag> getFlashcardTagsByFlashcardId(Long flashcardId) {
        List<FlashcardTag> flashcardTagList = new ArrayList<>();
        FlashcardTag addThisFlashcardTag = new FlashcardTag();

        String returnFlashcardTagSql = "SELECT * FROM flashcard_tag " +
                "WHERE flashcard_id = ?; ";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(returnFlashcardTagSql, flashcardId);
            while (results.next())
            {
                addThisFlashcardTag = mapRowToFlashcardTag(results);
                flashcardTagList.add(addThisFlashcardTag);
            }

        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return flashcardTagList;
    }

    @Override
    public boolean deleteTagFromCard(Long flashcardId, Long tagId) {
        String sql = "DELETE FROM flashcard_tag WHERE flashcard_id = ? AND tag_id = ?;";
        return jdbcTemplate.update(sql, flashcardId, tagId) == 1;
    }

    private FlashcardTag mapRowToFlashcardTag(SqlRowSet rowSet) {
        FlashcardTag mappedFlashcardTag = new FlashcardTag();
        mappedFlashcardTag.setFlashcardId(rowSet.getLong("flashcard_id"));
        mappedFlashcardTag.setTagId(rowSet.getLong("tag_id"));
        return mappedFlashcardTag;
    }


}
