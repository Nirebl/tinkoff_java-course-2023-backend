package edu.java.scrapper.clients.stackoverflow;

import edu.java.scrapper.clients.WebScrapperClient;
import java.time.Instant;

public class StackOverflowClient extends WebScrapperClient {
    private final String fromDate = "&fromdate=%s";
    private final String pagination = "&page=%s&pagesize=%s";

    public StackOverflowClient(String baseUrl) {
        super(baseUrl);
    }

    public AnswersDTO getAnswers(int questionId, Instant from, int page, int pageSize) {
        return super.retrieve(
            AnswersDTO.class,
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

    public CommentsDTO getComments(int questionId, Instant from, int page, int pageSize) {
        return super.retrieve(
            CommentsDTO.class,
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
