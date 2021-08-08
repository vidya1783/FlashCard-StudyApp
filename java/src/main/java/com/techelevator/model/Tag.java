package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tag {
    @JsonProperty("tag_id")
    private Long tagId = -1L;
    @JsonProperty("tag_text")
    private String tagText;
    @JsonProperty("creator_id")
    private Long creatorId;

    public Tag() {}

    public Tag(String tagText, Long creatorId)
    {
        this.setCreatorId(creatorId);
        this.setTagText(tagText);
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String toString()
    {
        String returnText = "TagText: " + getTagText() +
                System.lineSeparator() + "Tag Id: " + getTagId()
                + System.lineSeparator() + "Creator Id:" + getCreatorId() + System.lineSeparator();
        return returnText;
    }
}
