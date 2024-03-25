package edu.java.bot.scrapper;

import edu.java.bot.model.AddLinkRequest;
import edu.java.bot.model.LinkResponse;
import edu.java.bot.model.ListLinksResponse;
import edu.java.bot.model.RemoveLinkRequest;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class ScrapperWebClient implements ScrapperClient {
    private final WebClient webClient;

    public ScrapperWebClient(String baseUrl) {
        this.webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .build();
    }

    @Override
    public LinkResponse linksDelete(Long tgChatId, RemoveLinkRequest removeLinkRequest) {
        return webClient
            .method(HttpMethod.DELETE)
            .uri("/links")
            .header("Tg-Chat-Id", tgChatId.toString())
            .bodyValue(removeLinkRequest)
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .block();
    }

    @Override
    public ListLinksResponse linksGet(Long tgChatId) {
        return webClient
            .method(HttpMethod.GET)
            .uri("/links")
            .header("Tg-Chat-Id", tgChatId.toString())
            .retrieve()
            .bodyToMono(ListLinksResponse.class)
            .block();
    }

    @Override
    public LinkResponse linksPost(Long tgChatId, AddLinkRequest addLinkRequest) {
        return webClient
            .post()
            .uri("/links")
            .header("Tg-Chat-Id", tgChatId.toString())
            .bodyValue(addLinkRequest)
            .retrieve()
            .bodyToMono(LinkResponse.class)
            .block();
    }

    @Override
    public void tgChatIdDelete(Long id) {
        webClient
            .delete()
            .uri(String.format("/tg-chat/%s", id))
            .retrieve()
            .bodyToMono(Void.class)
            .block();
    }

    @Override
    public void tgChatIdPost(Long id) {
        webClient
            .post()
            .uri(String.format("/tg-chat/%s", id))
            .retrieve()
            .bodyToMono(Void.class)
            .block();
    }
}
