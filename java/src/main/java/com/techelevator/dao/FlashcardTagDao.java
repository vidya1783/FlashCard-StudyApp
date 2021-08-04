package com.techelevator.dao;

import com.techelevator.model.FlashcardTag;

public interface FlashcardTagDao {
    public FlashcardTag createFlashcardTag(Long flashcardId, Long tagId);
}
