package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FlashcardPlusTag extends Flashcard {
    @JsonProperty("tag_list")
    List<Tag> tagList;

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public FlashcardPlusTag()
    {
        super();
    }

    public FlashcardPlusTag(Flashcard flashcard)
    {
        this.setAnswerText(flashcard.getAnswerText());
        this.setQuestionText(flashcard.getQuestionText());
        this.setCreatorId(flashcard.getCreatorId());
        this.setFlashcardId(flashcard.getFlashcardId());
    }
}

