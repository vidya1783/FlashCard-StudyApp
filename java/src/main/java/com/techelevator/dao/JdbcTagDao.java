package com.techelevator.dao;

import com.techelevator.model.Flashcard;
import com.techelevator.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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




}
