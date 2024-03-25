package edu.java.scrapper.model;

import java.net.URI;
import java.util.List;

public record BotClientUpdateRequest(
    Long id,
    URI url,
    String description,
    List<Long> tgChatIds
) {
}
