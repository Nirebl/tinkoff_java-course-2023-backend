package edu.java.scrapper.clients.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ActivityItemDTO(
    @JsonProperty("timestamp")
    String timeStamp,
    @JsonProperty("activity_type")
    ActivityType activityType
) {
}
