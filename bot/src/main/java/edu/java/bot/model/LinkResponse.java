package edu.java.bot.model;

import java.net.URI;

public record LinkResponse(
    Long id,
    URI url
) {
}
