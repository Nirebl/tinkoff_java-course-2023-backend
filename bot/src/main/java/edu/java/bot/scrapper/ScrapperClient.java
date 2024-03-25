package edu.java.bot.scrapper;

import edu.java.bot.model.AddLinkRequest;
import edu.java.bot.model.LinkResponse;
import edu.java.bot.model.ListLinksResponse;
import edu.java.bot.model.RemoveLinkRequest;

public interface ScrapperClient {
    LinkResponse linksDelete(Long tgChatId, RemoveLinkRequest removeLinkRequest);

    ListLinksResponse linksGet(Long tgChatId);

    LinkResponse linksPost(Long tgChatId, AddLinkRequest addLinkRequest);

    void tgChatIdDelete(Long id);

    void tgChatIdPost(Long id);
}
