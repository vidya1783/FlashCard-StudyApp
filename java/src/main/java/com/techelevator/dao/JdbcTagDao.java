package com.techelevator.dao;

import com.techelevator.model.Flashcard;
import com.techelevator.model.FlashcardTag;
import com.techelevator.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcTagDao implements TagDao {
    JdbcTemplate jdbcTemplate;
    public JdbcTagDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag createTag(String tagText, Long creatorId) {
        Tag newTag = new Tag(tagText, creatorId);
        Long newTagId = -1L;
        String makeTagSql = "INSERT INTO tag (tag_text, creator_id) VALUES " +
                "(?,?) RETURNING tag_id;";

        try {
            newTagId = jdbcTemplate.queryForObject(makeTagSql, Long.class, tagText, creatorId);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());

        }

        newTag.setTagId(newTagId);
        return newTag;


    }

    @Override
    public boolean updateTag(String tagText, Long tagId) {

        String sql = "UPDATE tag " +
                "SET tag_text = ? " +
                "WHERE tag_id = ?;";

        int rows = jdbcTemplate.update(sql,tagText, tagId);
        return rows == 1;
    }

    @Override
    public Tag getTagByTagId(Long tagId) { // I haven't tested this yet
        String sql = "SELECT tag_id, tag_text, creator_id FROM tag WHERE "
                + "tag_id = ?";
        Tag tagToReturn = new Tag();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tagId);
            while (results.next())
            {
                tagToReturn = mapRowToTag(results);
            }
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }

        return tagToReturn;
    }

    @Override
    public List<Tag> tagsOnAsingleCard(Long cardId) {
        String sql = "SELECT t.tag_id, t.tag_text, t.creator_id FROM tag t " +
                "JOIN flashcard_tag ft ON t.tag_id = ft.tag_id " +
                " WHERE flashcard_id = ?";
        List<Tag> cardsTags = runSqlAgainstId(sql, cardId);
        return cardsTags;

    }

    @Override
    public List<Tag> getAllTags() {
        List<Tag> listOfTags = new ArrayList<>();
        Tag addTagToList = new Tag();
        String sql = "SELECT tag_id, tag_text, creator_id FROM tag";
        SqlRowSet results  = jdbcTemplate.queryForRowSet(sql);
        while (results.next())
        {
            addTagToList = mapRowToTag(results);
            listOfTags.add(addTagToList);
        }

        return listOfTags;

    }

    @Override
    public Boolean tagExistsInDatabase(Long tagId) {
        String sql = "SELECT CASE WHEN ? IN " +
        "(SELECT tag_id FROM tag) THEN true ELSE false END";
        return trueFalseQuerySql(sql,tagId);
    }


    @Override
    public Boolean cardAlreadyHasAtag(Long cardId, Long tagId) {
        String sql = "SELECT CASE WHEN ? IN " +
                "(SELECT tag_id FROM flashcard_tag " +
                "WHERE flashcard_id = ?) " +
                "THEN true ELSE false END";
        return trueFalseQuerySql(sql,tagId,cardId);
    }

    public Boolean trueFalseQuerySql(String sql, Long idToTest) {
        Boolean trueFalse = jdbcTemplate.queryForObject(sql,Boolean.class,idToTest);
        return trueFalse;
    }

    public Boolean trueFalseQuerySql(String sql, Long idToTest, Long secondIdtoTest) {
        Boolean trueFalse = jdbcTemplate.queryForObject(sql,Boolean.class,idToTest,
                secondIdtoTest);
        return trueFalse;
    }



    private List<Tag> runSqlAgainstId(String sql, Long id)
    {
        List<Tag> listOfTags = new ArrayList<>();
        Tag addTagToList = new Tag();
        SqlRowSet results  = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next())
        {
            addTagToList = mapRowToTag(results);
            listOfTags.add(addTagToList);
        }
        return listOfTags;
    }

    private Tag mapRowToTag(SqlRowSet rowSet) {
        Tag mappedTag = new Tag();
        mappedTag.setTagId(rowSet.getLong("tag_id"));
        mappedTag.setCreatorId(rowSet.getLong("creator_id"));
        mappedTag.setTagText(rowSet.getString("tag_text"));
        return mappedTag;
    }


}
