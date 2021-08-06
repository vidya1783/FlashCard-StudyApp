package com.techelevator.dao;

import com.techelevator.model.FlashcardPlusTag;

public interface FlashcardPlusTagDao {
    public FlashcardPlusTag getFlashcardWithTagByFlashcardId (Long flashcardId) throws Exception;
}
