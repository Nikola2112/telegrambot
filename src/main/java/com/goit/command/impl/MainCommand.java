package com.goit.command.impl;

import com.goit.command.CommandHandler;


public abstract class MainCommand implements CommandHandler {
    @Override
    public boolean supports(String name) {
        return getCommandName().equals(name);
    }

    protected abstract String getCommandName();
}
