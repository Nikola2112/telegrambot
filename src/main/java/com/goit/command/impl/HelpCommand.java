package com.goit.command.impl;

import com.goit.command.constant.Answer;
import com.goit.command.input.CustomerInput;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Objects;

public class HelpCommand extends MainCommand{
    public static final String COMMAND_NAME = "/help";

    @Override
    public boolean supports(String name) {
        return super.supports(name);
    }

    @Override
    public SendMessage handle(CustomerInput customerInput) {
        Objects.requireNonNull(customerInput, "userInput must not be null");

        long chatId = customerInput.getChatId();
        String answer = Answer.MESSAGE_HELP_COMMAND;

        return createSendMessage(chatId, answer);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    private static SendMessage createSendMessage(long chatId, String answer) {
        return new SendMessage(String.valueOf(chatId), answer);
    }
}
