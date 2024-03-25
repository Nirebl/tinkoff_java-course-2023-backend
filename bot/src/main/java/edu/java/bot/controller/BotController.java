package edu.java.bot.controller;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.bot.Bot;
import edu.java.bot.generated.BotControllerApi;
import edu.java.bot.generated.model.LinkUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController implements BotControllerApi {
    private final Bot bot;

    @Autowired
    public BotController(Bot bot) {
        this.bot = bot;
    }

    @Override
    public ResponseEntity<Void> updatesPost(LinkUpdateRequest linkUpdateRequest) {
        for (var chatId : linkUpdateRequest.getTgChatIds()) {
            try {
                bot.execute(new SendMessage(
                    chatId,
                    String.format("This url %s was changed", linkUpdateRequest.getUrl())
                ));
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return ResponseEntity.ok(null);
    }
}
