package com.techelevator.dao;

import com.techelevator.model.Flashcard;

import java.util.List;

public interface FlashcardDao {
    public Flashcard createTextFlashcard(Long creatorId, String questionText,
                                         String answerText) throws Exception;

    public Flashcard viewFlashcardById(Long flashcardId) throws Exception;

    public List<Flashcard> getAllFlashcardsInDeck(Long deckId) throws Exception;
}
