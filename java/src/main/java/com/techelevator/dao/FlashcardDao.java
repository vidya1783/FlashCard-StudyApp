package com.techelevator.dao;

import com.techelevator.model.Flashcard;

public interface FlashcardDao {
    public Flashcard createTextFlashcard(Long creatorId, String questionText,
                                         String answerText) throws Exception;
}
