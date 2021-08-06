package com.techelevator.dao;

import com.techelevator.model.Flashcard;

import java.security.Principal;
import java.util.List;

public interface FlashcardDao {
    public Flashcard createTextFlashcard(Long creatorId, String questionText,
                                         String answerText) throws Exception;

    public Flashcard viewFlashcardById(Long flashcardId) throws Exception;

    public List<Flashcard> getAllFlashcardsInDeck(Long deckId) throws Exception;

    public List<Flashcard> getALlCardsByCreatorId (Long creatorId) throws Exception;

    public Flashcard updateCard(Flashcard cardToUpdate) throws Exception;

    public boolean ownsCard(Principal principal, Long cardId);


}
