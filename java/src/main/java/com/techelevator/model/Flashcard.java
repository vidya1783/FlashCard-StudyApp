package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Flashcard {
    @JsonProperty("flashcard_id")
    private Long flashcardId = -1L;
    @JsonProperty("creator_id")
    private Long creatorId;
    @JsonProperty("question_text")
    private String questionText;
    @JsonProperty("answer_text")
    private String answerText;

    public Long getFlashcardId() {
        return flashcardId;
    }

    public void setFlashcardId(Long id) {
        this.flashcardId = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Flashcard()
    {}

    public Flashcard(Long creatorId, String questionText,
                     String answerText) {

        this.creatorId = creatorId;
        this.questionText = questionText;
        this.answerText = answerText;
    }


}

