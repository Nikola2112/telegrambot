package com.goit.service;
import com.goit.command.CommandHandler;
import com.goit.command.impl.HelpCommand;
import com.goit.command.impl.RegisterCommand;
import com.goit.command.impl.StartCommand;
import com.goit.command.impl.TicketDateCommand;
import com.goit.command.input.CustomerInput;
import com.goit.config.BotConfig;
import com.goit.dto.CustomerDto;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TelegramBotService extends TelegramLongPollingBot {

    private final InvoiceService invoiceService;
    private final TicketService ticketService;
    private final CustomerService customerService;
    private final BotConfig config;
    private final List<CommandHandler> handlers;

    public TelegramBotService(InvoiceService invoiceService, TicketService ticketService
            , CustomerService customerService,  BotConfig config) {
        this.invoiceService = invoiceService;
        this. ticketService =  ticketService;
        this.customerService = customerService;
        this.config = config;
        this.handlers = List.of(
                new StartCommand(customerService),
                new HelpCommand(),
                new RegisterCommand(customerService),
                new TicketDateCommand(ticketService)
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
        if (update.hasMessage()) {
            if (update.getMessage().hasContact()) {
                final var customerDto= toRegisteredCustomerDto(update);
                customerService.registered(customerDto);
            }
            if (update.getMessage().hasText()) {
                final var userInput = toUserInput(update);
                final var responseMessage = handleCommand(userInput);
                sendMessage(responseMessage);
            }
        }
    }

    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException();
        }
    }


    private CustomerDto toRegisteredCustomerDto(Update update) {
        return new CustomerDto(
                update.getMessage().getChatId(),
                update.getMessage().getContact().getFirstName(),
                update.getMessage().getContact().getLastName(),
                update.getMessage().getContact().getPhoneNumber(),
                new Timestamp((long) update.getMessage().getDate() * 1000));
    }

    private SendMessage handleCommand(final CustomerInput customerInput) {
        Optional<CommandHandler> currentHandler = Optional.empty();
        for (CommandHandler handler : handlers) {
            if (handler.supports(customerInput.getCommand())) {
                currentHandler = Optional.of(handler);
                break;
            }
        }
        return currentHandler
                .orElseThrow(() -> new IllegalArgumentException("Sorry, command was not recognized: " + customerInput.getCommand()))
                .handle(customerInput);
    }

    private CustomerInput toUserInput(final Update update) {
        return new CustomerInput(
                update.getMessage().getChatId(),
                update.getMessage().getChat().getFirstName(),
                update.getMessage().getChat().getLastName(),
                new Timestamp(update.getMessage().getDate()),
                update.getMessage().getText()
        );
    }
}