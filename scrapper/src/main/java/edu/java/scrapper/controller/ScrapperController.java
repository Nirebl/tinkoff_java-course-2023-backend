package edu.java.scrapper.controller;

import edu.java.scrapper.generated.ScrapperControllerApi;
import edu.java.scrapper.generated.model.AddLinkRequest;
import edu.java.scrapper.generated.model.LinkResponse;
import edu.java.scrapper.generated.model.ListLinksResponse;
import edu.java.scrapper.generated.model.RemoveLinkRequest;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapperController implements ScrapperControllerApi {

    @SneakyThrows @Override
    public ResponseEntity<LinkResponse> linksDelete(Long tgChatId, RemoveLinkRequest removeLinkRequest) {
        return ResponseEntity.ok(null);
    }

    @SneakyThrows @Override
    public ResponseEntity<ListLinksResponse> linksGet(Long tgChatId) {
        return ResponseEntity.ok(null);
    }

    @SneakyThrows @Override
    public ResponseEntity<LinkResponse> linksPost(Long tgChatId, AddLinkRequest addLinkRequest) {
         return ResponseEntity.ok(null);
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
