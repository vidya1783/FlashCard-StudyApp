package com.techelevator.dao;

import com.techelevator.model.Tag;

public interface TagDao {
    public Tag createTag(String tagText, Long creatorId);

    public boolean updateTag(String tagText, Long tagId);



}
