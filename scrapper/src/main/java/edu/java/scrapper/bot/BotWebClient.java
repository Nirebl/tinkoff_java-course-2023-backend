package edu.java.scrapper.bot;

import edu.java.scrapper.model.BotClientUpdateRequest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class BotWebClient implements BotClient {
    private final WebClient webClient;

    public BotWebClient(String baseUrl) {
        this.webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .build();
    }

    @Override
    public void updatesPost(BotClientUpdateRequest botClientUpdateRequest) {
        webClient.post()
            .uri("/updates")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(botClientUpdateRequest)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
    }
}
