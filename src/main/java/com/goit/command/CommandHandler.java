package com.goit.command;

import com.goit.command.input.CustomerInput;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CommandHandler {
    boolean supports(String command);

    SendMessage handle(CustomerInput command);
}
