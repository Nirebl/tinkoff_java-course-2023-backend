package edu.java.scrapper.clients.stackoverflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record StackOverflowAnswerDTO(
    @JsonProperty("has_more")
    boolean hasMore,
    @JsonProperty("items")
    List<AnswerItemDTO> items
)
{}
