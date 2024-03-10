package edu.java.scrapper.clients.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentItemDTO(
    @JsonProperty("comment_id")
    int commentId,
    @JsonProperty("link")
    String link
)
{}
