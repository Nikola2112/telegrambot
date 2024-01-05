package com.goit.initializer;

import com.goit.service.TelegramBotStarter;
import com.goit.excetpion.ApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramBotInitializer {

    private final TelegramBotStarter telegramBotStarter;

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(telegramBotStarter);
        } catch (TelegramApiException e) {
            log.error("An error occurred while connecting to the Telegram bot!");
            throw new ApplicationException("Error in method init(). Message: " + e.getMessage());
        }
    }
}
