package edu.java.scrapper;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.core.io.ClassPathResource;
import edu.java.scrapper.clients.stackoverflow.StackOverflowClient;
import java.io.IOException;
import java.time.Instant;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StackOverflowClientTest {
    private static WireMockServer wireMockServer;
    private static StackOverflowClient client;

    @BeforeAll
    public static void beforeAll() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());

        client = new StackOverflowClient(String.format("http://localhost:%s", wireMockServer.port()));
    }

    /*
     * url: https://api.stackexchange.com/2.3/questions/78132692/answers?site=stackoverflow&fromdate=1709251200&page=1&pagesize=10&filter=!nNPvSNXCix
     */
    @Test
    public void hasAnswerChangesForValidQuestionId() throws IOException {
        String testUrl = "/2.3/questions/78132692/answers?site=stackoverflow&fromdate=1709251200&page=1&pagesize=10&filter=!nNPvSNXCix";

        WireMock.stubFor(get(urlEqualTo(testUrl))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(new ClassPathResource("hasAnswerChangesForValidQuestionId.json").getContentAsString(UTF_8))));

        var answers = client.getAnswers(78132692, Instant.ofEpochSecond(1709251200), 1, 10);

        assertThat(answers.hasMore()).isFalse();
        assertThat(answers.items().size()).isEqualTo(2);
        var item1 = answers.items().get(0);
        assertThat(item1.answerId()).isEqualTo(78132987);
        assertThat(item1.link()).isEqualTo("https://stackoverflow.com/questions/78132692/getattribute-not-returning-the-value-i-was-expecting/78132987#78132987");
        var item2 = answers.items().get(1);
        assertThat(item2.answerId()).isEqualTo(78132843);
        assertThat(item2.link()).isEqualTo("https://stackoverflow.com/questions/78132692/getattribute-not-returning-the-value-i-was-expecting/78132843#78132843");
    }

    /*
     * url: https://api.stackexchange.com/2.3/questions/78132692/answers?site=stackoverflow&fromdate=1710028800&page=1&pagesize=10&filter=!nNPvSNXCix
     */
    @Test
    public void hasNotAnswerChangesForValidQuestionId() throws IOException {

        String testUrl = "/2.3/questions/78132692/answers?site=stackoverflow&fromdate=1710028800&page=1&pagesize=10&filter=!nNPvSNXCix";

        WireMock.stubFor(get(urlEqualTo(testUrl))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(new ClassPathResource("hasNotAnswerChangesForValidQuestionId.json").getContentAsString(UTF_8))));

        var answers = client.getAnswers(78132692, Instant.ofEpochSecond(1710028800), 1, 10);

        assertThat(answers.hasMore()).isFalse();
        assertThat(answers.items().size()).isEqualTo(0);
    }

    /*
     * url: https://api.stackexchange.com//2.3/questions/78132692/comments?site=stackoverflow&fromdate=1709251200&page=1&pagesize=3&filter=!nNPvSN_mfN
     */
    @Test
    public void hasCommentChangesForValidQuestionId() throws IOException {
        String testUrl = "/2.3/questions/78132692/comments?site=stackoverflow&fromdate=1709251200&page=1&pagesize=3&filter=!nNPvSN_mfN";

        WireMock.stubFor(get(urlEqualTo(testUrl))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(new ClassPathResource("hasCommentChangesForValidQuestionId.json").getContentAsString(UTF_8))));

        var comments = client.getComments(78132692, Instant.ofEpochSecond(1709251200), 1, 3);

        assertThat(comments.hasMore()).isTrue();
        assertThat(comments.items().size()).isEqualTo(3);
        var item1 = comments.items().get(0);
        assertThat(item1.commentId()).isEqualTo(137747988);
        assertThat(item1.link()).isEqualTo("https://stackoverflow.com/questions/78132692/getattribute-not-returning-the-value-i-was-expecting#comment137747988_78132692");
        var item2 = comments.items().get(1);
        assertThat(item2.commentId()).isEqualTo(137745381);
        assertThat(item2.link()).isEqualTo("https://stackoverflow.com/questions/78132692/getattribute-not-returning-the-value-i-was-expecting#comment137745381_78132692");
        var item3 = comments.items().get(2);
        assertThat(item3.commentId()).isEqualTo(137745363);
        assertThat(item3.link()).isEqualTo("https://stackoverflow.com/questions/78132692/getattribute-not-returning-the-value-i-was-expecting#comment137745363_78132692");
    }

    /*
     * url: https://api.stackexchange.com/2.3/questions/78132692/comments?site=stackoverflow&fromdate=1710092700&page=1&pagesize=5&filter=!nNPvSN_mfN
     */
    @Test
    public void hasNotCommentChangesForValidQuestionId() throws IOException {
        String testUrl = "/2.3/questions/78132692/comments?site=stackoverflow&fromdate=1710092700&page=1&pagesize=5&filter=!nNPvSN_mfN";

        WireMock.stubFor(get(urlEqualTo(testUrl))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(new ClassPathResource("hasNotCommentChangesForValidQuestionId.json").getContentAsString(UTF_8))));

        var comments = client.getComments(78132692, Instant.ofEpochSecond(1710092700), 1, 5);

        assertThat(comments.hasMore()).isFalse();
        assertThat(comments.items().size()).isEqualTo(0);
    }

    @AfterEach
    void afterEach() {
        wireMockServer.resetAll();
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }


}
