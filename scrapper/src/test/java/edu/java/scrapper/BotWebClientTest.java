package edu.java.scrapper;

import edu.java.scrapper.bot.BotWebClient;
import edu.java.scrapper.model.BotClientUpdateRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BotWebClientTest {

    @Test
    public void updatePost() throws URISyntaxException {
        List<Long> tgChatIds = new ArrayList<>();
        tgChatIds.add(10L);
        tgChatIds.add(20L);
        tgChatIds.add(30L);

        BotClientUpdateRequest requestDTO = new BotClientUpdateRequest(
            1L,
            new URI("https://stackoverflow.com/questions/52312088/how-to-create-bottomnavigationview-with-xamarin-for-android-api-19"),
            "MyDescrip",
            tgChatIds
        );

        BotWebClient botWebClient = new BotWebClient("http://localhost:8090/");
        botWebClient.updatesPost(requestDTO);
    }

}
