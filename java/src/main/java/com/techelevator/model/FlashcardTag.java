package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlashcardTag {
    @JsonProperty("flashcard_id")
    private Long flashcardId;
    @JsonProperty("tag_id")
    private Long tagId;

    public FlashcardTag(Long flashcardId, Long tagId) {
        this.flashcardId = flashcardId;
        this.tagId = tagId;
    }

    public FlashcardTag() {
    }

    public Long getFlashcardId() {
        return flashcardId;
    }

    public void setFlashcardId(Long flashcardId) {
        this.flashcardId = flashcardId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
