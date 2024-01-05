package com.goit.command.impl;
import com.goit.command.constant.Answer;
import com.goit.service.CustomerService;

import org.springframework.stereotype.Component;
import com.goit.command.input.CustomerInput;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


import java.util.List;

@Component
public class RegisterCommand extends MainCommand {
    public static final String COMMAND_NAME = "/register";
    private final CustomerService customerService;

    public RegisterCommand(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public SendMessage handle(CustomerInput customerInput) {
        boolean existsUser = customerService.existsByChatId(customerInput.getChatId());
        if (existsUser) {
            return new SendMessage(String.valueOf(customerInput.getChatId()), Answer.MESSAGE_REGISTERED);
        }
        return ButtonSendContact.buttonSendContact(customerInput);
    }
    }

