package com.goit.service;

import com.goit.command.*;
import com.goit.command.impl.*;


import com.goit.command.input.CustomerInput;
import com.goit.config.BotConfig;

import com.goit.excetpion.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



import java.util.List;
import java.util.Optional;


import static com.goit.utils.DtoConverter.toCustomerInput;
import static com.goit.utils.DtoConverter.toRegisteredCustomerDto;



@Component
@Slf4j
public class TelegramBotStarter extends TelegramLongPollingBot {

    private final BotConfig config;
    private final BookingTicketService bookingTicket;
    private final CustomerService customerService;
    private final List<CommandHandler> handlers;

    public TelegramBotStarter(BookingTicketService bookingTicket, CustomerService customerService, BotConfig config) {
        this.bookingTicket = bookingTicket;
        this.customerService = customerService;
        this.config = config;
        this.handlers = List.of(
                new StartCommand(),
                new HelpCommand(),
                new RegisterCommand(customerService),
                new TicketDateCommand(bookingTicket)
        );
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {
                if (update.getMessage().hasContact()) {
                     var registeredCustomerDto = toRegisteredCustomerDto(update);
                    customerService.registered(registeredCustomerDto);
                }
                if (update.getMessage().hasText()) {
                     var customerInput = toCustomerInput(update);
                     var sendMessage = handleCommand(customerInput);
                    sendMessage(sendMessage);
                }
            }
        } catch (ApplicationException e) {
            log.warn("Application exception: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("Validation exception: " + e.getMessage());
        } catch (Exception e) {
            log.error("Another error: " + e.getMessage());
        }
    }
    public void sendMessage( SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new ApplicationException("Error in method sendMessage()");
        }
    }
    private SendMessage handleCommand(final CustomerInput customerInput) {
        log.info("Starting command processing from user with ID: " + customerInput.getChatId());
        Optional<CommandHandler> currentHandler = Optional.empty();
        for (CommandHandler handler : handlers) {
            if (handler.supports(customerInput.getCommand())) {
                currentHandler = Optional.of(handler);
                break;
            }
        }
        return currentHandler
                .orElseThrow(() -> new IllegalArgumentException(
                        "Sorry, command was not recognized: " + customerInput.getCommand()))
                .handle(customerInput);
    }
}
