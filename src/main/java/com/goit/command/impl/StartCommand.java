package com.goit.command.impl;

import com.goit.command.constant.Answer;

import com.goit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.goit.command.input.CustomerInput;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Objects;


@Component
public class StartCommand extends MainCommand {
    public static final String COMMAND_NAME = "/start";

    private final CustomerService customerService;
    @Autowired
    public StartCommand (CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public SendMessage handle(CustomerInput customerInput) {
        final var answer = String.format(Answer.MESSAGE_START_COMMAND, customerInput.getFirstName(), Answer.BLUSH);
        return new SendMessage(String.valueOf(customerInput.getChatId()), answer);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
