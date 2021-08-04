package com.techelevator.dao;

import com.techelevator.model.FlashcardTag;

import java.util.List;

public interface FlashcardTagDao {
    public FlashcardTag createFlashcardTag(Long flashcardId, Long tagId);

    public List<FlashcardTag>  getFlashcardTagsByFlashcardId(Long flashcardId);

    public boolean deleteTagFromCard(Long flashcardId, Long tagId);
}
