package com.goit.command.impl;

import com.goit.command.constant.Answer;
import com.goit.command.input.CustomerInput;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;



public class HelpCommand extends MainCommand{
    public static final String COMMAND_NAME = "/help";

    @Override
    public boolean supports(String name) {
        return super.supports(name);
    }

    @Override
    public SendMessage handle(CustomerInput customerInput) {
        String answer = String.format(Answer.MESSAGE_HELP_COMMAND);
        return new SendMessage(String.valueOf(customerInput.getChatId()), answer);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}

