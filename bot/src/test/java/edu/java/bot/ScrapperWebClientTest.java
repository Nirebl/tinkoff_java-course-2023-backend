package edu.java.bot;

import edu.java.bot.model.AddLinkRequest;
import edu.java.bot.model.RemoveLinkRequest;
import edu.java.bot.scrapper.ScrapperWebClient;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ScrapperWebClientTest {
    @Test
    public void linksDelete() throws URISyntaxException {
        RemoveLinkRequest request = new RemoveLinkRequest(
            new URI(
                "https://stackoverflow.com/questions/52312088/how-to-create-bottomnavigationview-with-xamarin-for-android-api-19")
        );

        ScrapperWebClient scrapperWebClient = new ScrapperWebClient("http://localhost:8080/");
        var response = scrapperWebClient.linksDelete(30L, request);

        assertThat(response.id()).isEqualTo(50L);
        assertThat(response.url()).isEqualTo(new URI(
            "https://stackoverflow.com/questions/60323359/how-to-send-a-body-with-http-delete-when-using-webflux")
        );
    }

    @Test
    public void linksGet() {
        ScrapperWebClient scrapperWebClient = new ScrapperWebClient("http://localhost:8080/");
        var response = scrapperWebClient.linksGet(30L);

        assertThat(response.size()).isEqualTo(2L);
    }

    @Test
    public void linksPost() throws URISyntaxException {
        AddLinkRequest request = new AddLinkRequest(
            new URI(
                "https://stackoverflow.com/questions/39")
        );

        ScrapperWebClient scrapperWebClient = new ScrapperWebClient("http://localhost:8080/");
        var response = scrapperWebClient.linksPost(39L, request);

        assertThat(response.id()).isEqualTo(33L);
        assertThat(response.url()).isEqualTo(new URI(
            "https://stackoverflow.com/questions/23")
        );
    }

    @Test
    public void tgChatIdDelete() {
        ScrapperWebClient scrapperWebClient = new ScrapperWebClient("http://localhost:8080/");
        scrapperWebClient.tgChatIdDelete(78L);
    }

    @Test
    public void tgChatIdPost() {
        ScrapperWebClient scrapperWebClient = new ScrapperWebClient("http://localhost:8080/");
        scrapperWebClient.tgChatIdPost(73L);
    }
}
