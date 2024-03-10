package edu.java.scrapper.clients;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

abstract public class WebScrapperClient {
    private final WebClient webClient;

    public WebScrapperClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    protected <T> Mono<T> retrieve(Class<T> elementClass, String resources) {
        return this.webClient.get().uri(resources).retrieve().bodyToMono(elementClass);
    }
}
