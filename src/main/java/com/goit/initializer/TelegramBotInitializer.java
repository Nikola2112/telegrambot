package com.goit.initializer;

import com.goit.service.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@Component
@RequiredArgsConstructor
public class TelegramBotInitializer {

    private final TelegramBotService telegramBotService;

    @EventListener({ContextRefreshedEvent.class})
    void init() throws TelegramApiException {
        var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(telegramBotService);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}