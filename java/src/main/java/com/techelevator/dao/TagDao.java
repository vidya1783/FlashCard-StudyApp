package com.techelevator.dao;

import com.techelevator.model.Tag;

import java.util.List;

public interface TagDao {
    public Tag createTag(String tagText, Long creatorId);

    public boolean updateTag(String tagText, Long tagId);

    public Tag getTagByTagId(Long tagId);

    public List<Tag> tagsOnAsingleCard(Long cardId);

    public List<Tag> getAllTags();

    public Boolean tagExistsInDatabase(Long tagId);

    public Boolean cardAlreadyHasAtag(Long cardId, Long tagId);

}
