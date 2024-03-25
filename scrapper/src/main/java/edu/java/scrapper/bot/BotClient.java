package edu.java.scrapper.bot;

import edu.java.scrapper.model.BotClientUpdateRequest;

public interface BotClient {
    void updatesPost(BotClientUpdateRequest botClientUpdateRequest);
}
