package com.goit.command.impl;

import com.goit.command.constant.Answer;

import com.goit.service.CustomerService;
import org.springframework.stereotype.Component;
import com.goit.command.input.CustomerInput;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Objects;


@Component
public class StartCommand extends MainCommand {
    public static final String COMMAND_NAME = "/start";

    private final CustomerService customerService;

    public StartCommand (CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public SendMessage handle(CustomerInput customerInput) {
        Objects.requireNonNull(customerInput, "userInput must not be null");

        String firstName = customerInput.getFirstName();
        String chatId = String.valueOf(customerInput.getChatId());

        String greetingMessage = String.format(Answer.MESSAGE_START_COMMAND, firstName, Answer.BLUSH);
        sendMessageToUser(chatId, greetingMessage);

        return createSendMessage(chatId, greetingMessage);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
    private void sendMessageToUser(String chatId, String message) {
    }

    private static SendMessage createSendMessage(String chatId, String message) {
        return new SendMessage(chatId, message);
    }
}