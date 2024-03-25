package edu.java.scrapper.clients.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AnswerItemDTO(
    @JsonProperty("answer_id")
    int answerId,
    @JsonProperty("link")
    String link
) {
}
