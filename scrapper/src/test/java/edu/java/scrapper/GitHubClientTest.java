package edu.java.scrapper;

import edu.java.scrapper.clients.github.ActivityType;
import edu.java.scrapper.clients.github.TimePeriod;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import edu.java.scrapper.clients.github.GitHubClient;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GitHubClientTest {
    private static WireMockServer wireMockServer;
    private static GitHubClient client;

    @BeforeAll
    public static void beforeAll() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());

        client = new GitHubClient(String.format("http://localhost:%s", wireMockServer.port()));
    }

    /*
     * url: https://api.github.com/repos/gdarid/curves/activity?time_period=week
     */
    @Test
    public void activitiesAvailable() throws IOException {
        String testUrl = "/repos/gdarid/curves/activity?time_period=week";

        WireMock.stubFor(get(urlEqualTo(testUrl))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(new ClassPathResource("activitiesAvailable.json").getContentAsString(UTF_8))));

        var activities = client.getActivities("gdarid", "curves", TimePeriod.week);

        assertThat(activities[0].activityType()).isEqualTo(ActivityType.push);
        assertThat(activities[0].timeStamp()).isEqualTo("2024-03-11T16:38:01Z");
        assertThat(activities[1].activityType()).isEqualTo(ActivityType.push);
        assertThat(activities[1].timeStamp()).isEqualTo("2024-03-09T19:26:35Z");
        assertThat(activities[2].activityType()).isEqualTo(ActivityType.branch_creation);
        assertThat(activities[2].timeStamp()).isEqualTo("2024-03-09T17:55:19Z");
    }

    /*
     * url: https://api.github.com/repos/walter201230/Python/activity?time_period=day
     */
    @Test
    public void activitiesUnavailable() throws IOException {
        String testUrl = "/repos/walter201230/Python/activity?time_period=day";

        WireMock.stubFor(get(urlEqualTo(testUrl))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(new ClassPathResource("activitiesUnavailable.json").getContentAsString(UTF_8))));

        var activities = client.getActivities("walter201230", "Python", TimePeriod.day);

        assertThat(activities.length).isEqualTo(0);
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
