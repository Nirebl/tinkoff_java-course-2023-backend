package edu.java.scrapper.controller;

import edu.java.scrapper.generated.ScrapperControllerApi;
import edu.java.scrapper.generated.model.AddLinkRequest;
import edu.java.scrapper.generated.model.LinkResponse;
import edu.java.scrapper.generated.model.ListLinksResponse;
import edu.java.scrapper.generated.model.RemoveLinkRequest;
import java.net.URI;
import java.util.ArrayList;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapperController implements ScrapperControllerApi {

    @SneakyThrows @Override
    public ResponseEntity<LinkResponse> linksDelete(Long tgChatId, RemoveLinkRequest removeLinkRequest) {
        LinkResponse response = new LinkResponse(
            50L,
            new URI(
                "https://stackoverflow.com/questions/60323359/how-to-send-a-body-with-http-delete-when-using-webflux")
        );

        return ResponseEntity.ok(response);
    }

    @SneakyThrows @Override
    public ResponseEntity<ListLinksResponse> linksGet(Long tgChatId) {
        ArrayList<LinkResponse> responses = new ArrayList<LinkResponse>();
        responses.add(new LinkResponse(
            10L,
            new URI(
                "https://stackoverflow.com/questions/10")
        ));
        responses.add(new LinkResponse(
            20L,
            new URI(
                "https://stackoverflow.com/questions/20")
        ));

        ListLinksResponse response = new ListLinksResponse(
            responses,
            2
        );

        return ResponseEntity.ok(response);
    }

    @SneakyThrows @Override
    public ResponseEntity<LinkResponse> linksPost(Long tgChatId, AddLinkRequest addLinkRequest) {
        LinkResponse response = new LinkResponse(
            33L,
            new URI(
                "https://stackoverflow.com/questions/23")
        );

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> tgChatIdDelete(Long id) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> tgChatIdPost(Long id) {
        return ResponseEntity.ok(null);
    }
}
