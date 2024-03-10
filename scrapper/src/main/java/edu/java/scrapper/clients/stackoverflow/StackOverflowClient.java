package edu.java.scrapper.clients.stackoverflow;

import java.time.Instant;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class StackOverflowClient {
    private final String fromDate = "&fromdate=%s";
    private final String pagination = "&page=%s&pagesize=%s";
    private final WebClient webClient;

    public StackOverflowClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    protected <T> Mono<T> retrieve(Class<T> elementClass, String resources) {
        return this.webClient.get().uri(resources).retrieve().bodyToMono(elementClass);
    }

    public StackOverflowAnswerDTO getAnswers(int questionId, Instant from, int page, int pageSize) {
        return retrieve(
            StackOverflowAnswerDTO.class,
            String.format(
                "2.3/questions/%s/answers?site=stackoverflow"
                    + fromDate
                    + pagination
                    + "&filter=!nNPvSNXCix",
                questionId,
                from.getEpochSecond(),
                page,
                pageSize
            )
        ).block();
    }

    public StackOverflowCommentDTO getComments(int questionId, Instant from, int page, int pageSize) {
        return retrieve(
            StackOverflowCommentDTO.class,
            String.format(
                "2.3/questions/%s/comments?site=stackoverflow"
                    + fromDate
                    + pagination
                    + "&filter=!nNPvSN_mfN",
                questionId,
                from.getEpochSecond(),
                page,
                pageSize
            )
        ).block();
    }

}
